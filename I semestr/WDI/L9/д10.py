class ListItem:
 def __init__(self,value):
    self.val = value
    self.next = None

def wypisz(lis):
    while lis != None:
        print(lis.val)
        lis = lis.next

lis = ListItem(4)
lis.next = ListItem(-5)
lis.next.next = ListItem(8)
lis.next.next.next = ListItem(-9)
def rev(lis):
    if lis == None:
        return None # dla pustoi listy

    def aux(list):
        if(list.next == None):
            res = ListItem(list.val)
            return (res,res)
        else:
            res = aux(list.next)
            list2 = ListItem(list.val)
            res[1].next = list2
            return (res[0],list2)
    return aux(lis)[0]
print("rev")
wypisz(rev(lis))

def div(lis):
    pos = ListItem(0)
    curp = pos
    neg = ListItem(0)
    curn = neg
    while lis != None:
        if lis.val > 0:
            if pos.val == 0:
                pos.val = lis.val
            else:
                curp.next = ListItem(lis.val)
                curp = curp.next
        if lis.val < 0:
            if neg.val == 0:
                neg.val = lis.val
            else:
                curn.next = ListItem(lis.val)
                curn = curp.next
        lis = lis.next
    curp.next = neg # тут я их просто склеил, но у тебя их две как и просили, начало neg i pos
    return pos
print("div")
wypisz(div(lis))


class ListItem2:
 def __init__(self,value):
    self.val = value
    self.next = None
    self.prev = None



lis2 = ListItem2(4)
def add(lis, val):
    while lis.next != None:
        lis = lis.next
    res = ListItem2(val)
    res.prev = lis
    lis.next = res

add(lis2,45)
add(lis2,1)
add(lis2,5)
print("test add")
wypisz(lis2)

def pop(lis):
    lis = lis.next
    lis.prev = None
    return lis

print("test pop")
lis2 = pop(lis2)
lis2 = pop(lis2)
wypisz(lis2)