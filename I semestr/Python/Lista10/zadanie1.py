import kwadrat

kwadrat.tracer(0,10)
'''
kolonia_txt = """
...............................
......*........................
...............................
....***.....############.......
......*........................
..........**...................
...###.....*...................
...#.......*....###.....###....
....#......*......###..........
...............................
"""

'''
R = []
B = []
T = []
for x in open('czerwony.txt'):
  x = x.strip()
  x = x.split()
  R.append(x)

for x in open('niebieski.txt'):
  x = x.strip()
  x = x.split()
  B.append(x)


for i in range(15):
  T.append(str(B[i]) + str(R[i]))

def rysuj(T):
  kwadrat.clear()
  for y in range(len(T)):
    for x in range(len(T[y])):
      if T[y][x] == '#':
        kolor = 'blue'
      elif T[y][x] == '*':
        kolor = 'red'  
      else:
        kolor = 'black'
      kwadrat.kwadrat(x, y, kolor)
  kwadrat.update()    

def sasiedzi(x,y):
  wynik = []
  for dx,dy in [ (-1,-1), (-1, 1), (1,-1), (1,1), (0,-1), (0,1), (1,0), (-1,0)]:
    nx = (x + dx) % MX
    ny = (y + dy) % MY
    wynik.append( (nx, ny) )
  return wynik
      
MY = len(T)    
MX = len(T[0])
    
def pusta_tablica():
  return [ MX * ['.'] for i in range(MY)]

def nowe_pokolenie(T):
  P = pusta_tablica()
  R = 0
  B = 0
  for y in range(MY):
    for x in range(MX):
      liczba_sasiadowB = 0
      liczba_sasiadowR = 0
      for nx,ny in sasiedzi(x,y):
        if T[ny][nx] == '#':
          liczba_sasiadowB += 1
        if T[ny][nx] == '*':
          liczba_sasiadowR += 1
      if liczba_sasiadowB > liczba_sasiadowR:
          liczba_sasiadow = liczba_sasiadowB 
          if T[y][x] == '#' and liczba_sasiadow in [2,3]:
            P[y][x] = '#'
            B+=1
          elif T[y][x] == '.' and liczba_sasiadow == 3:
            P[y][x] = '#'
            B+=1
      if liczba_sasiadowB < liczba_sasiadowR:
          liczba_sasiadow = liczba_sasiadowR 
          if T[y][x] == '*' and liczba_sasiadow in [2,3]:
            P[y][x] = '*'
            R+=1
          elif T[y][x] == '.' and liczba_sasiadow == 3:
            P[y][x] = '*'
            R+=1
  if not R:
    rysuj(P)
    return True
  if not B:
    rysuj(P)
    return False
  return P 

licznik = 0
historia = set()  
  
while True:
  licznik += 1
  rysuj(T)
  T = nowe_pokolenie(T)
  if T == True:
    print('Radiant win')
    break
  if T == False:
    print('Dire win')
    break
  if str(T) in historia:
    print ('koniec:', licznik)
    break
  historia.add(str(T))  
