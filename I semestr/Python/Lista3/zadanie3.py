def usun_nawiasy(s):
    l=len(s)
    i=0
    while(i<l):
        if s[i]=='(' or s[i]==')':
            s=(s[:i]+s[i+1:])
            i=i-1
            l=l-1
        i=i+1
    return(s)
        
s="(Ala ma kota() (perskiego)!)"
ss='((1))234()'
sss="(()(a)())("

print(usun_nawiasy(sss))

