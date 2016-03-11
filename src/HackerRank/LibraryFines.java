package HackerRank;

/**
 * Created by vppriyad on 9/9/2015.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/*
Sample Input
9 6 2015
6 6 2015

Sample Output
45
*/
public class LibraryFines {

    public static void printFine(int fine){
        System.out.println(fine);
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        SimpleDateFormat df = new SimpleDateFormat("d M y");
        Date aDate = new Date();
        Date eDate = new Date();
        int fine = 0;
        try {
            aDate = df.parse(in.nextLine());
            eDate = df.parse(in.nextLine());
        }catch(ParseException e){
            System.out.println("Unexpected Input Format:"+e);
        }
        // if actual date is on or before expected date
        if (aDate.before(eDate) || (aDate.compareTo(eDate) == 0)){
            printFine(fine);
            return;
        }

        String aDateArr[] = df.format(aDate).split(" ");
        String eDateArr[] = df.format(eDate).split(" ");
        Integer aDateIntArr[] = new Integer[aDateArr.length];
        Integer eDateIntArr[] = new Integer[eDateArr.length];

        for (int i = 0; i < aDateArr.length ; i++){
            aDateIntArr[i] = Integer.parseInt(aDateArr[i]);
            eDateIntArr[i] = Integer.parseInt(eDateArr[i]);
        }
        // actual date is after expected date
        // if actual return date year after expected return date year, fine is 10000
        if (aDateIntArr[2] > eDateIntArr[2]){
            fine = 10000;
        }
        // same year, late months * 500
        else if (aDateIntArr[1] > eDateIntArr[1]){
            fine = (aDateIntArr[1] - eDateIntArr[1]) * 500;
        }
        // same year, same month, late days * 15
        else{
            fine = (aDateIntArr[0] - eDateIntArr[0]) * 15;
        }
        printFine(fine);
    }
}