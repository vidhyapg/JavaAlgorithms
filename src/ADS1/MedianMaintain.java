package ADS1;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by vppriyad on 8/23/2015.
 */
public class MedianMaintain {

    MaxPQ<Integer> lowHeap;
    MinPQ<Integer> highHeap;
    int n;
    int l;
    int h;

    public MedianMaintain(){
        lowHeap = new MaxPQ<Integer>();
        highHeap = new MinPQ<Integer>();
        n = 0;
        l = 0;
        h = 0;
    }

    /*
    If a element is smaller than (max(low heap)) then insert in low heap.
    If it is bigger than (min(high heap)) then insert in high heap.
    If it between these two values- can put in either one.(say high heap)
     */
    public void addInteger(Integer a){
        if((!lowHeap.isEmpty()) && (a > lowHeap.max())){
            highHeap.insert(a);
            h++;
        }
        else if ((!highHeap.isEmpty()) && (a < highHeap.min())){
            lowHeap.insert(a);
            l++;
        }
        else{
            highHeap.insert(a);
            h++;
        }
        n++;

        // boolean hightolow = false;
        // if h > l + 1 or l > h + 1
        if (h > l + 1){
            // if highHeap has more elements, extract min from highHeap and insert to lowHeap.
            rebalance(lowHeap, highHeap, true);
        }
        else if (l > h + 1){
            // if lowHeap has more elements, extract max from lowHeap and insert to highHeap.
            rebalance(lowHeap, highHeap, false);
        }
    }

    private void rebalance(MaxPQ lowHeap, MinPQ highHeap, boolean hightolow){
        // if highHeap has more elements, extract min from highHeap and insert to lowHeap.
        if(hightolow){
            lowHeap.insert(highHeap.delMin());
            h--;
            l++;
        }
        // if lowHeap has more elements, extract max from lowHeap and insert to highHeap.
        else{
            highHeap.insert(lowHeap.delMax());
            l--;
            h++;
        }
    }
    /*
    Median- if I is even, max(low heap)(either one is fine), if I is odd, min(high heap since this has one more element).
    */
    public Integer getMedian(){
        int med_index = 0;
        // n is even
        if (n%2==0){
            med_index = n/2;
        }
        else{
            med_index = (n+1)/2;
        }
        if (med_index <= l) {
            return lowHeap.max();
        }
        else {
            return highHeap.min();
        }
    }

    public static void main(String args[]){
        MedianMaintain mm = new MedianMaintain();
        int sum = 0;
        int median = 0;
        // populate hash table with Longs in file
        // hs_ArrayA = new HashSet<Long>();
        String filename = args[0];
        String line = null;
        int i = 1;
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bf = new BufferedReader(fileReader);
            while((line = bf.readLine())!= null){
                // add integer to the mm.
                mm.addInteger(Integer.parseInt(line.trim()));
                // get the current Median
                median = mm.getMedian();
                System.out.println("median at i: "+i+ " is: "+median);
                // find sum of medians
                sum += median;
                i++;
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

        System.out.println("Sum of medians: "+sum+" last 4 digits is "+ sum%10000);
    }
}
