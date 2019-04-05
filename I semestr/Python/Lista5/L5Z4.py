import time
def only_one1(L):
    numbers=[]
    position=[]
    for i in range(len(L)):
        numbers.append([L[i],i])
    numbers = sorted(numbers)
    numbers2=[]
    numbers2.append(numbers[0])
    for i in range(1,len(numbers)):
        if numbers2[len(numbers2)-1][0]==numbers[i][0]:
            continue
        numbers2.append(numbers[i])
    result=(len(numbers2))*[0]
    for i in range(len(numbers2)):
        n=numbers2[i][0]
        n2=numbers2[i][1]
        result[i]=[n2,n]
    result=sorted(result)
    lastResult=[]
    for i in  range(len(result)):
        lastResult.append(result[i][1])
    return lastResult

def only_one2(L):
    wynik=[]
    for e in L:
        if e in wynik:
            continue
        else:
            wynik.append(e)
    return wynik

L1=[1,2,3,1,2,3,8,2,2,2,9,9,4]
L2=[0]*100000
L3=list(range(10000,0,-1))
for i in range(3):
    t0=time.time()
    only_one1(L1)
    t1=time.time()
    first=t1-t0

    t0=time.time()
    only_one2(L1)
    t1=time.time()
    second=t1-t0

    print('L1 first',first)
    print('L1 second',second)

for i in range(3):
    t0=time.time()
    only_one1(L2)
    t1=time.time()
    first=t1-t0

    t0=time.time()
    only_one2(L2)
    t1=time.time()
    second=t1-t0

    print('L2 first',first)
    print('L2 second',second)

for i in range(3):
    t0=time.time()
    only_one1(L3)
    t1=time.time()
    first=t1-t0

    t0=time.time()
    only_one2(L3)
    t1=time.time()
    second=t1-t0

    print('L3 first',first) 
    print('L3 second',second)
