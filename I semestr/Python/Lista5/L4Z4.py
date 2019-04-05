from random import *

def randperm(n):
    l=list(range(n))
    l=sample(l,k=len(l))
    return l

l=10
for i in range(3):
    print(randperm(l))
n=10 ** 6
x=randperm(n)
print(len(x))




