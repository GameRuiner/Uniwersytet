def goodwords(s):
    print(s)
    print()
    words = {}
    s=s.lower()
    length = len(s)-2
    short = 1
    dS = {}
    for i in s:
        if i == " ": continue
        if i not in dS: dS[i]=1
        else: dS[i]+=1
    for x in open('slowa.txt'):
        x = x.strip()
        if len(x)>length: continue
        for i in x:
            b=False
            if i not in dS:
                b=True
                break
        if b: continue
        if len(x) not in words:
            words[len(x)] = []
            words[len(x)].append(x)
        else:
            words[len(x)].append(x)
    enigma(words,dS,length,short)
    	

def enigma(words,ds,l,s):
    u = set()
    while s <= l:
        a=words[s]
        if l not in words:
            l-=1
            s+=1
            continue
        b=words[l]
        for sw in a:
            for lw in b:
                d=di(sw,lw)
                if d==ds and ((sw+' '+lw or lw+' '+sw) not in u):
                    u.add(sw+' '+lw)
                    u.add(lw+' '+sw)
                    print(sw,lw)
        s+=1
        l-=1
    print()
        
		
def di(s1,s2):
    s=s1+s2
    d = {}
    for i in s:
        if i not in d: d[i]=1
        else: d[i]+=1
    return d
                    

goodwords('Marko Golowko')

goodwords('Kurt Cobain')

goodwords('Marilyn Monroe')

            
    
    
            
    
