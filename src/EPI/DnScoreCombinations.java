package EPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vppriyad on 10/21/2015.
 */
public class DnScoreCombinations {
    /* * example of working solution in EPI 17.1
    scoreWays: 2 3 7
totalScore: 12

combinations
score = 2
0	1
1	0
2	1
3	0
4	1
5	0
6	1
7	0
8	1
9	0
10	1
11	0
12	1

score = 3
0	1
1	0
2	1
3	0+1
4	1+0
5	0+1
6	1+1
7	0+1
8	1+1
9	0+2
10	1+1
11	0+2
12	1+2


score = 7
j = 7
0	1
1	0
2	1
3	0+1
4	1+0
5	0+1
6	1+1
7	0+1+1
8	1+1+0
9	0+2+1
10	1+1+1
11	0+2+1
12	1+2+1

    */
    // not going anywhere- visit later
    public int findScoreCombinations(List<Integer> w, int S){
        int sum = 0;
        if (w.size() == 1){
            Integer first = w.remove(0);
            System.out.println("last first is: " + first + " sum is: "+ S);
            if (S % first == 0)
                return 1;
            else
                return 0;
        }
        if (!(w.isEmpty())){
            Integer first = w.remove(0);
            System.out.println("first is: " + first + " sum is: "+ S);
            for (int i = 0; i < S/first; i++){
                sum += findScoreCombinations(w, S-(first*i));
            }
        }
        return sum;
    }

    public static void main (String args[]){
        DnScoreCombinations score = new DnScoreCombinations();
        Integer[] w = {2, 3, 7};
        List<Integer> scoreArray = new ArrayList<Integer>(Arrays.asList(w));

        int S = 12;
        System.out.println("Total number of combinations with: w: "+ Arrays.toString(w) + " S: "+ S+ " is: "+ score.findScoreCombinations(scoreArray, S));
    }


    /*
       int[][] dc = new int[w.length][S];
        // initialize dc[j][i]

        for (int i = 1; i < S; i++){
            dc[0][i] = (i % w[0] == 0)? 1: 0;
        }

        for (int j = 1; j < w.length; j++){
            for (int i = 1; i < S; i++){
                int sum = dc[j - 1][i];
                for (int x = i; x < i/w[j]; x++) {
                    if ((i == 2) && (j == 4)){
                        System.out.println("(i - (w[j] * x))"+ (i - (w[j] * x)));
                    }
                    if ((i - (w[j] * x)) >=0 )
                        sum += dc[j - 1][i - (w[j] * x)];
                }
                dc[j][i] = sum;
            }
        }
        for (int i = 0; i < S; i++) {
            System.out.print(i+ " ");
        }
        System.out.println();
        for (int j = 0; j < w.length; j++) {
            for (int i = 0; i < S; i++) {
                System.out.print(dc[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("w.length is: " + w.length+ ", S is: " + S);
        return dc[w.length-1][S-1];
     */
}
