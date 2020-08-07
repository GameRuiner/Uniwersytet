import csv
import math

	
def csv_dict_reader(file_obj):
    """
    Read a CSV file using csv.DictReader
    """
    reader = csv.DictReader(file_obj, delimiter=';')
    reader2 = csv.DictReader(file_obj, delimiter=';')
    #count = len(list(reader))
    y_sum = 0
    x_sum = 0
    count = 0
    for line in reader:
       x_sum += int(line["'Alt'"])
       y_sum += float(line["'Temp'"][0])+float(line["'Temp'"][2])/10
       count+= 1
    for line in reader2:
       print(3)
       x_sum += int(line["'Alt'"])
       y_sum += float(line["'Temp'"][0])+float(line["'Temp'"][2])/10
       count+= 1
    x_avg = x_sum / count
    y_avg = y_sum / count
    sum1 = 0
    sum2 = 0
    for line in reader:
       print(line["'Alt'"])
       sum1 += (int(line["'Alt'"]) - x_avg)*((float(line["'Temp'"][0])+float(line["'Temp'"][2])/10)-y_avg)
       sum2 += math.pow((int(line["'Alt'"])-x_avg),2)
    #a = sum1/sum2
    print(count)

with open("climate.csv") as f_obj:
        csv_dict_reader(f_obj)
	


	

