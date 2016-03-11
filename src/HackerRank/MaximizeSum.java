package HackerRank;

/**
 * Created by vppriyad on 9/12/2015.
 */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MaximizeSum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long T = scanner.nextLong();
        int N = 0;
        long M = 0;
        long[] arr = new long[500000];
        while (T-- > 0) {
            N = scanner.nextInt(); // no of elements
            M = scanner.nextLong(); // modulo by
            for (int i = 0; i < N; i++) {
                arr[i] = scanner.nextLong();
            }

            long[] sumToIndex = new long[N];
            sumToIndex[0] = arr[0] % M;
            for (int i = 1; i < N; i++) {
                sumToIndex[i] += (sumToIndex[i-1] + arr[i]) % M;
            }

            TreeMap<Long, Integer> treeMap = new TreeMap<Long, Integer>();
            Long diff = Long.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                if (!treeMap.containsKey(sumToIndex[i])) {
                    treeMap.put(sumToIndex[i], i);
                    Long higherKey = treeMap.higherKey(sumToIndex[i]);
                    if (higherKey != null) {
                        Long temp = M + (sumToIndex[i] - higherKey);
                        if (temp > diff) {
                            diff = temp;
                        }
                    }
                }
            }


            long max = Long.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                //System.out.println(sumToIndex[i]);
                if (max < sumToIndex[i])
                    max = sumToIndex[i];
            }

            max = max > diff ? max : diff;
            System.out.println(max);

        }
    }
}