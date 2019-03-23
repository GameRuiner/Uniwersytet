from wdi import *
a=Array(2)

def fib(n):
    a[0] = 1
    a[1] = 1
    for i in range(2,n+1):
        a[i%2]=a[0]+a[1]
    return(n,a[(i-1)%2])
for j in range(2,10):
    print(fib(j))