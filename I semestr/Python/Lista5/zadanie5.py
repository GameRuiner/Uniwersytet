from random import randint,random

def dGV1():
    k = randint (1,6)
    c=1
    t=1
    l=[]
    l.append(k)
    while True:
        k2 = randint(1,6)
        t+=1
        if k2 > k:
            c+=1
            l.append(k2)
        else:
            c=0
            l.clear()
        k = k2
        if t==101:
            return False
        if c==5:
            print(l)
            return True


def dGV2():
    k = randint (1,6)
    c=1
    t=1
    while True:
        k2 = randint(1,6)
        t+=1
        if k2 >= k:
            c+=1    
        else:
            c=0
        k = k2
        if t==101:
            return False
        if c==6:
            return True



    
counter1=0
counter2=0
n=100000
for i in range (n):
      if dGV1():
         counter1+=1
print(counter1/n*100,'%',sep='')

print("Second game")

for i in range (n):
   if dGV2():
       counter2+=1
print(counter2/n*100,'%',sep='')
    
