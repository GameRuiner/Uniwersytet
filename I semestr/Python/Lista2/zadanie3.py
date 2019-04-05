from math import cos,sin,ceil,floor,pi

def kolko(n):
    b=1
    for i in range (1,n+1):
        line =round(n*sin(pi/(n+1)*i))  
        print(' '*int((n-line)/2)+line*'#')

for i in range (7,14,2):
 kolko(i)
