from duze_cyfry import dajCyfre
import random
from turtle import *
tracer(0,1)
def printN(n):
    position=pos()
    L=dajCyfre(n)
    R = random.random()
    G = random.random()
    B = random.random()
    color=(R,G,B)
    
    for i in range(len(L)):
        for j in range(len(L[i])):
            if L[i][j]=='#':
                square(color)
                fd(20)
            else:
                fd(20)
        goto(position[0],position[1]-(i+1)*30)
        

def square(c):
  fillcolor(c)
  begin_fill()
  for i in range(4):
    fd(15)
    rt(90)
  end_fill()

def numbers(n):
    pu()
    L=list(str(n))
    for i in range(len(L)):
        printN(int(L[i]))
        print(pos())
        goto((i+1)*120,0)
        

numbers(12345)
#printN(2)
#square((1,1,1))
