from random import shuffle
words = []
letters = {}
def isnum():
    num={261,263,281,322,324,243,347,380}
    for i in range(97,123):
        if i!=118 and i!=120 and i!=113:
            num.add(i)
    return num

def numvv():
    numv={261,281,243,97,101,105,111,117,121}
    return numv
#smallW= ['w','z']


for x in open('slowa.txt'):
    x = x.strip()
    x = x.lower()
    xl = set()
    b=0
    for i in x:
        if i in xl:
            b=1
        xl.add(i)
        if b: break
    if b:continue
    words.append(x)
    
def alfabeton():
    print('bufońskich')
    num = isnum()
    numv = numvv()
    for i in 'bufońskich':
        num-= {ord(i)}
        numv-= {ord(i)}
    shuffle(words)
    alf=words[:]
    while len(numv)!=0 and len(alf)>0:
        x=alf.pop()
        word=set()
        for i in x:
            if ord(i) not in num:
                break
            word.add(ord(i))
            if i==x[len(x)-1]:
                print(x)
                num-= word
                numv-= word
    if ord('w') in num:
        print('w')
    if ord('z') in num:
        print('z')
    num=num-{ord('w'),ord('z')}
    #return list(num)
    print('letters: ',end=' ')
    for i in num:
        print(chr(i),end=',')
    print('')
          
for i in range(10):
    alfabeton()
    '''
    li=alfabeton()
    for j in li:
        k = chr(j)
        if k not in letters:
            letters[k]=1
        else:
            letters[k]+=1
    '''
#print (list(reversed(sorted(letters, key = letters.get))))
#['ń', 'f', 'ś', 'h', 'ć', 'g', 'b', 'c', 'ż', 'j', 'p', 'l', 'd', 's', 'm', 'n', 't', 'ł', 'k', 'r', 'ó']            
#bufońskich    
    
        
