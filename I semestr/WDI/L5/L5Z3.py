def gcd2(n,m):
    wynik=1
    while m:
        print(n,m)
        if n<m:
            n=n-m
            m=m+n
            n=m-n
            continue
        ilenp=n%2 + m%2
        if ilenp==2: n=n-m
        elif ilenp==0:
            wynik=wynik*2
            n=n//2
            m=m//2
        elif n%2==0: n=n//2
        elif m%2==0: m=m//2
    return wynik*n

def gcd(n,m):
    if (m==0): return n
    if (n<m): return gcd(m,n)
    ilenp = n%2 + m%2
    if (ilenp==2): return gcd(n-m,m)
    if (ilenp==0): return 2*gcd(n/2,m/2)
    if (n%2==0): return gcd(n/2,m)
    else: return gcd(n,m/2)


print(gcd2(30,42))