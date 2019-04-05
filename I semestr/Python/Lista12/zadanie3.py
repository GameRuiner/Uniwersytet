import random
L1 = ['ka','p','ko','w']
L2 = []
def isValid(L1,L2):
    if 'p' in L1:
        return isValid2(L2)
    return isValid2(L1)

def isValid2(L):
    if len(L) == 3: return False
    if len(L) == 0 or len(L) == 1: return True
    if 'ko' in L:  return False
    return True
L11 = L1[:]
L22 = L2[:]
history=''
while len(L22)!=4:
    if 'p' in L11:
        L11.remove('p')
        L22.append('p')
        a = random.randint(0,1)
        if a:
            b = random.randint(0,len(L11)-1)
            L22.append(L11[b])
            L11.remove(L11[b])
    else:
        L22.remove('p')
        L11.append('p')
        a = random.randint(0,1)
        if a:
            b = random.randint(0,len(L22)-1)
            L11.append(L22[b])
            L22.remove(L22[b])
    if not isValid(L11,L22) or len(L22)==0:
        L11 = L1[:]
        L22 = L2[:]
        history=''
    else:
        s=' '.join(L22)
        s+='\n'
        history+=s

print(history)
        
            
        
        
