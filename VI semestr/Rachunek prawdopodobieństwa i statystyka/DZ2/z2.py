import pyexcel as pe
import math

sheet = pe.get_book(file_name="dane0428.ods")
sheet=sheet[0]
start_day = [(1,39),(3,34),(5,38),(7,40),(9,39),(11,58),
(13,57),(15,47),(17,40),(19,40),(21,52),(23,47),(25,39),
(27,39),(29,41),(31,48),(33,47),(35,41),(37,39),(39,47),
(41,51),(43,48),(45,34),(48,92)]
start_day = []
country_num = 1
country = sheet[0,country_num]

while country != '':
   first_d = 0;
   i = 5
   while sheet[i,country_num] == '':
      i+=1
   first_c = i
   while sheet[i,country_num+1] == '' and i < 99:
      i+=1
   first_d = i
   start_day.append((country_num,(first_c+first_d)//2))
   country_num += 2
   country = sheet[0,country_num]

i = 0
last_day = 99
for i in range(len(start_day)):
	print("Dla panstwa", sheet[0,start_day[i][0]], 
	      "z dnia", sheet[start_day[i][1],0] )
	columna = start_day[i][0]
	wiersz = start_day[i][1]
	count = last_day - wiersz
	C_sum = 0
	D_sum = 0
	
	for j in range(count):
		Ci = (0 if sheet[wiersz,columna] == "" 
             else int(sheet[wiersz,columna])) 
		Di = (0 if sheet[wiersz,columna+1] == "" 
             else int(sheet[wiersz,columna+1]))
		C_sum+= Ci
		D_sum+= Di
		wiersz+=1
	if D_sum == 0:
		print("Wspolczynnik korelacji nie da sie policzyc", 0)
		continue
	wiersz = start_day[i][1]
	C_avg = C_sum/count
	D_avg = D_sum/count
	C_ssd_sum = 0
	D_ssd_sum = 0 	
	
	for j in range(count):
		Ci = (0 if sheet[wiersz,columna] == "" 
             else int(sheet[wiersz,columna]))
		Di = (0 if sheet[wiersz,columna+1] == "" 
             else int(sheet[wiersz,columna+1]))
		C_ssd_sum += pow((Ci - C_avg),2)
		D_ssd_sum += pow((Di - D_avg),2)
		wiersz+=1
	wiersz = start_day[i][1]
	C_ssd = math.sqrt(C_ssd_sum/(count-1))
	D_ssd = math.sqrt(D_ssd_sum/(count-1))
	coeff_sum = 0
	
	for j in range(count):
	   Ci = (0 if sheet[wiersz,columna] == "" 
             else int(sheet[wiersz,columna])) 
	   Di = (0 if sheet[wiersz,columna+1] == "" 
             else int(sheet[wiersz,columna+1]))
	   coeff_sum+= ((Ci - C_avg)/C_ssd)*((Di - D_avg)/D_ssd)
	   wiersz+=1

	coeff = 1/(count-1)*coeff_sum
	print("Wspolczynnik korelacji", coeff)

	