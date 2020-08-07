y = 0
z = 0
for i in range(100):
   i+=1
   print(i)
   if (i%3 == 0):
      z+=1; y+=1
   if i%2 == 0:
      y+=1
   if i%2+i%3 == 0:
      y-=1
print(y,z)