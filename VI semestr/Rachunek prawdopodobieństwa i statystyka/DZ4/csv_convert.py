import pyexcel as pe
import math
import csv

sheet = pe.get_book(file_name="dane0512.ods")
sheet=sheet[0]
country_num = 1
country = sheet[0,country_num]
counter = 0
country_popul = []
ages = ["Age","0-9","10-19","20-29","30-39","40-49","50-59","60-69","70-79","80-89","90-99","100+"]
country_popul.append(ages)
def csv_dict_reader(file_obj):
    """
    Read a CSV file using csv.DictReader
    """
    popul_list = [country]
    reader = csv.DictReader(file_obj, delimiter=',')
    popul_sum = 0
    num = 0
    for line in reader:
      popul_sum += int(line["M"]) + int(line["F"])
      if num%2 == 1:
         popul_list.append(popul_sum)
         popul_sum = 0
      num += 1
    popul_list.append(popul_sum)
    country_popul.append(popul_list)



while counter != 20:
   with open("PopulationPyramids\\"+country+".csv") as f_obj:
        csv_dict_reader(f_obj)
   
   country_num += 2
   country = sheet[0,country_num]
   counter += 1


with open('CountriesPopulation.csv', 'w', newline='') as csvfile:
   writer = csv.writer(csvfile, delimiter=',')
   
   country_popul_zip = zip(*country_popul)
   for i in country_popul_zip:
      writer.writerow(i)
   
   
   
   
   

