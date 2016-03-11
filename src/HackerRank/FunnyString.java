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
Sample Input
2
acxz
bcxz

Sample Output
Funny
Not Funny
 */
public class FunnyString {

    public static boolean isFunny(String str){
        char[] str_a = str.toCharArray();
        boolean funny = true;
        int N = str.length();
        for (int i = 1; i < N; i++){
            int f_diff = Math.abs((int)str_a[i] - (int)str_a[i-1]);
            int r_diff = Math.abs((int)str_a[N-i] - (int)str_a[N-i-1]);
            if (f_diff != r_diff) {
                funny = false;
                break;
            }
        }
        return funny;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        in.useDelimiter("\n");
        int N = 0;
        if(in.hasNextInt()){
            N = in.nextInt();
        }
        // System.out.println("Enter "+N+" strings");
        // for each String check if it a Funny String and display result
        // use next() instead of nextLine()  - complete token is preceded and followed by input that matches the delimiter pattern.
        // \n from previous int was getting counted as string previously.
        for (int j = 0; (j < N) && (in.hasNext()); j++){
            // String str = in.next();
            if(isFunny(in.next()))
                System.out.println("Funny");
            else
                System.out.println("Not Funny");
        }
    }
}