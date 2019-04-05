def b(N,A,B):
    if A-B == 0: return(A)
    k = [ i for i in range(A,B+1)]
    L = [B]*N
    result.append(L)
    
    
    return result

result = []
print (b(3,1,5))
