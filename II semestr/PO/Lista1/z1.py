from math import *
def newFigura(typ, koor):
    return [typ,koor]
def pole(Figura):
    if Figura[0]=='trojkat':
        koor = Figura[1]
        t1 = koor[0]
        t2 = koor[1]
        t3 = koor[2]
        a = sqrt((t1[0]-t2[0])**2 + (t1[1]-t2[1])**2)
        b = sqrt((t2[0]-t3[0])**2 + (t2[1]-t3[1])**2)
        c = sqrt((t1[0]-t3[0])**2 + (t1[1]-t3[1])**2)
        p = (a+b+c)/2
        return sqrt(p*(p-a)*(p-b)*(p-c))
    elif Figura[0]=='kwadrat':
        koor = Figura[1]
        t1 = koor[0]
        t2 = koor[1]
        a = sqrt((t1[0]-t2[0])**2 + (t1[1]-t2[1])**2)
        return a*a
    elif Figura[0] == 'kolo':
        koor = Figura[1]
        r = koor[1]
        return pi*r*r
def przesun(Figura, xy ):
    x=xy[0]
    y=xy[1]
    koor = Figura[1]
    if Figura[0]!='kolo':
        for k in koor:
            k[0]+=x
            k[1]+=y
    else:
        koor[0][0]+=x
        koor[0][1]+=y

def sumapol(Figury):
    res=0
    for Figura in Figury:
        res+=pole(Figura)
    return res
