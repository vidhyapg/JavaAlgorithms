package EPI;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    

import java.util.*;

/**
 * Created by vppriyad on 11/2/2015.
 */
public class ClosestStars{

    // run time: O(N logK), space: O(K)
    // input: list of stars and its distance from earth.
    // StarName: (1 to N)
    // distance is integer
    // output: list of k stars closest to earth/
    public Collection<Star> findKStars(ArrayList<Star> starlist, int k){
        // max heap: Star, add comparator to compare them by dist.
        // default min heap, top is star with smallest dist.
        // for max heap, top is star with greatest dist.
        Comparator starComp = new StarComparator();
        PriorityQueue<Star> maxH = new PriorityQueue<>(k, starComp);

        for (Star eachStar: starlist){
            if(maxH.size() <= k){
                maxH.add(eachStar);
            }
            else{
                // if dis of max is greater than eachStar
                // using reverse comparator
                if (starComp.compare(eachStar, maxH.peek())>0)
                {
                    maxH.poll();
                    maxH.add(eachStar);
                }
            }
        }
        return maxH;
    }

    public static void main(String args[]){
        int n = 100; int k = 10;
        ClosestStars cs = new ClosestStars();
        ArrayList<Star> starList = new ArrayList<>();

        for (int i= n; i > 0; i--){
            Star eachStar = new Star(Integer.toString(i), i);
            starList.add(eachStar);
        }

        System.out.println("The k closest stars are: ");
        for (Star eachStar: cs.findKStars(starList, k)){
            System.out.println(eachStar.getDist());
        }
    }
}



// two stars natural order of comparison is by name match.
class Star implements Comparable<Star>{
    private String name;
    private Integer dist;
    Star(String n, Integer d){
        name = n;
        dist = d;
    }

    public Integer getDist() {
        return this.dist;
    }

    public int compareTo(Star s1){
        if (s1 == null)
            throw new NullPointerException();
        return this.name.compareTo(s1.name);
    }
}
class StarComparator implements Comparator<Star> {
    // changed for max heap.
    public int compare(Star s1, Star s2){
        return s2.getDist() - s1.getDist();
    }

}
