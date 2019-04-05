import math
from turtle import *
points = []
speed('fastest')
for x in open('punkty.txt'):
  x = x.strip()
  Lis = x.split()
  points.append((int(Lis[0]),int(Lis[1])))
  
def dist(p1,p2):
  return ((p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2) ** 0.5


for i in points:
  lpath = []
  distance = 0
  spath = []
  lpath.append(i)
  for j in points:
    if j in lpath: continue
    d = dist(i,j)
    spath.append((d,j))
  m = min(spath)
  distance+= m[0]
  lpath.append(m[1])
  while len(lpath)<len(points):
    spath=[]
    for j in points:
      if j in lpath: continue
      d = dist(lpath[len(lpath)-1],j)
      spath.append((d,j))
    m = min(spath)
    distance+= m[0]
    lpath.append(m[1])
  if distance < 5258:
    print(lpath)
    print(distance)
    break

print('done')
    
def draw_points(ps):
  pu()
  fillcolor('yellow')
  for p in ps:
    begin_fill()
    goto(*p)
    circle(10)
    end_fill()
  pd()    

def draw_line(ps, c):
  pu()
  goto(*ps[0])
  pd()
  pencolor(c)
  for p in ps[1:]:
    goto(*p)  # == goto(p[0], p[1])

draw_points(points)
draw_line(lpath,'violet')








