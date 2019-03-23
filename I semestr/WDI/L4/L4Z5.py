from wdi import *

def k(n):
    s=str(n)
    m=set()
    for i in range(len(s)):
        m.add(int(s[i]))
    return len(m)

print(k(3435339))
