import pyexcel as pe
import math
import csv

sheet = pe.get_book(file_name="RUSUSA.ods")
sheet=sheet[0]

last_day = 122 #ostatni dzień 122

e_growths = [] # Tu znajduje się informacja dla obliczeń
               # nazwa kolumny, numer kolumny i numer wiersza z którego zaczynamy obliczenia
rus_cases = ['Rosja. Zachorowania', 9,52]
rus_deaths = ['Rosja. Zgony', 10,72]
usa_cases = ['Stany Zjednoczone. Zachorowania', 11, 18]
usa_deaths = ['Stany Zjednoczone. Zgony',12, 46]
e_growths.append(rus_cases)
e_growths.append(rus_deaths)
e_growths.append(usa_cases)
e_growths.append(usa_deaths)

f = open('output.txt', 'w')

for e in e_growths:
   print(e[0])
   f.write(e[0] + '\n')
   start_day = e[2]
   column = e[1]
   
   for i in range(start_day,last_day - 2):
      last_sum = sheet[i,column] # x_{t-1}
      next_sum = sheet[i+1,column] # x_{t)
      if last_sum == next_sum: # w tym przypadku dNd wychodzi 0
         continue              # i nie chcielibyśmy delić przez 0
      dNd = next_sum - last_sum # \delta x_{t-1}
      dNd1 = sheet[i+2,column] - next_sum # \delta x_{t}
      growth_factor = dNd1 / dNd # k - współczynnik wzrostu
      print(growth_factor, sheet[i+2,0]) # wypisujemy współczynnik i dzień
      #f.write('['+str(round(growth_factor,3)) + ',' + str(sheet[i+2,0])[5:] +'], ')
      f.write(str(round(growth_factor,3)) +', ')
      if i%10 == 0:
         f.write('\n')
   f.write('\n')
      