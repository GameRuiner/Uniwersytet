from wdi import *

def isPal(A):
    a=0
    b=len(A)-1
    while a<=b:
        print(a,b)
        print(A[a], A[b])
        if A[a] != A[b]: return False
        a+=1
        b-=1
    return True
A=Array(3)
A[0]=1
A[1]=0
A[2]=1
B=Array(6)
B[0]=1
B[1]=1
B[2]=0
B[3]=0
B[4]=1
B[5]=1
C=Array(6)
C[0]=1
C[1]=0
C[2]=1
C[3]=1
C[4]=0
C[5]=1
print(isPal(A),isPal(B),isPal(C))
#print(isPal(C))