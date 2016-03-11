package HackerRank;

/**
 * Created by vppriyad on 9/8/2015.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
Input
6
-4 3 -9 0 4 1
Output
0.500
0.333
0.167
 */

public class Fraction {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        float cPos = 0, cNeg = 0, cZero = 0;
        // float fPos = 0, fNeg = 0, fZero = 0;
        for(int i = 0; i < N; i++){
            int x = in.nextInt();

            if (x > 0){
                cPos++;
            }else if (x < 0){
                cNeg++;
            }else{
                cZero++;
            }
        }

        System.out.printf("%.3f\n",cPos/N);
        System.out.printf("%.3f\n",cNeg/N);
        System.out.printf("%.3f\n",cZero/N);
    }
}
