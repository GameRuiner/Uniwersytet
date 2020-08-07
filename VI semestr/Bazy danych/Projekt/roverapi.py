#!/usr/bin/env python
from collections import defaultdict
import sys,psycopg2,json

def main(argv):
   str=" ".join(argv)
   argv = str.split('\r')
   if(argv[0] == '--init'):
      init()
   else:
      json_parser(argv)
   
def init():
   conn = db_open()
   cursor = conn.cursor()
   PATH_TO_FILE = 'init.sql'
   
   f = open('init.sql','r')
   sql = f.read()
   cursor.execute(sql)
   
   conn.commit()
   print('{"status": "OK"}')

   
def db_open(database='student',login='app',password='qwerty'):
   conn = psycopg2.connect(dbname=database, user=login, 
                        password=password, host='localhost')
   return conn

def node(id, lat, lon, description,cursor):
   cursor.execute("insert into node values ({0}, 'SRID=4326;POINT({1} {2})', {1}, {2}, '{3}');".format(id, lat, lon, description));
   print('{"status": "OK"}')
   
def catalog(version, nodes, cursor):
   nodes_str = "'{"
   for node in nodes:
      nodes_str+=str(node)+','
   nodes_str = nodes_str[:-1]
   nodes_str+="}'"
   cursor.execute("insert into catalog values ({0}, {1}, 0);".format(version, nodes_str))
   print('{"status": "OK"}')  

def closest_nodes(lat,lon,cursor):
   json_nodes = {"status": "OK", "data": []}
   cursor.execute("SELECT id, dist, lat, lon FROM node t1, round(ST_Distance(t1.geog, 'SRID=4326;POINT({0} {1})')) as dist ORDER BY dist LIMIT 3;".format(lat,lon))
   for row in cursor:
      json_row = {"node": row[0], "olat": row[2], "olon": row[3], "distance": int(row[1])}
      json_nodes["data"].append(json_row)
   print(json_nodes)
   

def trip(cyclist, date, version, cursor):
   cursor.execute("insert into trip values ('{0}', '{1}', {2});".format(cyclist, date, version))
   print('{"status": "OK"}')
   
def party(cyclist, date, cursor):
   json_nodes = {"status": "OK", "data": []}
   cursor.execute('''
   SELECT cyclist, nodes, dist 
   FROM (SELECT nodes[('{0}'-date)+2], cyclist FROM trip JOIN catalog USING (version) WHERE cyclist !='{1}') as candidats 
        JOIN node n1 ON (nodes = id),
        node n2, 
        round(ST_Distance(n1.geog, n2.geog))as dist, 
        (SELECT nodes[('{0}'-date)+2] as party_leader FROM trip JOIN catalog USING (version) WHERE cyclist ='{1}') as party_to
   WHERE n2.id = party_to.party_leader AND dist < 20000
   ORDER BY dist,cyclist;
   '''.format(date, cyclist))
   for row in cursor:
      json_row = {"ocyclist": row[0], "node": row[1], "distance": int(row[2])}
      json_nodes["data"].append(json_row)
   print(json_nodes)
   
def guests(node_id,date,cursor):
   json_nodes = {"status": "OK", "data": []}
   cursor.execute('''
       SELECT cyclist FROM
       (SELECT nodes[('{0}'-date)+2], cyclist FROM trip JOIN catalog USING (version)) as nodes_cyclist
       WHERE nodes = {1}
       ORDER BY cyclist;
       '''.format(date, node_id))
   for row in cursor:
      json_row = {"cyclist": row[0]}
      json_nodes["data"].append(json_row)
   print(json_nodes)

   
def cyclists(limit, cursor):
   json_nodes = {"status": "OK", "data": []}
   cursor.execute('''
       SELECT name, no_trips, distance 
       FROM cyclist 
       ORDER BY distance, name
       LIMIT {0}'''.format(limit))
   for row in cursor:
      json_row = {"cyclist": row[0], "no_trips": row[1], "distance": row[2]}
      json_nodes["data"].append(json_row)
   print(json_nodes)
 
def json_parser(json_obj):
   conn = db_open()
   cursor = conn.cursor()
   for obj in json_obj:
     request = json.loads(obj)
     function = request['function']
     body = request['body']
     
     if (function == 'node'):
        node_id = body['node']
        lat = body['lat']
        lon = body['lon']
        description = body['description']
        node(node_id, lat, lon, description, cursor)
        
     if (function == 'catalog'):
         catalog_id = body['version']
         nodes = body['nodes']
         catalog(catalog_id, nodes, cursor)
         
     if (function == 'closest_nodes'):
        lat = body['ilat']
        lon = body['ilon']
        closest_nodes(lat,lon,cursor)
        
     if (function == 'trip'):
         cyclist = body['cyclist']
         date = body['date']
         version = body['version']
         trip(cyclist, date, version, cursor)
     
     if  (function == 'party'):
         cyclist = body['icyclist']
         date = body['date']
         party(cyclist, date, cursor)
         
     if  (function == 'guests'):
         node_id = body['node']
         date = body['date']
         guests(node_id,date,cursor)
     
     if  (function == 'cyclists'):
         limit = body['limit']
         cyclists(limit,cursor)
         
   conn.commit()


if __name__ == "__main__":
   main(sys.argv[1:])