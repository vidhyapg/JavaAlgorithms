package ADS1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by vppriyad on 8/23/2015.
 * The file contains 1 million Longs, both positive and negative (there might be some repetitions!).
 * This is your array of integers, with the ith row of the file specifying the ith entry of the array.

 Your task is to compute the number of target values t in the interval [-10000,10000] (inclusive) such
 that there are distinct numbers x,y in the input file that satisfy x+y=t.
 (NOTE: ensuring distinctness requires a one-line addition to the algorithm from lecture.)
 */

public class TwoSum {

    private HashSet<Long> hs_ArrayA;

    public TwoSum(String filename){
        // populate hash table with Longs in file
        hs_ArrayA = new HashSet<Long>();
        String line = null;
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bf = new BufferedReader(fileReader);
            while((line = bf.readLine())!= null){
                hs_ArrayA.add(Long.parseLong(line.trim()));
            }
            bf.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            filename + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + filename + "'");
        }
    }
    // given t, find if there are distinct numbers x,y in the input file that satisfy x+y=t
    private boolean isTargetSumPresent(int t){
        boolean targetPresent = false;
        // for each x
        for(Long x : hs_ArrayA) {
            if (x != t/2) { // distinct numbers x and y
                // find if (t-x) is present in the array.
                if (hs_ArrayA.contains(t - x)) {
                    targetPresent = true;
                    // System.out.println("Sum present: t = "+ t + " ,x = "+ x + " ,y = "+(t-x));
                    break;
                }
            }
        }
        return targetPresent;
    }

    public int findTargetValueCount(int start, int end){
        int count = 0;
        for (int t = start; t <= end; t++){
            // given t, find if there are distinct numbers x,y in the input file that satisfy x+y=t, if so, increment count.
            if (isTargetSumPresent(t))
                count++;
            if (t == (0)){
                System.out.println("t is 0");
            }
        }
        return count;
    }

    public static void main(String args[]){
        TwoSum ts = new TwoSum(args[0]);
        int start = -10000;
        int end = 10000;
        // int start = -10;
        // int end = 10;
        System.out.println("The number of target values t in the interval[ "+start+","+ end +" ] (inclusive) is: " +
                ts.findTargetValueCount(start, end));
    }
}
