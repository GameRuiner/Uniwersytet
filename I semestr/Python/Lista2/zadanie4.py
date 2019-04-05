from duze_cyfry import dajCyfre

def bigNumber(n):
    string = str(n)
    result = []
    str1 = ''
    str2 = ''
    str3 = ''
    str4 = ''
    str5 = ''
    for i in range(len(string)):
                   number=dajCyfre(int(string[i]))
                   str1+=number[0]+' '
                   str2+=number[1]+' '
                   str3+=number[2]+' '
                   str4+=number[3]+' '
                   str5+=number[4]+' '
    
    print(str1)
    print(str2)
    print(str3)
    print(str4)
    print(str5)
                   
bigNumber(12345)
