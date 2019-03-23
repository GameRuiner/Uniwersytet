def newUlamek(a,b):
    g = gcd(a,b)
    if g > 1:
        a = a//g
        b = b//g
    if a<0 and b<0:
        a = abs(a)
        b = abs(b)
    if b<0:
        b = abs(b)
        a = -1*a
    return [a,b]

def show(u):
    if u[1]!=1:
        print(str(u[0])+'/'+str(u[1]))
    else:
        print(u[0])

def gcd(a,b):
    a=abs(a)
    b=abs(b)
    while a!=0 and b!=0:
        if a>b:
            a = a % b
        else:
            b = b % a
    return a+b

def lcm(a,b):
   a=abs(a)
   b=abs(b)
   return abs(a*b)//gcd(a,b)

def plus(a,b):
    k = lcm(a[1],b[1])
    c = a[0]*(k//a[1])+b[0]*(k//b[1])
    d = k
    return newUlamek(c,d)

def minus(a,b):
    k = lcm(a[1],b[1])
    c = a[0]*(k//a[1])-b[0]*(k//b[1])
    d = k
    return newUlamek(c,d)

def mult(a,b):
    return newUlamek(a[0]*b[0],a[1]*b[1])

def div(a,b):
    return newUlamek(a[0]*b[1],a[1]*b[0])

