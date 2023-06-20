# Function for nth Fibonacci number
def Fibonacci(n):
	if n < 0:
		print("Incorrect input")
	elif n == 0:
		return 0
	elif n == 1 or n == 2:
		return 1
	else:
		return Fibonacci(n-1) + Fibonacci(n-2)
# Driver
print(Fibonacci(9))


# Function for nth fibonacci
# number - Dynamic Programming
FibArray = [0, 1]

def fibonacci(n):
	if n < 0:
		print("Incorrect input")
	elif n < len(FibArray):
		return FibArray[n]
	else:	
		FibArray.append(fibonacci(n - 1) + fibonacci(n - 2))
		return FibArray[n]
# Driver 
print(fibonacci(9))


# Function for nth fibonacci
# number - Space Optimisation
def fibonacci(n):
	a = 0
	b = 1
	if n < 0:
		print("Incorrect input")
	elif n == 0:
		return 0
	elif n == 1:
		return b
	else:
		for i in range(1, n):
			c = a + b
			a = b
			b = c
		return b
# Driver
print(fibonacci(9))


from functools import lru_cache
# Function for nth Fibonacci number
# lru_cache will store the result so we don't have to find fibonacci for same num again
@lru_cache(None)
def fibonacci(num: int) -> int:
	if num < 0:
		print("Incorrect input")
		return
	elif num < 2:
		return num
	return fibonacci(num - 1) + fibonacci(num - 2)
# Driver 
print(fibonacci(9))
