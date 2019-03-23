from wdi import *

def U2(n):
    u2 = Array(24)
    for i in  range(24):
        u2[i]=0
    i=23
    while n > 0:
        x=n%2
        u2[i]=x
        i-=1
        n = n // 2
    for i in range(24):
        print(u2[i],end=' ')
    print()
    return u2

def toU2(n):
    if n > -1:
        return U2(n)
    else:
        x = U2(-n - 1)
        pos = Array(24)
        for i in range(24):
            if x[i] == 1: pos[i]=2
        result = Array(24)
        for i in range(24):
            result[i]=1
        for i in range(24):
            if pos[i]==2: result[i]=0
        for i in range(24):
            print(result[i], end=' ')
        return result

print(U2(100))
