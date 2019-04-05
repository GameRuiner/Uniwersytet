from math import sqrt
def isPrime(n):
    if n < 2: return False
    for i in range(2,int(sqrt(n))):
        if(n%i)==0:
            return False  
    return True

def hyperLucky(n,m):
    d=n-m
    lucker='7'*m
    maxn='9'*d+lucker
    array=[]
    for i in range(d):
        array.append(9)
    for i in range(d):
        array[i]=lucker 
        number = int('9'*(d-i)+lucker+'9'*i)
        print(array)



print(hyperLucky(10,7))

