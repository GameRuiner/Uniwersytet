from wdi import *
def factorial(n):
    c=1
    f=1
    kk=0
    while f*c+1<n:
        f*=c+1
        c+=1
        kk+=1
    while c>0:
        r=n//f
        print (r,n,f)
        n=n-r*f
        f=f//(c)
        c-=1
        kk += 1
    return kk

print(factorial(2**16))
A = Array(100)
