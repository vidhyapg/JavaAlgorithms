package EPI;

import java.util.Arrays;

/**
 * Created by vppriyad on 10/5/2015.
 */
public class BinarySearchDup {

    public static int binarySearchFirst(int[] a, int k){
        int result = -1;
        int l = 0;
        int h = a.length-1;
        int m;

        while(l<=h){
            m = l + ((h-l)/2);
            if (k < a[m]){
                h = m-1;
            }
            else if (k > a[m]){
                l = m+1;
            }
            else{
                result = m;
                h = m-1;
            }
        }
        return result;
    }

    public static void main(String args[]){
        int[] a = {2, 5, 5, 6, 6, 6, 7, 8, 8, 9, 9, 9};
        // int [] a = new int[0];
        System.out.println(Arrays.toString(a));
        System.out.println(binarySearchFirst(a, 6));
        System.out.println(binarySearchFirst(a, 9));
        System.out.println(binarySearchFirst(a, 8));
    }
}
