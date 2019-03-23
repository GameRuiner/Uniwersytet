from wdi import*
a=[5,2,8,1,0,9,7,6,3,4]
n=len(a)
for i in range(n-1):
    j=i
    bb=a[i]
    while j<n:
       if a[j]<a[i]:
           a[i]=a[j]
           a[j]=bb
           bb=a[i]
       j+=1
    print(a)