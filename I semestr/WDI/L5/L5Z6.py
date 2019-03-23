from wdi import *
def fibonacci(k,r):
    a = Array(2)
    a[0]=1
    a[1]=1
    for i in range(2,k):
        a[i%2]=(a[0]+a[1])%r
    print(a[i%2],i+1)
fibonacci(10,9)