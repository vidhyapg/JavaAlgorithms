package ADS1;
/*************************************************************************
 *  vppriyad run in command prompt
 *  Compilation:  javac Merge.java
 *  Execution:    java Merge < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   http://algs4.cs.princeton.edu/22mergesort/tiny.txt
 *                http://algs4.cs.princeton.edu/22mergesort/words3.txt
 *
 *  Sorts a sequence of strings from standard input using mergesort.
 *
 *  % more tiny.txt
 *  S O R T E X A M P L E
 *
 *  % java Merge < tiny.txt
 *  A E E L M O P R S T X                 [ one string per line ]
 *
 *  % more words3.txt
 *  bed bug dad yes zoo ... all bad yet
 *
 *  % java Merge < words3.txt
 *  all bad bed bug dad ... yes yet zoo    [ one string per line ]
 *
 *************************************************************************/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *  The <tt>Merge</tt> class provides static methods for sorting an
 *  array using mergesort.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/22mergesort">Section 2.2</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  For an optimized version, see {@link MergeX}.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Inversion {

    // This class should not be instantiated.
    private Inversion() { }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    private static long merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);
        long count = 0;

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];   // this copying is unnecessary
            else if (j > hi)               a[k] = aux[i++];
                // copying from second array when first aray is not empty.
            else if (less(aux[j], aux[i]))
            {
                a[k] = aux[j++];
                // inversions = number of remaining elements in first array
                count+= mid-i+1;
            }
            else
                a[k] = aux[i++];
        }

        // postcondition: a[lo .. hi] is sorted
        assert isSorted(a, lo, hi);
        return count;
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static long sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        long left = 0, right = 0, split = 0;
        if (hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        left = sort(a, aux, lo, mid);
        right = sort(a, aux, mid + 1, hi);
        split = merge(a, aux, lo, mid, hi);
        StdOut.println("Split AlgoStanford1.Inversion Count is" + split);
        return (left+right+split);
    }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static long sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        long count = sort(a, aux, 0, a.length-1);
        assert isSorted(a);
        return count;
    }


    /***********************************************************************
     *  Helper sorting functions
     ***********************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


    /***********************************************************************
     *  Check if array is sorted - useful for debugging
     ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }


    /***********************************************************************
     *  Index mergesort
     ***********************************************************************/
    // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
    private static int merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = index[k];
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                    index[k] = aux[j++];
            else if (j > hi)                     index[k] = aux[i++];
            else if (less(a[aux[j]], a[aux[i]])) index[k] = aux[j++];
            else                                 index[k] = aux[i++];
        }

        return 0;
    }

    /**
     * Returns a permutation that gives the elements in the array in ascending order.
     * @param a the array
     * @return a permutation <tt>p[]</tt> such that <tt>a[p[0]]</tt>, <tt>a[p[1]]</tt>,
     *    ..., <tt>a[p[N-1]]</tt> are in ascending order
     */
    public static int[] indexSort(Comparable[] a) {
        int N = a.length;
        int[] index = new int[N];
        for (int i = 0; i < N; i++)
            index[i] = i;

        int[] aux = new int[N];
        sort(a, index, aux, 0, N-1);
        return index;
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static int sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
        if (hi == lo) return 0;
        int mid = lo + (hi - lo) / 2;
        int left = 0, right = 0, split = 0;
        left = sort(a, index, aux, lo, mid);
        right = sort(a, index, aux, mid + 1, hi);
        split = merge(a, index, aux, lo, mid, hi);
        return (left+right+split);
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; mergesorts them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
        StdOut.println("Count of Inversions start ");
        int[] oldArray = StdIn.readAllInts();
        Integer[] a = new Integer[oldArray.length];
        int i = 0;
        for (int value : oldArray) {
            a[i++] = Integer.valueOf(value);
        }
        long count = Inversion.sort(a);
        //show(a);
        StdOut.println("Count of Inversions is: ");
        StdOut.println(count);
    }
}
