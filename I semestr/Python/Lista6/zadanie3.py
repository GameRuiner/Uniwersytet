from math import sqrt

def dividers(n):
    divs=set()
    for i in range(2,int(sqrt(n))+1):
        if n%i==0 and isPrime(i): divs.add(i)
    return divs

def isPrime(n):
    if n < 2: return False
    for i in range(2,int(sqrt(n))+1):
        if n%i==0: return False
    return True

print(dividers(5532))
