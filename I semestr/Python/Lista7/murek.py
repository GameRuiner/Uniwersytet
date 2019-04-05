from turtle import *
import random 

def kwadrat(bok):
   R = random.random()
   G = random.random()
   B = random.random()
   color=(R,G,B)
   fillcolor(color)
   begin_fill()
   for i in range(4):
     fd(bok)
     rt(90)
   end_fill()
   
def murek(s,bok):
  for a in s:
    if a == 'f':
       kwadrat(bok)
       fd(bok)
    elif a == 'b':
       kwadrat(bok)
       fd(bok)       
    elif a == 'l':
       bk(bok)
       lt(90)
    elif a == 'r':
      rt(90)
      fd(bok)

      
color('black', 'yellow','green')

ht()
pu()
tracer(0,0) # szybkie rysowanie    
#murek('fffffffffrfffffffffflfffffffffrfffffl',10)   
#murek(4 * 'fffffr', 14)   
update() # uaktualnienie rysunku
#murek('f'+('f'*19+'lfl'+'f'*19+'rfr')*9+'f'*19,15)
for i in range(1,20):
   murek('f'*i+'r',20)

#input()
