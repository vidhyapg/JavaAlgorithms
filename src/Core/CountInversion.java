package Core;
import java.util.Arrays;
/**
 * Created by vppriyad on 5/10/2015.
 */
public class CountInversion{

    public static int sort_count(Comparable[] src, Comparable[] aux, int lo, int hi){
        if (hi <= lo) return 0;
        int mid = (hi + lo)/2 ;
        int x, y, z;
        x = sort_count(src, aux, lo, mid);
        y = sort_count(src, aux, mid +1, hi);
        z = count_split_inv(src, aux, lo, mid, hi);
        return (x+y+z);
    }

    public static int sort_count (Comparable[] src){
        Comparable[] aux = new Comparable[src.length];
        return sort_count(src, aux, 0, src.length - 1);
    }

    // changes in src should be such that values from lo to hi should sorted and remaining unchanged.
    private static int count_split_inv (Comparable[] src, Comparable[] aux, int lo, int mid, int hi){
        int i = lo;
        int j = mid+1;
        int num_inv = 0;

        for (int k = lo; k <= hi ; k++){
            aux[k] = src[k];
        }
        // bug was k = 0
        for (int k = lo; k <= hi; k++){
            if (j > hi)
                src[k] = aux[i++];
            else if (i > mid)
                src[k] = aux[j++];
            // number of split inversion when copied from 2nd array = number of remaining elements in first array.
            else if (less(aux[j], aux[i])) {
                src[k] = aux[j++];
                num_inv += mid - i+1; // including i
            }
            else
                src[k] = aux[i++];
        }

        return num_inv;
    }

    private static boolean less (Comparable v, Comparable w){
        return (v.compareTo(w) < 0);
    }
    private static void show(Comparable a[]){
        System.out.print("[ ");
        for (int k = 0; k < a.length; k++){
            System.out.print(a[k] + " ");
        }
        System.out.println("]");
    }
    public static void main(String args[]){
        Integer[] src1 = new Integer[]{4, 3, 9, 8, 1, 2, 7, 5, 6, 12, 10, 34, 54, 17, 19};
        Integer[] src = new Integer[]{4, 3, 2};
        // String[] src = new String[]{"Siva", "Vidhya", "Abc"};
        System.out.println("Array before sorting with length "+ src.length);
        CountInversion.show(src);
        System.out.println("Number of inversions after sorting: " + CountInversion.sort_count(src));
        CountInversion.show(src);
    }

}
