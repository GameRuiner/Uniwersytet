
def szachowica(n,k):
   empStr = ' '*k
   starStr = '*'*k
   firstStr = (empStr + starStr) * n + '\n'
   secondStr = (starStr + empStr) * n + '\n'
   firstBlock = firstStr * k 
   secondBlock = secondStr * k
   block = firstBlock + secondBlock
   return  block * n

print(szachowica(4,3))

a='3'*10 + '4' * 30
print(a)
