def kratka(N,napis):
    k = N - len(napis)
    if k>0: napis+=k*' '
    for i in range(N):
        if i%2==0: print('#'*N)
        else:
            for j in range(N):
                if j%2==0: print('#',end='')
                else: print(napis[(j-1)//2],end='')
            print()

kratka(11,'lol')
    
