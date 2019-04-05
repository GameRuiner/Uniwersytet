from math import cos,sin,ceil,floor,pi

def kolko(n,k):
    center = floor(n/2)
    indent = int((k-n)/2)
    for i in range(n):
        string =' '*indent
        for j in range(n):
            x=abs(center-i)
            y=abs(center-j)
            #if (x+y)<=center+1:
            if (x*x+y*y)<((n/2)**2):
                string+='#'
            else:
                string+=' '
        print(string)
    
        
kolko(7,11)
kolko(9,11)
kolko(11,11)
