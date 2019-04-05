P = []
labels = {}
c=-1
for x in open('program.pygo'):
  L = x.split()
  c+=1
  if len(L) == 0:
    continue
  if L[1] == '=':
    P.append( ('=', L[0], ' '.join(L[2:])) )
  elif L[0] == 'print':
    P.append( ('print', ' '.join(L[1:])) )
  elif L[0] == 'goto':
    P.append( ('goto', L[1]) )
  elif L[0] == 'if':
    P.append( ('if', ' '.join(L[1:-2]) , L[-1]))
  elif L[0][len(L[0])-1] == ':':
    labels[L[0][:-1]] = c
    if L[2] == '=':
      P.append( ('=', L[1], ' '.join(L[3:])) )
    elif L[1] == 'print':
      P.append( ('print', ' '.join(L[2:])) )
    elif L[1] == 'goto':
      P.append( ('goto', L[2]) )
    elif L[1] == 'if':
      P.append( ('if', ' '.join(L[2:-2]) , L[-1]))
    
    
#for instr in P:
#  print (instr)
print(labels)
PC = 0
M = {} # memory

while PC < len(P):
  instr = P[PC]
  typ = instr[0]
  if typ == '=':
    M[instr[1]] = eval(instr[2], M)
    PC += 1
  elif typ == 'print':
    print (eval(instr[1], M))
    PC += 1
  elif typ == 'goto':
    PC = labels[instr[1]] 
  elif typ == 'if':
    warunek = eval(instr[1], M)
    if warunek:
      PC = labels[instr[2]] 
    else:
      PC += 1 
       
    
