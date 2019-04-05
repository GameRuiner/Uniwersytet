from losowanie_fragmentow import losuj_fragment

def pass1(n):
    k=n-1
    s=''
    while len(s)!=n:
        x = losuj_fragment()
        if len(s) + len(x) == k:
            continue
        if len(s) + len(x) <= n:
            s+=x
    return s


def pass2(n):
    k=n-1
    s=''
    while len(s)!=n:
        x = losuj_fragment()
        if len(s) + len(x) != k:
            if len(s) + len(x) <= n:
                s+=x
    return s

def pass3(n):
    k=n-1
    s=''
    x = losuj_fragment()
    while len(s)!=n and len(s) + len(x) != k:
        x = losuj_fragment()
        if len(s) + len(x) <= n:
                s+=x
    return s

print(pass1(8))
print(pass2(8))
print(pass3(8))
