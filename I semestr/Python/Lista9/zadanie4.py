from turtle import *
import random
tracer(0,1)
#speed('fastest')
pu()
lt(90)
fd(200)
rt(90)
pd()
def koch(poziom, dlugosc):
  if poziom == 0:
    kwadrat(dlugosc)
  else:
    dlugosc = (dlugosc / 3)
    koch(poziom-1, dlugosc)
    lt(90)
    koch(poziom-1, dlugosc / 1.2)
    rt(90)
    koch(poziom-1, dlugosc / 1.2)
    rt(90)
    koch(poziom-1, dlugosc / 1.2)
    lt(90)
    koch(poziom-1, dlugosc)
def kwadrat(bok):
  R = random.random()
  G = random.random()
  B = random.random()
  fillcolor(R,G,B)
  begin_fill()
  for i in range(4):
    fd(bok)
    rt(90)  
  fd(bok)
  end_fill()
  
for i in range(4):
  koch(3, 400)
  rt(90)


