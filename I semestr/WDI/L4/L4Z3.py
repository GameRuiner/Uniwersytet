def euclid(a,b):
    if b==0: return a
    else:
        return euclid(b, a % b)
n=int(input())
x=int(input())
for i in range(1,n):
    b=int(input())
    a=euclid(x,b)
    if x>a: x=a
    if x==1: print(x)
if x!=1: print(x)