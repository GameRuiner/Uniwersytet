from L35Z4 import *
from wdi import *
def potNr(a,b):
    rez = 1
    while b>0:
        if b%2:
            rez=rez*a
        b = b // 2
        a = a * a
    return rez

def potR(a,b):
    if not b:
        return 1
    if b%2:
        return a*potR(a*a,b//2)
    return potR(a*a,b/2)
def p(k):
    max=0
    min=k*k
    maxi=0
    mini=0
    start=potR(2,k)
    end=potR(2,k+1)
    for i in range(start,end):
        a=Array(24)
        a=toU2(i)
        counter=0
        for j in range(24):
            if a[j]: counter+=2
            else: counter+=1
        if counter>max:
            max=counter
            maxi=i
        if counter<min:
            min=counter
            mini=i
    print('max i =',maxi,'liczba mnożeń=',max,'min i =',mini,'liczba mnożeń=',min)

p(10)