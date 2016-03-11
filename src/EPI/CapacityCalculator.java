package EPI;

import java.util.Arrays;

/**
 * Created by vppriyad on 10/1/2015.
 */
public class CapacityCalculator {

    public static int findMinCapacity(Integer[] h, int n){
        Integer min_cap = Integer.MAX_VALUE;
        Integer cap = 0;
        Integer prev = h[0];
        for (Integer i = 1; i < n; i++){
            // going up, charge loose
            if ((h[i] > h[i-1])){
                cap -= h[i]-h[i-1];
            }
            // going down, charge gain
            else if (h[i] < h[i-1]){
                // if (cap < MAX)
                cap += h[i-1]- h[i];
            }
            if (cap < min_cap)
                min_cap = cap;
            System.out.println("min_cap: "+min_cap + " cap: " + cap);
        }
        return Math.abs(min_cap);
    }
    public static int findMinCapacityRunning(Integer[] h, int n){
        Integer min_height = Integer.MAX_VALUE;
        Integer cap = 0;
        for (Integer height: h){
            cap = Math.max(cap, height - min_height);
            min_height = Math.min(min_height, height);
            System.out.println("min_height: " + min_height+ " cap: " + cap);
        }
        return cap;
    }

    public static void main (String args[]){
        Integer[] heights = {3, 5, 4, 10, 8, 6, 7, 9, 23, 34, 12, 9, 5, 4};
        System.out.println(Arrays.toString(heights));
        System.out.println("The min battery capacity (meters): "+ findMinCapacity(heights, heights.length));
        System.out.println(Arrays.toString(heights));
        System.out.println("The min battery capacity (meters): "+ findMinCapacityRunning(heights, heights.length));
    }
}
