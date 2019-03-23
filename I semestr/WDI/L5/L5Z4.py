from wdi import *
a=Array(3)

def fib(n):
    a[0] = 1
    a[1] = 1
    a[2] = 1
    for i in range(3,n+1):
        a[i%3]=a[0]+a[1]+a[2]
    return(n,a[(i)%3])
for j in range(3,10):
    print(fib(j))