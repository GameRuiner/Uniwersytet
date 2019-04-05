#https://skos.ii.uni.wroc.pl/mod/page/view.php?id=5622

def word(v):
    words = set()
    for x in open('slowa.txt'):
        x = x.strip()
        if v in x:
            words.add(x)
    return words

def mults(x,y,z):
    a = word(x)
    b = word(y)
    c = word(z)
    #Każde
    m1 = a & b & c

    #Co najmniej jedno
    m2 = a | b | c
    
    #Co dokadnie jedno
    m3 = a ^ b ^ c

    #x ale nie z
    m4 = a - c
    
    #x i y
    m5 = a & b
    return m1,m2,m3,m4,m5

mults('ala','ma','kota')
    
    
        
