package HackerRank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/**
 * Created by vppriyad on 9/8/2015.
 */

/*
3
11  2   4
4   5   6
10  8   -12

15
*/
public class Diagonal {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[][] mat = new int[N][N];
        for (int i = 0 ; i < N; i++){
            for (int j = 0; j < N; j++){
                mat[i][j] = in.nextInt();
            }
        }
        int rsum = 0, lsum = 0;
        for (int i = 0; i < N; i++){
            lsum += mat[i][i];
            rsum += mat[i][N-1-i];
        }
        System.out.println(Math.abs(lsum-rsum));
    }
}
