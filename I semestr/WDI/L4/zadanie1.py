def a(n):
    if n % 2 == 0: return n
    return -n


def b(n):
    result = -1
    b = 0
    for i in range(2, n + 1):
        b = a(i)
        result += 1 / b
    return result


def c(n,x):
    result=0
    for i in range(1,n+1):
        result+=i*power(x,i)
    return result
def power(x, n):
    if n == 0: return 1
    if n == 1: return x
    if n % 2 == 0:
        return power( x * x,n / 2 )
    if n % 2 == 1:
        return x * power(x * x,(n-1) / 2)

print(c(4,2))
