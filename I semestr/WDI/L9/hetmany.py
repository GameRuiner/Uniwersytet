from wdi import*
n=4
b=Array(n)

def init():
   for i in range(n):
      b[i]=-1

def isf(x,y):
   for i in range(x):
      if b[i]-i==y-x or b[i]+i==y+x or b[i]==y:
         return 0
   return 1

def queens(p):
        b[0]=0
        k=1
        while k<n and k>=0:
                b[k]+=1
                while b[k]<n and not isf(k,b[k]):
                        b[k]+=1
                if b[k]<n: k+=1
                else: b[k]=-1; k-=1
        if k==n:
                drawresult()
                #queens(b[0]+1)

def drawresult():
        print('\n')
        for i in range(n):
                print(b[i],end=' ')
        print()
        for i in range(n):
                for j in range(n):
                        if b[j]==i: print('x',end=' ')
                        else: print('o',end=' ')
                print()

def hetmany(n, k, b):
        if k==n:
                return poprawne(b,n)
        for i in range(n):
                b[k]=i
                if (hetmany(n,k+1,b)): return 1
        return 0

def poprawne(a,n):
  for i in range(n):
          if not isf(i,a[i]): return 0
  return 1






def queens2(p,res):
    b[0] = p
    k = 1
    while k < n and k >= 0:
        b[k] += 1
        while b[k] < n and not isf(k, b[k]):
            b[k] += 1
        if b[k] < n:
            k += 1
        else:
            b[k] = -1; k -= 1
    if k == n:
        drawresult()
        queens2(b[0]+1,res+1)
    else:
        print(res)
  
init()
queens2(0,0)