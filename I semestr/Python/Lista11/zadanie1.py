Alf = list('aąbcćdeęfghijklłmnńoóprsśtuwyzźż')
shift = {}
for i in range(len(Alf)):
    shift[Alf[i]] = []
    for j in range(len(Alf)):
        shift[Alf[i]].append(Alf[(j+i)%32])
print(shift)
def ceasar(s,k):
    word = ''
    for ch in s:
        c = shift[ch][k]
        word+= c
    return word

print(ceasar('cćdeęfghijklłmnńoóprsśtuwyzźżaąb',29))
    
