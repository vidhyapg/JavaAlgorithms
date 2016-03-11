package EPI;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by vppriyad on 10/11/2015.
 */
public class IntersectArrays {

    private int[] listToIntArray(List<Integer> list){
        int i = 0;
        int[] a = new int[list.size()];

        for (Integer element: list){
            a[i++] = element.intValue();
        }
        return a;
    }

    // O(m+n)
    // even if n << m, if all elements in n is larger than B, have to traverse m elements.
    // uses the fact that both arrays are sorted and n~=m
    public int[] intersectTwoSortedArrays(int[] A, int[] B){
        int n = A.length;
        int m = B.length;
        int i = 0, j = 0;
        LinkedList<Integer> C = new LinkedList<Integer>();

        while ((i < n)  && (j < m))
        {
            if (A[i] == B[j]){
                if ((i == 0) || (A[i] != A[i-1])){
                   C.addLast(A[i]);
                }
                i++; j++;
            }
            else if (A[i] < B[j])
                i++;
            else if (A[i] > B[j])
                j++;
        }

        return listToIntArray(C);
    }

    // O(n log m) where n << m. Not using fact that both are sorted.
    private int[] intersectSmallAndBig(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        // n is smaller than m
        LinkedList<Integer> C = new LinkedList<Integer>();
        // for each element in smaller array, binary search if it is present in the bigger array, if so add to C,
        // ignore if previous element was same to remove duplicates.
        int prev = -1;
        for (int x: A){
            if (x != prev) {
                if (Arrays.binarySearch(B, x) >= 0)
                    C.addLast(x);
            }
            prev = x;
        }

        return listToIntArray(C);
    }

    public int[] intersectDifferentSizeSortedArrays(int[] A, int[] B){
        int n = A.length;
        int m = B.length;
        return ((n < m)? intersectSmallAndBig(A, B): intersectSmallAndBig(B, A));
    }

    public static void main(String args[]){
        int[] A = {9, 10, 10, 12, 14, 15, 16, 17, 18, 21};
        int[] B = {8, 9, 10, 10, 13, 14, 15, 18, 20, 21};
        // int[] A = {9};
        IntersectArrays iA = new IntersectArrays();
        System.out.println(Arrays.toString(iA.intersectTwoSortedArrays(A, B)));;
        System.out.println(Arrays.toString(iA.intersectDifferentSizeSortedArrays(A, B)));;
    }
}
