def a(L,w):
    if len(L) == 1:
        w.add(L[0])
    w.add(sum(L))    
    for e in L:
       L.remove(e)
       w.add(sum(L))
       L.append(e)
    for e in L:
        K=L[:]
        K.remove(e)
        a(K,w)
    return w


wynik = set()
L=[1,2,3,100]
print(a(L,wynik))
