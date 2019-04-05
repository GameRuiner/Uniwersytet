from math import sqrt
def isPrime(n):
    if n < 2: return False
    for i in range(2,int(sqrt(n))):
        if(n%i)==0:
            return False  
    return True


def hyperLucky(n,m):
    difference=n-m
    maxLucky=int(difference*'9'+m*'7')
    while(True):
        if isPrime(maxLucky): return(maxLucky)
        else: maxLucky-= 10**(m)
        if maxLucky<int('7'*m): return False

print(hyperLucky(10,7))

