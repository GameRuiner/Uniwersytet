def isInclude(sS,lS):
    csS = {}
    clS = {}
    for i in sS:
        if i not in csS:
            csS[i] = 1
        else:
            csS[i] += 1
    for i in lS:
        if i not in clS:
            clS[i] = 1
        else:
            clS[i] += 1
    for i in csS:
        if i not in clS:
            return False
        else:
            if csS[i]>clS[i]:
                return False
    return True

print(isInclude('aktyw','lokomotywa'))
print(isInclude('kot','lokomotywa'))
print(isInclude('motyl','lokomotywa'))
print(isInclude('żak','lokomotywa'))
print(isInclude('kotka','lokomotywa'))
    
