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
07:05:45PM
hh:mm:ssa
Sample Output
19:05:45
HH:mm:ss
 */
public class TimeConversion {

    public static void main(String[] args) throws ParseException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:m:ssaa");
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
        Scanner in = new Scanner(System.in);
        try {
            Date dt = parseFormat.parse(in.nextLine());
            String newDt = displayFormat.format(dt);
            System.out.println(newDt);
        }catch(ParseException e){
            System.out.println("Unexpected format not in hh:m:ssa"+e);
        }
    }
}
