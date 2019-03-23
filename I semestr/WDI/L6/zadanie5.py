from wdi import *

def eratosfen(n):
    A = Array(n)
    for i in range(2,n):
        j=2
        while i*j<n:
            A[i*j]=1
            j+=1
    for i in range(2,n):
        if A[i]==0: print(i)



eratosfen(100)