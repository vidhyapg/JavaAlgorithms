package CCI;

/**CCI 9.1
 * Created by vppriyad on 11/3/2015.
 */
public class Stairs{

    // find number of ways to climb stairs with n steps when at each  // step you can take either 1, 2, or 3 number of hops.
    public int findWays(int n){
        if (n < 0)
            return 0;
        else if (n == 0)
            return 1;

        return findWays(n-1)+ findWays(n-2)+ findWays(n-3);
    }

    private long findWaysDPR(int n, int[] map){
        if (n < 0)
            return 0;
        else if (n == 0)
            return 1;
        // if n > 0, can find from map if it is cached.
        else{
            // value cached
            if (map[n] > -1){
                return map[n];
            }
            // value not cached. Compute and store.
            else{
                map[n] = findWays(n-1) + findWays(n-2) + findWays(n-3);
                return map[n];
            }
        }

    }

    public long findWaysDPR(int n){
        int[] map = new int[n+1];
        for (int i = 0; i < map.length; i++){
            map[i] = -1;
        }
        return findWaysDPR(n, map);
    }

    private long findWaysDPI(int n, int[] map){
        // initialize map 0, 1, 2
        map[0] = 1; // if n = 0 invalid parameter. but to be consistent with prev sol return 1.
        map[1] = 1; // {1}
        map[2] = 2; // {2}, {1,1}
        map[3] = 4; // {3}, {1,2}, {2,1}, {1,1,1}

        // map[n] has the ans.
        for (int i = 4; i <=n; i++){
            map[i] = map[i-1] + map[i-2] + map[i-3];
        }

        // map[n] has the ans.
        return map[n];
    }

    public long findWaysDPI(int n){
        int[] map = new int[n+1];
        for (int i = 0; i < map.length; i++){
            map[i] = -1;
        }
        return findWaysDPI(n, map);
    }

    public static void main(String args[]){
        Stairs stair = new Stairs();
        int n = 15;
        System.out.println("R: Possible ways to climb "+n+" stairs is: "+ stair.findWays(n));
        System.out.println("DPR: Possible ways to climb "+n+" stairs is: "+ stair.findWaysDPR(n));
        System.out.println("DPI: Possible ways to climb "+n+" stairs is: "+ stair.findWaysDPI(n));
    }

}