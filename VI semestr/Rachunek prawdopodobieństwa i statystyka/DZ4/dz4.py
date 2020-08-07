import pyexcel as pe
import math
import csv
import numpy as np

d_cases_death = {}
countries = []
sheet = pe.get_book(file_name="dane0512.ods")
sheet=sheet[0]
counter = 0
country_num = 1

while counter!=20:
   country_name = sheet[0,country_num]
   countries.append(country_name)
   cases = int(sheet[3,country_num])
   deaths = int(sheet[3,country_num+1])
   d_cases_death[country_name] = (cases,deaths) 
   counter+=1
   country_num+=2

    
d_population = {}
with open('CountriesPopulation.csv') as f_obj:
   reader = csv.reader(f_obj, delimiter=',')
   for line in reader:
      if line[0]=='Age':
         for i in range(len(line)):
            if i == 0: 
               continue
            d_population[line[i]] = []
         continue
      for i in range(len(line)):
         if i == 0: 
            continue
         d_population[countries[i-1]].append(int(line[i]))

X=[]
for i in d_population:
   d_population[i].insert(0,1)
   X.append(d_population[i])

Y_cases = []
Y_deaths = []



for i in d_population:
   Y_cases.append(d_cases_death[i][0])
   Y_deaths.append(d_cases_death[i][1])

   
   
Y_cases = np.array(Y_cases,dtype=np.float64)
Y_deaths = np.array(Y_deaths,dtype=np.float64)
X = np.array(X,dtype=np.float64)


Xt = X.transpose()
XtX = Xt.dot(X)
XtXinv = np.linalg.inv(XtX)
XtXinvXt = XtXinv.dot(Xt)
Bc = XtXinvXt.dot(Y_cases)
Bd = XtXinvXt.dot(Y_deaths)

#print(np.around(Bc, decimals=3))
for num in Bc:
   print(round(float(num),4))
print()
for num in Bd:
   print(round(float(num),4))
#print(np.around(Bd, decimals=3))



