# Function for nth Fibonacci number
def Fibonacci(n):

	# Check if input is 0 then it will
	# print incorrect input
	if n < 0:
		print("Incorrect input")

	# Check if n is 0
	# then it will return 0
	elif n == 0:
		return 0

	# Check if n is 1,2
	# it will return 1
	elif n == 1 or n == 2:
		return 1

	else:
		return Fibonacci(n-1) + Fibonacci(n-2)

# Driver Program
print(Fibonacci(9))

# This code is contributed by Saket Modi
# then corrected and improved by Himanshu Kanojiya


# Function for nth fibonacci
# number - Dynamic Programming
# Taking 1st two fibonacci numbers as 0 and 1
FibArray = [0, 1]

def fibonacci(n):

	# Check is n is less
	# than 0
	if n < 0:
		print("Incorrect input")
		
	# Check is n is less
	# than len(FibArray)
	elif n < len(FibArray):
		return FibArray[n]
	else:	
		FibArray.append(fibonacci(n - 1) + fibonacci(n - 2))
		return FibArray[n]

# Driver Program
print(fibonacci(9))

# This code is contributed by Saket Modi

# Function for nth fibonacci
# number - Space Optimisation
# Taking 1st two fibonacci numbers as 0 and 1

def fibonacci(n):
	a = 0
	b = 1
	
	# Check is n is less
	# than 0
	if n < 0:
		print("Incorrect input")
		
	# Check is n is equal
	# to 0
	elif n == 0:
		return 0
	
	# Check if n is equal to 1
	elif n == 1:
		return b
	else:
		for i in range(1, n):
			c = a + b
			a = b
			b = c
		return b

# Driver Program
print(fibonacci(9))

# This code is contributed by Saket Modi
# Then corrected and improved by Himanshu Kanojiya

from functools import lru_cache

# Function for nth Fibonacci number

# lru_cache will store the result
# so we don't have to find
# fibonacci for same num again


@lru_cache(None)
def fibonacci(num: int) -> int:

	# check if num is less than 0
	# it will return none
	if num < 0:
		print("Incorrect input")
		return

	# check if num between 1, 0
	# it will return num
	elif num < 2:
		return num

	# return the fibonacci of num - 1 & num - 2
	return fibonacci(num - 1) + fibonacci(num - 2)


# Driver Program
print(fibonacci(9))

# This code is contributed by Sayedul Haque Sarker
