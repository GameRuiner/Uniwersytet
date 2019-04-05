from turtle import fd, bk, lt, rt, pu, pd, speed
from random import randint

speed('slow')

def branch(n):
    c=randint(10,30)
    if n>30:
        n=n-15
        rt(c)
        fd(n)
        branch(n)
        bk(n)
        lt(c)
        fd(n)
        Lbranch(n)
        bk(n)
def Lbranch(n):
    c=randint(10,30)
    if n>30:
        n=n-15
        lt(c)
        fd(n)
        Lbranch(n)
        bk(n)
        rt(c)
        fd(n)
        branch(n)
        bk(n)
        
def tree(n):
    lt (90)
    pu()
    bk(300)
    pd()
    fd(100)
    branch(n)
    Lbranch(n)
tree(100)

#x = input('Nacisnij cos, prosze')
