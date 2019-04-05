def deszyfr(s):
    L = s.split()
    L2 = []
    for i in L:
        if i.isalpha():
            L2.append(i)
    letters = {}
    chars = {}
    for i in L2:
       k = ppn(i)
       if len(dic[k])==1:
           word = dic[k][0]
           for j in range(len(i)):
               letters[i[j]]= word[j]
               chars[word[j]] = i[j]
           print(word,end=' ')
       else:
           L=[]
           for w in dic[k]:
               L.append(w)
           word = deszyfr2(L,i,letters,chars)
           print(word,end=' ')
    print()
                          
                      
def deszyfr2(L,i,letters,chars):
    for c in range(len(i)):
              ch = L[0][c]
              for j in range (len(L)):
                  word = L[j]
                  if word[c]!=ch:
                      if i[c] in letters:
                          ch = letters[i[c]]
                          L2=[]
                          for m in range(len(L)):
                              if L[m][c] == ch:
                                  L2.append(L[m])
                          if len(L2)>1:
                                  return deszyfr2(L2,i,letters,chars)
                          else:
                              return L2[0]
                      else:
                          Chars = set()
                          L2=[]
                          for j in range(len(L)):
                              Chars.add(L[j][c])
                          for chars2 in Chars:
                              if chars2 not in chars:
                                  for j in range(len(L)):
                                      if L[j][c] == chars2:
                                         L2.append(L[j])
                                  if len(L2) > 1:
                                      return deszyfr2(L2,i,letters,chars)
                                  else:
                                      return L2[0]
                          
                                  
                          
                              
                          
                          
def ppn(word):
    chars = {}
    c = 1
    f = word[0]
    result='1'
    chars[f] = c
    word = word[1:]
    for i in word:
        if i in chars:
            result +='-'+str(chars[i])
        else:
            c+=1
            chars[i] = c
            result += '-'+str(chars[i])
    return result

dic = {}
for x in open('slowa.txt'):
    x = x.strip()
    k = ppn(x)
    if k not in dic:
        dic[k] = []
        dic[k].append(x)
    else:
        dic[k].append(x)
       

for x in open('szyfry'):
    x = x.strip()
    deszyfr(x)
