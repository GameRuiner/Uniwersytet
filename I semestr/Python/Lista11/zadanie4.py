def ppn(word):
    chars = {}
    c=1
    f = word[0]
    print('1',end='')
    chars[f] = c
    word = word[1:]
    for i in word:
        if i in chars:
            print('-',chars[i],end='',sep='')
        else:
            c+=1
            chars[i] = c
            print('-',chars[i],end='',sep='')
    print()
            
ppn('tak')
ppn('nie')
ppn('tata')
ppn('Indianin')
