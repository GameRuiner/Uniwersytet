
def reversible():
    se=set()
    re=set()
    for x in open('slowa.txt'):
        x = x.strip()
        se.add(x)
    for x in se:
        nX=rev(x)
        if (nX in se) and (nX not in re) and (x not in re):
            re.add(nX)
    return re

def rev(s):
    L=list(s)
    L.reverse()
    s=''.join(L)
    return s
    
result=[]
result=list(reversible())
c=0
for i in range(len(result)):
    print(result[i],' ',rev(result[i]))
    c+=1
print(c)
