from duze_cyfry import dajCyfre
from random import *
from turtle import *
tracer(0,1)
colors=[['red',1],['green',2],['blue',3],['yellow',4],['orange',5]]
positions=[[0] * 37 for i in range(37)]
def printN(n):
    position=pos()
    L=dajCyfre(n)
    choise=choice(colors)
    color=choise[0]
    nColor=choise[1]
    if goodNum(position,nColor,L):
        for i in range(len(L)):
            for j in range(len(L[i])):
                if L[i][j]=='#':
                    square(color)
                    fd(10)
                else:
                    fd(10)
            goto(position[0],position[1]-(i+1)*10)
        
def square(c):
  pd()
  fillcolor(c)
  begin_fill()
  for i in range(4):
    fd(10)
    rt(90)
  end_fill()
  pu()

def numbers(n):
    pu()
    for i in range(20):
        for i in range(len(n)):
            b=len(positions)-2
            x=randint(1,b)
            y=randint(1,b)
            goto(x*10,y*10)
            printN(n[i])
    
def goodNum(p,c,L):
    x=p[0]
    y=p[1]
    if y-(5*10)<0 or x+5*10>350 or positions[x//10][y//10]!=0:
        return False
    for i in range(len(L)):
        for j in range(len(L[i])):
            if L[i][j]=='#':
                if isBadP(p,i,j,c): return False
    for i in range(len(L)):
        for j in range(len(L[i])):
            if L[i][j]=='#':
                positions[x//10+j][y//10-i]=c
    return True

def isBadP(p,i,j,c):
    xx=p[0]
    yy=p[1] 
    x=xx//10+j
    y=yy//10-i
    if positions[x][y]==0:
        if positions[x+1][y]!=c and positions[x][y+1]!=c and positions[x][y-1]!=c and positions[x-1][y]!=c:
            return False
        else:
            return True
    else: 
        return True


x=list(range(10))
shuffle(x)
numbers(x)

