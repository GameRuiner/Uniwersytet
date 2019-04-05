import random
slowa = {}
slwo=''
b=False
for x in open('pan-tadeusz.txt'):
    x.strip()
    L=x.split()

    if len(L)==0: continue
    if b:
        b=False
        slowa[slowo].append(L[0])
    for i in range(len(L)):
              if L[i] not in slowa:
                 slowa[L[i]] = []
              if i+1<len(L):
                 word=L[i+1]
                 slowa[L[i]].append(word)
              else:
                  slowo=L[i]
                  b=True

def SAM(word,n):
    print(word,end=' ')
    if n>0:
       L = slowa[word]
       i = random.randint(0,len(L)-1)
       SAM(L[i],n-1)


SAM('Pan',200)
              
              
              
