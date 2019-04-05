

def bigWords():
    L=[]
    m=0
    Lm=[]
    #for x in open('13.txt'):
    #for x in open('100.txt'):
    for x in open('20.txt'):
        L.append(x.split())
    for i in range(len(L)):
        for j in range(len(L[i])):
             word=L[i][j]
             if goodW(word):
                word = goodW(word)
             else: continue   
             if len(word)==m:  
                Lm.append(len(word))
             if len(word)>m:
                 m=len(word)
                 Lm.clear()
                 Lm.append(word)
    Lm=sorted(Lm)
    print(Lm)

num={260,262,280,321,323,211,346,377,379,261,263,281,322,324,243,347,378,380}
for i in range(65,91):
    num.add(i)
for i in range(97,122):
    num.add(i)
def goodW(s):
    if len(s)==1: return False
    while ord(s[len(s)-1]) not in num:
        s=s[:-1]
        if len(s)==1: return False
    return s
bigWords()
