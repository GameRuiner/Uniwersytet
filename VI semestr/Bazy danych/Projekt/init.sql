DROP TABLE node CASCADE;
DROP TABLE catalog CASCADE;
DROP TABLE  cyclist CASCADE;
DROP TABLE trip CASCADE;

CREATE TABLE  node (
      id int PRIMARY KEY, 
      geog geography, 
      lat float, 
      lon float,  
      description text
 );

CREATE INDEX on node using Gist (geog);

CREATE TABLE  catalog   (
      version int PRIMARY KEY, 
      nodes int[], 
      route_length int
);

CREATE OR REPLACE FUNCTION catalog_insert() RETURNS trigger AS $catalog_insert$
    DECLARE
      route_length int := 0;
      old_node int := NEW.nodes[1];
      new_node int;
      old_geog geography;
      new_geog geography;
      distance int;
      n int;
    BEGIN
    
        FOREACH n IN ARRAY NEW.nodes 
        LOOP
            IF old_node = n THEN
               CONTINUE;
            END IF;
            SELECT geog INTO old_geog FROM node WHERE id=old_node;
            SELECT geog INTO new_geog FROM node WHERE id=n;
            SELECT dist INTO distance FROM round(ST_Distance(old_geog, new_geog)) as dist; 
            route_length := route_length + distance;
            old_node = n;
        END LOOP;
        
        RETURN (NEW.version, NEW.nodes, route_length); 
        
    END;
$catalog_insert$ LANGUAGE plpgsql;

CREATE TRIGGER catalog_insert BEFORE INSERT ON catalog FOR EACH ROW EXECUTE PROCEDURE catalog_insert();

CREATE TABLE  cyclist (
      name text PRIMARY KEY, 
      no_trips int, 
      distance int
);

CREATE TABLE  trip  (
      cyclist text REFERENCES cyclist(name), 
      date date, 
      version int REFERENCES catalog(version)
);

CREATE OR REPLACE FUNCTION trip_insert() RETURNS trigger AS $trip_insert$
    DECLARE
      n int;
      add_distance int;
    BEGIN
      SELECT COUNT(name) INTO n FROM cyclist WHERE name=NEW.cyclist;
      SELECT route_length INTO add_distance FROM catalog c WHERE c.version=NEW.version;
      IF n = 0 THEN
         INSERT INTO cyclist VALUES (NEW.cyclist, 1, add_distance);
      ELSE
         UPDATE cyclist SET no_trips = no_trips+1, 
                            distance = add_distance + distance
         WHERE name = NEW.name;
      END IF;
      RETURN NEW; 
        
    END;
$trip_insert$ LANGUAGE plpgsql;

CREATE TRIGGER trip_insert BEFORE INSERT ON trip FOR EACH ROW EXECUTE PROCEDURE trip_insert();
