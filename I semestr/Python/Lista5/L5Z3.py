def only_one(L):
    wynik=[]
    for e in L:
        if e in wynik:
            continue
        else:
            wynik.append(e)
    return wynik


L=[1,2,3,1,2,3,8,2,2,2,9,9,4]
print(only_one(L))
