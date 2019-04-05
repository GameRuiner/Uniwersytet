#wiersze_pliku = open('/home/prych/data/2grams_cleaned').readlines()
def enigma(s):
    s=s.lower()
    result=[]
    b=True
    charsS = {}
    for i in s:
        if i==' ': continue 
        if i not in charsS:
            charsS[i]=1
        else:
            charsS[i]+=1
    copy = charsS.copy()
    while b:
        for x in open('slowa.txt'):
            x = x.strip()
            copy = charsS.copy()
            if len(x) == 1: continue
            k=True
            if not b: break
            for i in range(len(x)):
                if not k: continue
                c=x[i]
                if c not in copy:
                    k=False
                    continue
                if copy[c] == 1:
                    del copy[c]
                else:
                    if copy[c] >1: copy[c]-=1
                if i == len(x)-1:
                    oneMoreV = copy.copy()
                    if len(oneMoreV)>3 or len(oneMoreV)==0 : 
                        charsS = copy.copy()
                        result.append(x)
                if len(charsS)==0:
                    b=False
    
                    
    return ' '.join(result)
                
                

print(enigma('Marko Golowko'))

print(enigma('Kurt Cobain'))

            
    
    
            
    
