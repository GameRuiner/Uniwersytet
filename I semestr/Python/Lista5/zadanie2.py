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
    for i in range(n):
        for j in range(n):
            r= random.random()
            g= random.random()
            b= random.random()
            k=r,g,b
            kwadrat(k)
            fd(30)
        pu()
        goto(0,(i+1)*30)
        pd()


squares(10)

