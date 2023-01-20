import java.math.BigInteger;
//import java.util.ArrayList;
//import java.util.Arrays;
import java.lang.Long;

class fibonacci_in_java {

    public static void main(String[] args) {
        //ArrayList<String> partners = new ArrayList(Arrays.asList(new String[] { "Tom", "Maxim" }));
        //System.out.println("Partners: " + partners);
        testFibonacci();
    }
    // Implement the following functions, which will be tested for speed, and bad
    // inputs:
    //
    // fib(n) --> returns the n th fibonacci number. Bonus for fastest.
    // n may be a long, but for full credit the return should be a
    // https://docs.oracle.com/javase/7/docs/api/java/math/BigInteger.html
    //
    // fib_sum_even(n) --> sums the first n even fibonacci numbers, e.g.
    // fib_sum_even(3) --> sum([0,2,8]) --> 10

    /* //lenas fib
     * public static long fib(long n){
     * if(n==1){return 0;}
     * if(n==2){return 1;}
     * long toldest=0;
     * long told = 1;
     * long t = 1;
     * for(int i=2; i<n; i++){
     * t = told + toldest;
     * toldest = told;
     * told = t;
     * }
     * return t;
     * }
     */

    // SLOW FIB
    public static BigInteger fib(long n) {
        if (n == 1) {
            return BigInteger.ZERO;
        }
        if (n == 2) {
            return BigInteger.ONE;
        }
        BigInteger toldest = BigInteger.ZERO;
        BigInteger told = BigInteger.ONE;
        BigInteger t = BigInteger.ONE;
        for (int i = 1; i < n; i++) {
            t = told.add(toldest);
            toldest = told;
            told = t;
        }
        return t;
    }

    // MED FIB
    public static BigInteger medFib(long n) {
        if (n == 1) {
            return BigInteger.ZERO;
        }
        if (n == 2) {
            return BigInteger.ONE;
        }
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (long bit = Long.highestOneBit(n); bit != 0; bit >>>= 1) {
            BigInteger d = a.multiply(b.shiftLeft(1).subtract(a));
            BigInteger e = a.multiply(a).add(b.multiply(b));
            a = d;
            b = e;
            if ((n & bit) != 0) {
                BigInteger c = a.add(b);
                a = b;
                b = c;
            }
        }
        return a;
    }

    // FAST FIB
    public static BigInteger fastFib(long n) {
        if (n == 1) {
            return BigInteger.ZERO;
        }
        if (n == 2) {
            return BigInteger.ONE;
        }
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (long bit = Long.highestOneBit(n); bit != 0; bit >>>= 1) {
            BigInteger d = karatsubaMultiply(a, b.shiftLeft(1).subtract(a));
            BigInteger e = karatsubaMultiply(a, a).add(karatsubaMultiply(b, b));
            a = d;
            b = e;
            if ((n & bit) != 0) {
                BigInteger c = a.add(b);
                a = b;
                b = c;
            }
        }
        return a;
    }

    // karatsuba multiplication. so interesting. who knew you could actually do less
    // multiplication in a multiplication problem
    public static BigInteger karatsubaMultiply(BigInteger x, BigInteger y) {
        if (x.bitLength() <= 1536 || y.bitLength() <= 1536) {
            return x.multiply(y);

        } else {
            int n = Math.max(x.bitLength(), y.bitLength());
            int half = (n + 32) / 64 * 32;
            BigInteger mask = BigInteger.ONE.shiftLeft(half).subtract(BigInteger.ONE);
            BigInteger xlow = x.and(mask);
            BigInteger ylow = y.and(mask);
            BigInteger xhigh = x.shiftRight(half);
            BigInteger yhigh = y.shiftRight(half);

            BigInteger a = xhigh.multiply(yhigh);
            BigInteger b = (xlow.add(xhigh)).multiply(ylow.add(yhigh));
            BigInteger c = xlow.multiply(ylow);
            BigInteger d = b.subtract(a).subtract(c);
            return a.shiftLeft(half).add(d).shiftLeft(half).add(c);
        }
    }

    public static void testFibonacci() {
        long[] testNs = { 1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 50, 250, 2500, 2000000 };
        // long[] testKs = {0,3,}
        for (int i = 0; i < testNs.length; i++) {
            long n = testNs[i];

            long fibstartT = System.nanoTime();
            BigInteger x = fib(n);
            long fibT = System.nanoTime() - fibstartT;

            long medfibstartT = System.nanoTime();
            BigInteger y = medFib(n);
            long medfibT = System.nanoTime() - medfibstartT;

            long fastfibstartT = System.nanoTime();
            BigInteger z = fastFib(n);
            long fastfibT = System.nanoTime() - fastfibstartT;

            if (x.equals(y) && y.equals(z)) {
                System.out.println("fib(" + n + ") = " + z + " \n  Elapsed time || fib: " + fibT + "ns |med: " + medfibT
                        + "ns |fast: " + fastfibT+"ns");
            } else {
                System.out.println("Fibs do not match fib(" + n + ")=" + x + "|" + y + "|" + z);
            }
        }
    }
}