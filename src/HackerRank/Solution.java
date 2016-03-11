package HackerRank;

/**
 * Created by vppriyad on 9/8/2015.
 */
/*
import java.io.*;
import java.util.*;

class Solution {

    static int solveMeSecond(int a, int b) {
        return a+b;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t;
        int sum;
        int a,b;
        t = in.nextInt();
        for (int i=0;i<t;i++) {
            a = in.nextInt();
            b = in.nextInt();
            sum = solveMeSecond(a, b);
            System.out.println(sum);
        }
    }
}
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int N;
        N = in.nextInt();
        long sum = 0;
        for (int i=0; i<N; i++){
            sum += in.nextInt();
        }
        System.out.println(sum);
    }
}
