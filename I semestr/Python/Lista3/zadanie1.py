def isPrime(n):
    if n < 2: return False
    for i in range(2,n//2+1):
        if(n%i)==0:
            return False     
    return True

def isLucky(n):
    if n < 777: return False
    string=str(n)
    for i in range(len(string)):
        a=i+1
        b=i+2
        if b==len(string): return False
        if(string[i] == string[a] == string[b] == '7'):
            return True
    return False




counter = 0
for i in range(1,100000):
    if(isLucky(i) and isPrime(i)):
        counter+=1
        print(i)    

print(counter)
