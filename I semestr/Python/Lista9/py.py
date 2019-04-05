for x in open('slowa.txt'):
    x = x.strip()
    xl = set()
    b=0
    for i in x:
        if i in xl:
            b=1
        xl.add(i)
        if b: break
    if b:continue
    if 'ń' in x and 'f' in x and 'h' in x:
        print(x)
print('koniec')

