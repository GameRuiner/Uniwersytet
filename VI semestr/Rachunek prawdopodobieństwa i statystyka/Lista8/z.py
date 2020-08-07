import csv
import math

with open('climate.csv', newline='') as csvfile:
    spamreader = csv.reader(csvfile, delimiter=';')
    alt = [];temp = [];lat = [];long = []
    for row in spamreader:
        if row[5] != "'Alt'":
           alt.append(int(row[5]))
           y = float(row[4][0])+float(row[4][2])/10
           temp.append(y)
           lat.append(float(row[1].replace(',','.')))
           long.append(float(row[2].replace(',','.')))
    y_sum = 0
    x_sum = 0
    count = len(alt)
    for x in alt:
      x_sum += x
    for y in temp:
      y_sum += y
    x_avg = x_sum / count
    y_avg = y_sum / count
    sum1 = 0
    sum2 = 0
    for i in range(count):
        sum1 += (alt[i] - x_avg) * (temp[i] - y_avg)
        sum2 += math.pow(alt[i] - x_avg,2)
    a = sum1/sum2
    b = y_avg - a*x_avg
    print(x_avg, y_avg, a, b)
    x1_sum = 0
    for x1 in lat:
      x1_sum+= x1
    x2_sum = 0
    for x2 in long:
      x2_sum+= x2
    x1_avg = x1_sum / count
    x2_avg = x2_sum / count
    #print(x1_avg, x2_avg)
    sum1 = 0
    sum2 = 0
    for i in range(count):
      sum1 += (lat[i] - x1_avg) * (temp[i] - y_avg)
      sum2 += math.pow(lat[i] - x1_avg,2)
    a1 = sum1/sum2
    sum1 = 0
    sum2 = 0
    for i in range(count):
      sum1 += (long[i] - x2_avg) * (temp[i] - y_avg)
      sum2 += math.pow(long[i] - x2_avg,2)
    a2 = sum1/sum2
    b1 = y_avg - a1*x1_avg
    b2 = y_avg - a2*x2_avg
    #print(b1 + b2)