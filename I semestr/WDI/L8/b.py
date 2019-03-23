import random
import time
def b(n):
    if n==1: return 0
    if n%2:
        return 1+b((n+1)/2)
    return 1+b(n/2)


def c(n):
    if n==1: return 1
    if n%2: return c((n-1)/2) + c((n+1)/2) + 1
    return 2*c(n//2)+1


def minN(a,b,p):
    if p==b: return a[p]
    elif p-b == 1:
            if a[p]<a[b]: return a[p]
            else: return a[b]
    else:
        s=(b+p)//2
        m1 = minN(a,b,s)
        m2 = minN(a,s+1,p)
        if m1 < m2: return m1
        else: return m2
def maxm1(a, n):
    min = a[0]
    for k in range(1,n):
        if min>a[k]: min=a[k]
    return min


a = [i for i in range(100,100000)]
random.shuffle(a)
n = len(a)
t1 = time.time()
print(minN(a,0,n-1))
print(time.time() - t1)

t1 = time.time()
print(maxm1(a,n))    
print(time.time() - t1)
