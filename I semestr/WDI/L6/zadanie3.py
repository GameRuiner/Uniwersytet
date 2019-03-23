a = [3,8,2,5,7,4,2,8]
print(a)
n=len(a)
i=0
for j in range(n):
  i=0
  while i<n-j-1:
    if a[i]>a[i+1]:
        c=a[i]
        a[i]=a[i+1]
        a[i+1]=c
    i+=1
  print(a)