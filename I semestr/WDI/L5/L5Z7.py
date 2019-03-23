def more(n,m):
    k=1
    b=n
    while b<m:
        k+=1
        b=b*n
    return k
print (more(3,678))