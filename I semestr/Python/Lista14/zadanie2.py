odl = {}
miasta = []
for x in open('miejscowosci.txt'):
    x = x.strip()
    miasta.append(x)    
for x in open('plik.txt'):
    x = x.strip()
    L = x.split()
    n=0
    miasto1 = ''
    miasto2 = ''
    if len(L) > 3:
        for i in miasta:
            if i in x: break
        L2 = i.split()
        for j in range(len(L)):
            if L[j] == L2[0]: break
        if j==0:
            miasto1 = ' '.join(L2)
            n=len(L2)
            while n < len(L):
                miasto2+=L[n]
                n+=1
        else:
            
                

