package HackerRank;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/**
 * Created by vppriyad on 9/13/2015.
 */
public class Example1 {
/*
 * Complete the function below.
 */

    static ArrayList<Integer> findDigits(int a){
        ArrayList<Integer> digits = new ArrayList<Integer>();
        while (a > 0) {
            digits.add(a % 10);
            a = a / 10;
        }
        return digits;
    }

    static void findingDigits(int[] foo) {
        // for each array element a find and print count
        int count = 0;
        for(int a : foo){
            count = 0;
            for (int x: findDigits(a)){
                if ((x!= 0)&&(a % x == 0))
                    count++;
            }
            System.out.println(count);
        }

    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int _foo_size = 0;
        _foo_size = Integer.parseInt(in.nextLine());
        int[] _foo = new int[_foo_size];
        int _foo_item;
        for(int _foo_i = 0; _foo_i < _foo_size; _foo_i++) {
            _foo_item = Integer.parseInt(in.nextLine());
            _foo[_foo_i] = _foo_item;
        }

        findingDigits(_foo);

    }
}
