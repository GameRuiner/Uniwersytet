from turtle import *
import random 

speed('fastest')

def kwadrat(kolor):
    fillcolor(kolor)
    begin_fill()
    for i in range(4):
        fd(30)
        rt(90)
    end_fill()

def squares(n):
    r1= random.random()
    g1= random.random()
    b1= random.random()
    r2= random.random()
    g2= random.random()
    b2= random.random()
    k1 = r1,g1,b1
    k2 = r1,g2,b2
    i=1
    c=0
    while True:
        k=i
        while k>0:
                kolor= ( c/(n) * r2 + (1-c/(n)) * r1,
                         c/(n) * g2 + (1-c/(n)) * g1,
                         c/(n) * b2 + (1-c/(n)) * b1 )
                kwadrat(kolor)
                fd(30)
                k=k-1
                c=c+1
                if c==n+1: return 
        i=i+1
        c=c-1
        rt(90)
        



squares(151)


