num={261,263,281,322,324,243,347,378,380}
title = ''
c = 0
t = ''
ct = 0
b = True
for x in open('lalka.txt'):
    x = x.strip()
    L = x.split()
    for e in L:
        b = True
        for k in e:
            if ord(k) in num:
                if ct>c:
                    c = ct
                    title = t
                    ct = 0
                    t = ''
                    
                b = False
        if b:
            
          ct+=1
          t+=' '+e
print(title)
