def F(n):
    if n % 2 == 0:
        return n / 2
    else:
        return 3 * n + 1

def energiaF(k):
    counter = 0
    while k!=1:
        k=F(k)
        counter+=1
    return counter

def analizaCollatza(a,b):
    l=[]
    sum=0
    for i in range(a,b+1):
        x=energiaF(i)
        l.append(x)
        sum+=x
    l=sorted(l)
    if len(l) % 2 == 1:
        m=l[len(l)//2]
    else:
        m=(l[len(l)//2]+l[len(l)//2-1])/2
    print('Średnia energia',sum/(b-a+1))
    print('Mediana energii',m)
    print('Maksymalna',l[len(l)-1],'Minimalna',l[0])

print('7:',energiaF(7))
    
analizaCollatza(7,10)
