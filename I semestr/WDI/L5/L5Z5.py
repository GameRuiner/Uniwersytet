from wdi import *
def fTrec(n,m):
    if not m: return n
    if not n: return m
    return fTrec(n-1,m) + 2*fTrec(n,m-1)

def fTrec2(n,m):
    T=Array(n+1)
    for i in range(n+1):
        T[i]=i
    for j in range(1,m+1):
        T[0]=j
        for k in range(1,n+1):
            T[k]=T[k]*2+T[k-1]
    return T[n]



print (fTrec(3,4))
print (fTrec2(3,4))