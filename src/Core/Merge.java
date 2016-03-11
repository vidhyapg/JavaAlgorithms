package Core;
import java.util.Arrays;
/**
 * Created by vppriyad on 5/10/2015.
 */
public class Merge{

    public static void sort(Comparable[] src, Comparable[] aux, int lo, int hi){
        if (hi <= lo) return;
        int mid = (hi + lo)/2 ;
        sort(src, aux, lo, mid);
        sort(src, aux, mid+1, hi);
        merge(src, aux, lo, mid, hi);
    }

    public static void sort (Comparable[] src){
        Comparable[] aux = new Comparable[src.length];
        sort(src, aux, 0, src.length-1);
    }

    // changes in src should be such that values from lo to hi should sorted and remaining unchanged.
    private static void merge (Comparable[] src, Comparable[] aux, int lo, int mid, int hi){
        int i = lo;
        int j = mid+1;

        for (int k = lo; k <= hi ; k++){
            aux[k] = src[k];
        }
        // bug was k = 0
        for (int k = lo; k <= hi; k++){
            if (j > hi)
                src[k] = aux[i++];
            else if (i > mid)
                src[k] = aux[j++];
            // bug was checking for src instead of aux
            else if (less(aux[j], aux[i]))
                src[k] = aux[j++];
            else
                src[k] = aux[i++];
        }
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
        // Integer[] src = new Integer[]{4, 3, 9, 8, 1, 2, 7, 5, 6, 12, 10, 34, 54, 17, 19};
        String[] src = new String[]{"Siva", "Vidhya", "Abc"};
        System.out.println("Array before sorting with length "+ src.length);
        Merge.show(src);

        Merge.sort(src);

        System.out.println("Array after sorting");
        Merge.show(src);
    }

}
