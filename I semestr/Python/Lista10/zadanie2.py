import random

def conundrum(s):
    L=s.split()
    a = L[0]
    b = L[2]
    c = L[4]
    d = {}
    nums = set()
    m = set()
    for i in range(0,5,2):
        for e in L[i]:
            m.add(e)
    num=[i for i in range(10)]        
    if len(c) > max(len(a),len(b)):
        d[c[0]]=1
        m.remove(c[0])
        num.remove(1)
    ppp=0
    while True:
        na=nb=nc=0
        random.shuffle(num)
        if tuple(num) in nums:
            continue
        ppp+=1
        nums.add(tuple(num))
        k=0
        for i in m:
            d[i]=num[k]
            k+=1
        k=0
        for i in range(len(a)-1,-1,-1):
            na+=d[a[i]]*(10**k)
            k+=1
        k=0
        for i in range(len(b)-1,-1,-1):
            nb+=d[b[i]]*(10**k)
            k+=1
        k=0
        for i in range(len(c)-1,-1,-1):
            nc+=d[c[i]]*(10**k)
            k+=1    
        if na+nb==nc:
            print(len(nums))
            return d
        if ppp==300000:
            return None
            
        
    return d


print(conundrum("send + more = money"))
print(conundrum("ciacho + ciacho = nadwaga"))
