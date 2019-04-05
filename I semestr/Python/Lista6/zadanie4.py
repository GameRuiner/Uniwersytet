def sp(s):
    result=[]
    word=''
    for i in s:
        if i == ' ':
            if len(word)>0:
                result.append(word)
                word=''
        else:
            word=word+i
    return result

s=" Ala   ma    kota  "
print(sp(s))

