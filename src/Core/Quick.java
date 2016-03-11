package Core;

/**
 * Created by vppriyad on 5/10/2015.
 * Quick Sort and Selection algorithms implementation.
 */
public class Quick {

    private static void sort(Comparable a[], int lo, int hi){
        // bug 1: base case
        if (lo >= hi)
            return;
        int p = partition(a, lo, hi);
        sort(a, lo, p-1);
        sort(a, p+1, hi);
    }
    public static void sort(Comparable a[]){
        sort(a, 0, a.length-1);
    }

    private static int partition(Comparable a[], int lo, int hi){
        // choosing the pivot needs to be randomized.
        Comparable pivot = a[lo];
        int i, j;
        i = j = lo+1;
        while(j <= hi){
            if (less(a[j], pivot)){
                swap(a, i, j);
                i++; j++;
            }
            else
                j++;
        }
        swap(a, lo, i-1);
        return i-1;
    }

    public static Comparable selection (Comparable a[], int k, boolean recursive){
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
    private static Comparable selection_iter (Comparable a[], int k){
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

    private static Comparable selection_recursive (Comparable a[], int lo, int hi, int k){
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

    private static boolean less(Comparable a, Comparable b){
        return (a.compareTo(b) < 0);
    }

    private static void swap(Comparable a[], int i, int j){
        Comparable temp;
        temp = a[i];
        a[i] = a[j];
        a[j]= temp;
    }

    public static void show(Comparable a[]){
        System.out.print("[ ");
        for (int k = 0; k < a.length; k++) {
            System.out.print(a[k] + " ");
        }
        System.out.println(" ]");
    }

    public static void main(String args[]){
        Integer[] src = new Integer[]{2, 9, 4, 3, 5, 7, 1, 6, 8};
        String[] src1 = new String[]{"Siva", "Vidhya", "Abc"};

        System.out.println("Array before Sorting: Length = " + src.length);
        show(src);

        int k = 8;
        System.out.println("kth "+k+": order statistic in Array: is recur "+ selection(src, k, true));
        System.out.println("kth "+k+": order statistic in Array: is iter "+ selection(src, k, false));

        Quick.sort(src);

        System.out.println("Array after Sorting");
        show(src);

    }
}
