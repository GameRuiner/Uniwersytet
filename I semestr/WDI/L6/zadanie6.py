from wdi import *

def erat(n,m):
    ten=10000
    print(m-n)
    A = Array(ten)
    B = Array(ten)
    C = Array(ten)
    for i in range(2,ten):
        j=n//i+1
        while j*i<m:
            C[j*i-n]=1
            j+=1
    for i in range(ten,m//2+1,ten):
        for i in range(i,i+ten):
            j=1
            while j*i<n:
                j+=1
            if j*i>m: continue
            while j*i<=m and j*i>=n:
                if i==17: print(i*j)
                C[j*i-n]=1
                j+=1
    for i in range(ten-1):
        if C[i]==0:print(C[i],i+n)

erat(900000,909999)