import math
from decimal import *
def chudnovsky2(n):
    pi = Decimal(13591409)
    ak = Decimal(1)
    k = 1
    while k < n:
        ak *= -Decimal((6*k-5)*(2*k-1)*(6*k-1))/Decimal(k*k*k*26680*640320*640320)

        val = ak*(13591409 + 545140134*k)
        
        d = Decimal((6*k-5)*(2*k-1)*(6*k-1))/Decimal(k*k*k*26680*640320*640320)
        
        pi += val
        k += 1
    pi = pi * Decimal(10005).sqrt()/4270934400
    pi = pi**(-1)
    return pi


def word(x):
    while not x[-1:].isalpha() and len(x)>1:
        x=x[:-1]
    return x
N = 100
getcontext().prec = N
my_pi = chudnovsky2(N)
print(my_pi)
L=[]
for i in str(my_pi):
    if i == '.': continue
    L.append(i)
c=0
s = []
ss = []
for x in open('pan-tadeusz.txt'):
    x=x.strip()
    L2 = x.split()
    if len(L2)==0: continue
    for w in L2:
        w = word(w)
        n = int(L[c])
        if n==0: n=10
        if len(w) == n:
            s.append(w)
            c+=1
        else:
            if len(s)>len(ss): ss=s
            c=0
            s=[]   

print(' '.join(ss))
        
    

