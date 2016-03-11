package EPI;
import java.util.*;
/**EPI 6.16
 * Created by vppriyad on 10/28/2015.
 */
public class Subset{

    // return subset array containing k elements (equally likely) out of // a, using O(1) storage and min random calls.
    public static ArrayList<Integer> findSubset(ArrayList<Integer> a, int k){
        int n = a.size();
        Random random = new Random();
        for (int i = 0 ; i < k; i++){
            int r = i + random.nextInt(n - i);
            swap(a, i, r);
        }
        for(int j = k; j < n; j++){
            a.remove(a.size()-1);
        }
        return a;
    }

    public static void swap( ArrayList<Integer> a, int i, int j){
        Integer xi = a.get(i);
        a.set(i, a.get(j));
        a.set(j, xi);
    }

    public static void main(String args[]){
        ArrayList<Integer> input = new ArrayList<>();
        int n = 20; int k = 10;
        Random rand = new Random();
        for (int i = 0; i < n; i++)
        {
            input.add(rand.nextInt(100));
        }

        System.out.println(input.toString());

        System.out.println(findSubset(input, k).toString());

    }

}