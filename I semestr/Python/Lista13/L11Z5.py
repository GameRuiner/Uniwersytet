def deszyfr(s):
    print(s)
    L = s.split()
    L2 = []
    history = set()
    cursor = 0
    unknown = []
    for i in L:
        if i.isalpha():
            L2.append(i)
    letters = {}
    result = L2[:]
    chars = {}
    for i in L2:
       k = ppn(i)
       print(dic[k])
       if len(dic[k])==1:
           word = dic[k][0]
           for j in range(len(i)):
               letters[i[j]]= word[j]
               chars[word[j]] = i[j]
           result[cursor] = word
           cursor+=1
       else:
           L=[]
           for w in dic[k]:
               L.append(w)
           word = deszyfr2(L,i,letters,chars,history)
           if word == None:
              unknown.append((i,cursor))   
           else:
               for j in range(len(i)):
                  letters[i[j]]= word[j]
                  chars[word[j]] = i[j]
               result[cursor] = word
           cursor+=1
    for i in unknown:
        k = ppn(i[0])
        for w in dic[k]:
               L.append(w)
        word = deszyfr2(L,i[0],letters,chars,history)
        if word != None:
            for j in range(len(i[0])):
                  letters[i[0][j]]= word[j]
                  chars[word[j]] = i[0][j]
        result[i[1]] = word
    print(letters)
    print(chars)
    return result
                          
def deszyfr2(L,i,lett,chars,history):
    if len(L) == 1: return L[0]
    L2 = []
    letters = lett.copy()
    for j in range(len(i)):
        if i[j] in letters:   
            for word in L:
                if word[j]==letters[i[j]]:
                    L2.append(word)
            del letters[i[j]]
            return deszyfr2(L2,i,letters,chars,history)
    for word in L:
        b=True
        for ch in range(len(word)):
            if word[ch] in chars and chars[word[ch]]!=i[ch]:
                b=False
        if b: L2.append(word)
    #print(L2)
    if tuple(L2) in history: return None
    history.add(tuple(L2))
    return deszyfr2(L2,i,letters,chars,history)
                    
                          
                                  
                          
                              
                          
                          
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
    x = x.lower()
    k = ppn(x)
    if k not in dic:
        dic[k] = []
        dic[k].append(x)
    else:
        dic[k].append(x)
       
for x in open('szyfry'):
   x = x.strip()
   result = deszyfr(x)
   for j in result:
        print(j,end=' ')
   print()
