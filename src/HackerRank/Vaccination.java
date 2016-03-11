package HackerRank;
import java.io.*;
import java.security.InvalidParameterException;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
/**
 * Created by vppriyad on 9/13/2015.
 */
public class Vaccination {
    /*
    Input: Integer N is the total number of cities
    Integer B is total number of clinics
    Array pop[] contains population in each city.

    Output:
    The maximum number of people to be immunized in any single clinic.

    Runtime Complexity:
    Worst Case, Best Case, Average Case = O(N*B*B)
    Space Complexity: O(N*B)
     */
    private static int maxPeopleDynamic(int N, int B, int pop[]){
        // Array to hold maximum number of people to be immunized in the largest clinic for each subproblem bottom up (1 to N) (1 to B)
        int[][] min_ofMaxPeople = new int[N][B];

        if ((N <= 0) || (B < N)){
            throw new InvalidParameterException();
        }
        // base case when N = 1, C can range from 1 to B
        for (int c = 1; c <= B; c++){
                min_ofMaxPeople[0][c-1] = pop[0] / c;
        }

        // for each city i, from 2 to N (city 1 is initialized in base case)
        for (int i = 2; i <= N; i++){
            int c = 1;
            for (c = 2; c <= B; c++){
                // for [i,c] = min{max{pop[i-1]/1, [i-1, c-1]}, max{pop[i-1]/2, [i-1, c-2 ]},.... max{pop[i-1]/c-(i-1), [i-1,i-1]}}
                // for [i,c] = min{max{pop[i-1]/j, [i-1,c-j]}}
                int j = 1;
                int min = Math.max(pop[i-1] / j, min_ofMaxPeople[i-2][c-j-1]);
                // number of clinics for each city can take values from (j) 1 to B-(N-1)
                // for each of these values get cached value of ans for remaining cities N-1 with B-j clinics and derive ans for N
                // min of all such possible values for N is the ans for N
                for (j = 2; j <= c-(i-1); j++) {
                    int max_people = Math.max(pop[i-1] / j, min_ofMaxPeople[i-2][c-j-1]);
                    if (max_people < min) {
                        min = max_people;
                    }
                }
                min_ofMaxPeople[i-1][c-1] = min;
            }
        }
        return min_ofMaxPeople[N-1][B-1];
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        // Test Cases

        System.out.println("Test 1 result is " + (test1() ? "PASS" : "FAIL"));
        System.out.println("Test 2 result is " + (test2() ? "PASS" : "FAIL"));
        System.out.println("Test 3 result is " + (test3() ? "PASS" : "FAIL"));
        System.out.println("Test 4 result is " + (test4() ? "PASS" : "FAIL"));
        System.out.println("Test 5 result is " + (test5() ? "PASS" : "FAIL"));
        System.out.println("Test 6 result is " + (test6() ? "PASS" : "FAIL"));
        /*
        Scanner in = new Scanner(System.in);
        int N = 0;
        if (in.hasNextInt()){
            N = in.nextInt();
        }
        int B = 0;
        if (in.hasNextInt()){
            B = in.nextInt();
        }
        int[] a = new int[N];

        for (int i = 0 ; i < N; i++) {
            if (in.hasNextInt()) {
                a[i] = in.nextInt();
            }
        }
        try {
            System.out.println(maxPeopleDynamic(N, B, a));
        }catch(Exception e) {
            System.out.println("Exception" + e);
        }
        */
    }
    // naive implementation with  exponential running time. Used for testing the dynamic programming implementation on small values.
    private static int maxPeople(int N, int B, int pop[]){
        if ((N <= 0) || (B < N)){
            throw new InvalidParameterException();
        }
        // base case, if N = 1 or B = 1
        if ((N==1)||(B==1)){
            //System.out.println("Base for N: "+ N +" B: "+ B + " Ans is: "+ pop[0]/B);
            return pop[0]/B;
        }

        int min = 0;
        // assign B number of clinics to N Cities
        // start with city = N-1, check for all possible clinics value this city can take.
        // calculate minimum of max people to be immunized in all cities when this city has c clinics.
        int city = N-1;
        int c = 1;
        int max_people;
        // initialize min with first value for this city and when clinics = 1.
        int min_ofMaxPeople = Math.max(pop[city]/c, maxPeople(N-1, B-c, pop));
        // number of clinics can range from 1 to B-N-1
        for (c = 2; c <= B-(N-1); c++){
            // this city has c clinics
            // maximum number of people to be immunized in the largest clinic
            // return max of (people to be immunized in this city, max(people to be immunized in other N-1 cities))
            max_people = Math.max(pop[city]/c, maxPeople(N-1, B-c, pop));
            if (max_people < min_ofMaxPeople){
                min_ofMaxPeople = max_people;
            }
        }
        //System.out.println("For N: "+ N +" B: "+ B + " Ans is: "+ min_ofMaxPeople);
        //System.out.println("*****************************************************");
        return min_ofMaxPeople;
    }

