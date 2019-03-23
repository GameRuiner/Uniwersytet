a=[1,4,8,8,34,8]
i=5
x = a[i]
print (x)
l = 0; p =i-1
while l<=p:
    k = (l+p)//2
    if a[k]>x: p=k-1
    else: l=k+1
print (l)