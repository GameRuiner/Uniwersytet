import time
def deszyfr(s):
    print(s)
    L = s.split()
    L2 = []
    cursor = 0
    unknown = []
    for i in L:
        if i.isalpha():
            L2.append(i)
    letters = {}
    result = L2[:]
    chars = {}
    #test = ppn('lęn')
    #print(dic[test])
    for i in L2:
       k = ppn(i)
       if len(dic[k])<4:
          moreletters(dic[k],letters,chars,i)
          #print(letters)
    for i in L2:
       k = ppn(i)
       #print(dic[k])
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
           history = set()
           word = deszyfr2(L,i,letters,chars,history)
           if word == None:
              unknown.append((i,cursor))   
           else:
               for j in range(len(i)):
                  letters[i[j]]= word[j]
                  chars[word[j]] = i[j]
               result[cursor] = word
           cursor+=1
    while len(unknown)>0:
            if len(letters)==0:
                return deszyfr3(L2,letters,chars)
            for i in unknown:
                k = ppn(i[0])
            L = []
            for w in dic[k]:
                   L.append(w)
            history = set()
            #print(unknown)
            word = deszyfr2(L,i[0],letters,chars,history)
            if word != None:
                unknown.remove(i)
                for j in range(len(i[0])):
                      letters[i[0][j]]= word[j]
                      chars[word[j]] = i[0][j]
            result[i[1]] = word
    #print(letters)
    #print(chars)
    return result
                          
def deszyfr2(L,i,lett,chars,history):
    #print(i)
    #time.sleep(0.5)
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
    if len(L2)<=5 and len(L2)>0:
      boo = True
      for el in range(len(L2)):
          if L2[el]!=L2[0]: boo = False
      if boo: return L2[0]
      moreletters(L2,lett,chars,i)
      #print(L2)
      
    #print(len(L2))
    if tuple(L2) in history: return None
    history.add(tuple(L2))
    #print(history)
    return deszyfr2(L2,i,letters,chars,history)

def deszyfr3(L,letters,chars):
    L8 = []
    for i in L:
        p=ppn(i)
        n=len(dic[p])
        L8.append((n,i))
    L8.sort()
    i = L8[0][1]
    for j in dic[ppn(i)]:
        print(j)
        res2 = []
        letters={}
        for ch in range(len(j)):
            letters[i[ch]] = j[ch]
            chars[j[ch]] = i[ch]
        for word in L8[1:]:
            
            print(word[1])
            k=ppn(word[1])
            #print(k)
            L7=[]
            for m in dic[k]:
                L7.append(m)
            #print(L7[0])
            res = deszyfr2(L7,word[1],letters,chars,set())
            if res==None:
                break
            print(res)
            res2.append(res)
            if word==L8[len(L8)-1]:
                return res2
    
    
def moreletters(L,letters,chars,i):
    for j in range(len(L[0])):
             v=True
             for m in range(1,len(L)):
                if L[0][j] != L[m][j]: v=False
             if v: 
                 letters[i[j]] = L[0][j] 
                 chars[L[0][j]] = i[j]
    
    #print(letters)                    
                          
                                  
                          
                              
                          
                          
def ppn(word):
    chars = {}
    c = 1
    word = word.lower()
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
   result = deszyfr(x)
   print(result)
   for j in result:
        print(j,end=' ')
   print()
   print()
