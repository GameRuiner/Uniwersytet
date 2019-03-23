def Hanoi(n,A,C,B):
    if n > 0:
        Hanoi(n-1,A,B,C)
        L = len(A)
        a=L
        for i in range(L):
            if C[i]==0:
                c=i
                break
        for i in range(L):
            if A[i]==0:
               a=i
               break
        C[c] = A[a-1]
        A[a-1]=0
                
        print(A, B, C, '##############', sep = '\n')

        Hanoi(n-1,B,C,A)
     
A = [5,4,3,2,1]
B = [0,0,0,0,0]
C = [0,0,0,0,0]
Hanoi(5,A,C,B)



A = [2, 1]
B = []
C = []

def move(n, A, C, B):
    if n > 0:
        # move n - 1 disks from source to auxiliary, so they are out of the way
        move(n - 1, A, B, C)

        # move the nth disk from source to target
        C.append(A.pop())

        # Display our progress
        print(A, B, C, '##############', sep = '\n')

        # move the n - 1 disks that we left on auxiliary onto target
        move(n - 1, B, C, A)

# initiate call from source A to target C with auxiliary B
#move(2, A, C, B)
