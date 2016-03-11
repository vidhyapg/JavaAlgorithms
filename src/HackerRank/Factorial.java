package HackerRank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/**
 * Created by vppriyad on 9/10/2015.
 */
/*
N!=N×(N?1)×(N?2)×?×3×2×1
Sample Input
25
Sample Output
15511210043330985984000000

 */

public class Factorial {

    public static BigInteger factorial (BigInteger n){
        if (n.equals(BigInteger.ONE)){
            return BigInteger.ONE;
        }

        // n * factorial(n-1)
        return (n.multiply(factorial(n.subtract(BigInteger.ONE))));
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        BigInteger bN = new BigInteger(String.valueOf(N));
        BigInteger factorial = factorial(bN);
        System.out.println(factorial);
    }
}