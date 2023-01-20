#include <stdio.h>

int reg_fib(int n)
{
    int x = 0;
    int y = 1;
    int i;
    int sum;
    for (i = 1; i <= n; i++)
    {
        sum = x + y;
        x = y;
        y = sum;
    }
    return x;
}

int recursive_fib(int n)
{
    if (n <= 1)
        return n;
    return recursive_fib(n - 1) + recursive_fib(n - 2);
}

int main(void)
{
    //same error at >48. idk why
    int n = 5;
    printf("\nfibonnaci sequence to the n: %d", n);
    printf("\nreg_fib: %d", reg_fib(n));
    printf("\nrecursive_fib: %d\n\n", recursive_fib(n));
    return 0;
}