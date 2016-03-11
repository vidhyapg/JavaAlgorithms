import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

/**
 * Created by vppriyad on 5/10/2015.
 * ADS1.Quick Sort and Selection algorithms implementation.
 */
public class Quick {

    private void sort(Comparable a[], int lo, int hi){
        // bug 1: base case
        if (lo >= hi)
            return;
        comparisons += hi-lo;
        // swap (a, lo, hi);
        // find median
        int m = median(a, lo, hi);
        swap (a, lo, m);
        int p = partition(a, lo, hi);
        sort(a, lo, p-1);
        sort(a, p+1, hi);
    }

    // sort array with lot of duplicate keys
    private void sort_dup(Comparable a[], int lo, int hi){
        int lt, gt, i;
        if (lo >= hi)
            return;
        comparisons += hi-lo;
        Comparable v = a[lo];
        lt = i = lo; gt = hi; // note gt should start from hi
        // parition array with lot of duplicates
        while (i <= gt){ // note <=
            int cmp = a[i].compareTo(v);
            if (cmp < 0){
                swap(a, i,lt);
                i++; lt++;
            }
            else if (cmp > 0){
                swap(a, i, gt);
                gt--; // note do not increment i
            }
            else{
                i++;
            }
        }
        sort_dup(a, lo, lt-1);
        sort_dup(a, gt+1, hi);
    }

    public void sort_dup(Comparable a[]){
        sort_dup(a, 0, a.length-1);
        size = a.length-1;
    }


    public void sort(Comparable a[]){
        sort(a, 0, a.length-1);
        size = a.length-1;
    }

    private int partition(Comparable a[], int lo, int hi){
        // choosing the pivot needs to be randomized.
        Comparable pivot = a[lo];
        int i, j;
        i = j = lo+1;
        while(j <= hi){
            if (less(a[j], pivot)){ swap(a, i, j); i++; j++;}
            else
                j++;
        }
        swap(a, lo, i-1);
        return i-1;
    }

    public Comparable selection (Comparable a[], int k, boolean recursive){
        if (recursive) {
            System.out.println("Recursive Select");
            return selection_recursive(a, 0, a.length - 1, k);
        }
        else {
            System.out.println("Iterative Select");
            return selection_iter(a, k);
        }
    }
    // find the kth order statistic of the array a
    // out: kth element in sorted array of a
    // -1 if k is out of bounds.
    // run time: avg linear time, quadratic worst case time.
    private Comparable selection_iter (Comparable a[], int k){
        // TODO Random shuffle for efficiency.
        int lo = 0;
        int hi = a.length -1;
        if ((k > hi) || (k < lo))
            return -1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (k < j) {
                hi = j-1;
            } else if (k > j) {
                lo = j+1;
            } else {
                return a[k-1];
            }
        }
        // only one element remaining
        return a[k];
    }

    private Comparable selection_recursive (Comparable a[], int lo, int hi, int k){
        Comparable kth;
        if(hi <= lo)
            return a[hi];
        // TODO randomize pivot selection
        int j = partition(a, lo, hi);
        // k is in second part of partition
        if (k > j){
            kth = selection_recursive(a, j+1, hi, k-j);
        }
        // k is in first part of partition
        else if (k < j){
            kth = selection_recursive(a, lo, j-1, k);
        }
        // found k
        else{
            // bug: a[j]
            kth = a[lo+j];
        }
        return kth;
    }

    private boolean less(Comparable a, Comparable b){
        return (a.compareTo(b) < 0);
    }

    private void swap(Comparable a[], int i, int j){
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j]= temp;
    }

    private int median(Comparable a[], int i, int j){
        Comparable temp;
        // can be either i or j or mid, depending on whose a value is in the middle
        int median = 0;
        // k is middle with (j-i) = 2k
        int mid = 0;
        if (i < j)
            mid = (j+i)/2;
        // no change in answer with 1 or 2 element checks
        // array with only one element
        if (i == j)
            return i;
        // array with two elements. i and mid are same, return smallest
        if (j - i == 1)
        {
            if (less(a[j], a[i]))
                return j;
            else
                return i;
        }

        // i < j < mid
        // mid < j < i
        if ((less(a[i], a[j]) && less(a[j], a[mid])) || (less(a[mid], a[j]) && less(a[j], a[i])))
        {
            median = j;
        }
        // j < i < mid
        // mid < i < j
        else if ((less(a[j], a[i]) && less(a[i], a[mid])) || (less(a[mid], a[i]) && less(a[i], a[j])))
        {
            median = i;
        }
        // i < mid < j
        // j < mid < i
        else if  ((less(a[i], a[mid]) && less(a[mid], a[j])) || (less(a[j], a[mid]) && less(a[mid], a[i])))
        {
            median = mid;
        }
        return median;
    }

    public void show(Comparable a[]){
        System.out.print("[ ");
        for (int k = 0; k < a.length; k++) {
            System.out.print(a[k] + " ");
        }
        System.out.println(" ]");
    }

    public int getComparisons(){
        return comparisons;
    }

    public int getSize(){
        return size;
    }

    public static void main(String args[]){
        Quick quick = new Quick();
        StdOut.println("Quick Sort Start");
        // int[] oldArray = StdIn.readAllInts();
        //Integer[] a = new Integer[oldArray.length];
        Integer[] b = new Integer[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        int i = 0;
        /*for (int value : oldArray) {
            a[i++] = Integer.valueOf(value);
        }*/
        quick.sort_dup(b);
        //StdOut.println("Median of 3 element array is : " + quick.median(b, 0, b.length - 1));
        StdOut.println("Array after Sorting ");
        quick.show(b);
        StdOut.println("Total number of elements "+ quick.getSize());
        StdOut.println("Number of Comparisons is "+ quick.getComparisons());
    }

    private int comparisons;
    private int size;
}
