package HackerRank;

/**
 * Created by vppriyad on 9/11/2015.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
Input
10
1
1
1
2
1
3
2
1 2
3
1 4 1
3
1 5 1
1
234
1
20000
3
6 23 6
1
1

Output
YES
YES
YES
NO
YES
YES
YES
YES
YES
YES
 */
public class RunningSum {

    /*
    Sample Input
    2
    3
    1 2 3
    4
    1 2 3 3

    Sample Output
    NO
    YES
     */
    /*
    check if there exists an element in the array such that the sum of the elements on its left is equal to the sum of the elements
    on its right. If there are no elements to the left/right, then the sum is considered to be zero.
    Formally, find an i, such that, A1+A2...Ai-1 =Ai+1+Ai+2...AN.
    */
    private static boolean checkIfSumBalance(int a[], int n, int sum){
        boolean sum_balance = false;
        // loop thru and check if sum_balance is true for any of the elements.
        // it is true if before_sum == after_sum for a element.
        // before_sum is sum of elements (i-1 to 0) or before_sum of prev + a[i-1]
        // after_sum is Tsum-(sum(element i, i-1,.. 0 )) or after_sum of prev -a[i]
        // before and after sum of first element
        int before_sum = 0;
        int after_sum = sum - a[0];

        // corner case: if only one element it is not going to go through the for loop
        // if only one element, always the condition is true 0 == 0
        if (n == 1){
            return true;
        }
        for (int i = 1; i < n; i++){
            if (after_sum == before_sum){
                sum_balance = true;
                break;
            }
            before_sum = before_sum + a[i-1];
            after_sum = after_sum - a[i];
        }
        return sum_balance;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
            Scanner in = new Scanner(System.in);
            // in.useDelimiter("\n");
            int N = 0;
            // get total number of test cases
            if (in.hasNextInt()){
                N = in.nextInt();
            }
            // for each test case
            for (int i = 0 ; i < N; i++){
                if (in.hasNextInt()){
                    // get size of the array
                    int size = in.nextInt();
                    int a[] = new int[size];
                    // get the array elements seperated by space.
                    int sum = 0;
                    for (int j = 0; (j < size) && (in.hasNextInt()) ; j++){
                        a[j] = in.nextInt();
                        sum += a[j];
                    }
                    // System.out.println("Sum of all elements: "+sum);
                    if (checkIfSumBalance(a, size, sum)) System.out.println("YES");
                    else System.out.println("NO");
                }
        }
    }
}