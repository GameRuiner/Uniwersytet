import random

def tlumacz(txt):
  wynik = []
  L=[]
  for p in txt.split():
    if p in pol_ang:
      if len(pol_ang[p])>1:
          for i in range(len(pol_ang[p])):
              if pol_ang[p][i] not in angC:
                  c=counter(pol_ang[p][i])
              L.append([c,pol_ang[p][i]])
          L.sort(reverse = True)
          wynik.append(L[0][1])
          L.clear()
    else:
      wynik.append('?' + p)
  return ' '.join(wynik)


def counter(word):
    count=0
    for x in open('brown.txt'):
      x = x.strip()
      L = x.split()
      for i in range(len(L)):
          if L[i]==word:
              count+=1
    return count

      
pol_ang = {} # pusty słownik
angC = {}
for x in open('pol_ang.txt'):
  x = x.strip()
  L = x.split('=')
  if len(L) != 2:
    continue
  pol, ang = L
  
  if pol not in pol_ang:
    pol_ang[pol] = [ang]
  else:  
    pol_ang[pol].append(ang)

zdanie = 'dziewczyna spotkać chłopiec i pójść do kino uerthf'

print (tlumacz(zdanie))