    public static boolean test1(){
        int pop[]= {200000,500000};
        int N = 2, B = 7;
        int expected_result = 0;
        int actual_result = 0;
        try {
            expected_result = maxPeople(N, B, pop);
        }catch(Exception e){
            System.out.println("Exception" + e);
        }
        try {
            actual_result = maxPeopleDynamic(N, B, pop);
        }catch(Exception e){
            System.out.println("Exception" + e);
        }
        System.out.println("    Log: Actual Result is: " + actual_result);
        return ((expected_result == actual_result)? true : false );
    }

    public static boolean test2(){
        int pop[]= {2000,5000,6000,7000,8000};
        int N = 5, B = 10;
        int expected_result = 0;
        int actual_result = 0;
        try {
            expected_result = maxPeople(N, B, pop);
        }catch(Exception e){
            System.out.println("Exception" + e);
        }
        try {
            actual_result = maxPeopleDynamic(N, B, pop);
        }catch(Exception e){
            System.out.println("Exception" + e);
        }
        System.out.println("    Log: Actual Result is: " + actual_result);
        return ((expected_result == actual_result)? true : false );
    }

    public static boolean test3(){
        int pop[]= {23423, 45656, 233432, 5000000};
        int N = 4, B = 20;
        int expected_result = 0;
        int actual_result = 0;
        try {
            expected_result = maxPeople(N, B, pop);
        }catch(Exception e){
            System.out.println("Exception" + e);
        }
        try {
            actual_result = maxPeopleDynamic(N, B, pop);
        }catch(Exception e){
            System.out.println("Exception" + e);
        }
        System.out.println("    Log: Actual Result is: " + actual_result);
        return ((expected_result == actual_result)? true : false );
    }

    public static boolean test4(){
        int pop[]= {2000};
        int N = 1, B = 0;
        int actual_result = 0;
        try {
            actual_result = maxPeopleDynamic(N, B, pop);
        }catch(Exception e){
            System.out.println("    Log: Actual Result is: " + "Invalid Parameter Exception");
            return true;
        }
        return false;
    }

    public static boolean test5(){
        int pop[]= {2000};
        int N = 1, B = 1;
        int expected_result = 0;
        int actual_result = 0;
        try {
            expected_result = maxPeople(N, B, pop);
        }catch(Exception e){
            System.out.println("Exception" + e);
        }
        try {
            actual_result = maxPeopleDynamic(N, B, pop);
        }catch(Exception e){
            System.out.println("Exception" + e);
        }
        System.out.println("    Log: Actual Result is: " + actual_result);
        return ((expected_result == actual_result)? true : false );
    }

    public static boolean test6(){
        int pop[]= {2000,5000,6000,7000,8000};
        int N = 5, B = 2000000;
        int expected_result = 0;
        int actual_result = 0;
        try {
            actual_result = maxPeopleDynamic(N, B, pop);
        }catch(Exception e){
            System.out.println("Exception" + e);
        }
        System.out.println("    Log: Actual Result is: " + actual_result);
        try {
            expected_result = maxPeople(N, B, pop);
        }catch(Exception e){
            System.out.println("Exception" + e);
        }
        return ((expected_result == actual_result)? true : false );
    }
}
