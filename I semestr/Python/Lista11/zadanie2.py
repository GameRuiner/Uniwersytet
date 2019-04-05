Alf = list('aąbcćdeęfghijklłmnńoóprsśtuwyzźż')
shift = {}
for i in range(len(Alf)):
    shift[Alf[i]] = []
    for j in range(len(Alf)):
        shift[Alf[i]].append(Alf[(j+i)%32])

def ceasar(s,k):
    word = ''
    for ch in s:
        c = shift[ch][k]
        word+= c
    return word

words = {}
badChars = {'q','v','x'}
for x in open('slowa.txt'):
        bad = False
        x = x.strip()
        if not (x.isalpha() and x.islower()):
            continue
        for i in badChars:
            if i in x:
               bad = True
               break
        if bad:
            continue
        L = len(x)
        if L not in words:
            words[L] = set()
        words[L].add(x)
L = list(words.keys())
find = False
for i in range(L[len(L)-1],0,-1):
    print(i)
    for word in words[i]:
        for j in range(1,32):
            newWord = ceasar(word,j)
            if newWord in words[i]:
                print(word,newWord)
                find = True
    if find:
        break
                
