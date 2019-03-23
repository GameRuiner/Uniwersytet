def LCM(a,b):
    return a*b/euclid(a,b)

def euclid(a,b):
    if b==0:
        return a
    else:
        return euclid(b, a % b)
def b(a,b):
    x=euclid(a,b)
    print(int(a/x),'/',int(b/x),sep='')
(b(6,8))