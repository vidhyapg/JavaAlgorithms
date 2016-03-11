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
Sample Input

6
Sample Output

     #
    ##
   ###
  ####
 #####
######
 */
public class AddSpaces {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        String str = "#";
        String padded;
        /*for (int i= 0; i < N; i++) {
            padded = String.format("%" + N + "s", str);
            System.out.println(padded);
            str = str.concat("#");
        }
        */

        char[] step = new char[N];
        Arrays.fill(step, ' ');

        for (int i = N-1; i >=0; i--){
            step[i] = '#';
            System.out.println(step);
        }

    }
}