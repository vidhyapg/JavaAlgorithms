package HackerRank;

/**
 * Created by vppriyad on 9/10/2015.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/*
Sample Input #1
We promptly judged antique ivory buckles for the next prize

Sample Output #1
pangram
 */
public class Pangram {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        /*
        A-Z 65 - 90 convert to (0 to 25)
        a-z 97 - 122 convert to (0 to 25)
        space 32
         */
        Scanner in = new Scanner(System.in);
        // Default delimiter is space. This ignores "\n" before string
        in.useDelimiter("\n"); // very very important to use this with hasNext() and next().
        int alph_present = 0;
        String str = "";
        if(in.hasNext()){
            str = in.next();
        }
        char a[] = str.toCharArray();
        int val;
        for (int i = 0 ; i < str.length(); i++){
            val = (int) a[i];
            // if value is within A-Z, convert to 0 to 25
            if ((val >=65) && (val <= 90)){
                val = (val-65);
            }
            // if value is within a -z
            else if ((val >=97) && (val <= 122)){
                val = (val-97);
            }
            // if value is space skip to next char, convert to 0 - 25
            else if (val == 32){
                continue;
            }
            // if val is not in any of the above then error
            else{
                throw new InputMismatchException();
            }
            // at this point val should be 0 to 25, set the corresponding position bit (1 to 26)
            alph_present |= (1 << val);
        }
        // System.out.println("the alph_present is " + alph_present);
        // with 26 bits set to 1
        if (alph_present == 0x3FFFFFF)
            System.out.println("pangram");
        else
            System.out.println("not pangram");
    }
}