package EPI;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * EPI 16.1
 * Created by vppriyad on 10/18/2015.
 */
public class TowerOfHanoi {
    // move top n disks from p1 to p2 using p3 as temp peg.
    // p1, p2, p3 are all stacks with capacity to hold n Items.
    public void moveDisks(int n, Stack P1, Stack P2, Stack P3 ){
        if (n <= 0)
            return;
        if (P1.isEmpty())
            return;
        moveDisks(n-1, P1, P3, P2);
        moveTop(P1, P2);
        moveDisks(n-1, P3, P2, P1);
    }

    private void moveTop(Stack P1, Stack P2){
        P2.push(P1.pop());
    }

    public static void main(String args[]){
        Stack<Integer> P1 = new Stack<>();
        Stack<Integer> P2 = new Stack<>();
        Stack<Integer> P3 = new Stack<>();
        TowerOfHanoi toh = new TowerOfHanoi();
        P1.push(5);
        P1.push(4);
        P1.push(3);
        P1.push(2);
        P1.push(1);
        Integer[] p1AsArray = new Integer[P1.size()];
        P1.toArray(p1AsArray);
        System.out.println("P1: " + Arrays.toString(p1AsArray));

        toh.moveDisks(P1.size(), P1, P2, P3);

        Integer[] p2AsArray = new Integer[P2.size()];
        P2.toArray(p2AsArray);
        System.out.println("P2: " + Arrays.toString(p2AsArray));

    }
}
