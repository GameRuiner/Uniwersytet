from random import randint

def dragonGameV1():
    a = randint (1,6)
    b = randint (1,6)
    firstResult = a + b
    counter = 1
    trows = 1
    while True:
         a = randint (1,6)
         b = randint (1,6)
         result = a + b
         trows += 1
         if result > firstResult:
             counter += 1
         else:
             counter = 0

         firstResult = result
             
         if counter == 5:
             return "You won"
            
         if trows == 100:
            return "You lost"

def dragonGameV2():
    a = randint (1,6)
    b = randint (1,6)
    firstResult = a + b
    counter = 1
    trows = 1
    while True:
         a = randint (1,6)
         b = randint (1,6)
         result = a + b
         trows += 1
         if result >= firstResult:
             counter += 1
         else:
             counter = 0

         firstResult = result
             
         if counter == 6:
             return "You won"
            
         if trows == 100:
            return "You lost"
        
for i in range (100):
   print(dragonGameV1())

print("Second game")

for i in range (100):
   print(dragonGameV2())
