def fTrec(n, m):
    if not m: return n
    if not n: return m
    return fTrec(n-1,m)+2*fTrec(n,m-1)

print(fTrec(3,4))