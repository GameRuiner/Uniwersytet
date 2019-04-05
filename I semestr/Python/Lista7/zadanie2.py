from turtle import *

goto(0,250)
tracer(0,1)
def square(c):
  pd()
  fillcolor(c)
  begin_fill()
  for i in range(4):
    fd(8)
    rt(90)
  end_fill()
  pu()
L=[]
for x in open('niespodzianka.txt'):
  L.append(x.split())
for i in range(len(L)):
    for j in range(len(L[i])):
        L[i][j]=eval(L[i][j])

def niespodzianka(L):
    for i in range(len(L)):
        for j in range(len(L[i])-1):
            sety(250-(j)*8)
            c=L[i][j]
            R=c[0]/255
            G=c[1]/255
            B=c[2]/255
            square((R,G,B))
            
        fd(8)    
        
        
        #print(i)

niespodzianka(L)
