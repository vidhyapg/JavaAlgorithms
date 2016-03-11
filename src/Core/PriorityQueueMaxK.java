package Core;
import java.security.InvalidParameterException;
import java.util.*;
/**
 * Created by vppriyad on 10/8/2015.
 */
public class PriorityQueueMaxK {

    // extract K max elements from prorityQueue q.
    public LinkedList<Integer> extractKMax(Integer[] q, int k, int n){
        if (k > n) {
            throw new InvalidParameterException();
        }
        PriorityQueue<Entry> candidates = new PriorityQueue();
        LinkedList<Integer> maxList = new LinkedList<Integer>();
        int index = 1;
        candidates.add(new Entry(q[index], index));
        Entry nextMax;
        Integer nextMaxLeftIndex;
        Integer nextMaxRightIndex;
        System.out.println("PQ is"+ Arrays.toString(q));
        System.out.println("n is: "+ n + ", k is: " + k);
        int i = 0;
        while ((i < k) && (!(candidates.isEmpty()))){
            nextMax = candidates.poll();
            System.out.println(i+"th Max Element is: "+ nextMax.getValue());

            maxList.addFirst(nextMax.getValue());
            nextMaxLeftIndex = nextMax.getIndex()*2;
            nextMaxRightIndex = nextMax.getIndex()*2 + 1;

            System.out.println("Next Right Element"+ nextMaxRightIndex);
            if (nextMaxRightIndex < n) {
                candidates.add(new Entry(q[nextMaxLeftIndex], nextMaxLeftIndex));
                candidates.add(new Entry(q[nextMaxRightIndex], nextMaxRightIndex));
            }
            i++;
        }
        return maxList;
    }

    public static void main(String args[]){
        Integer[] q = {0, 10, 7, 4, 5, 3, 2};
        Integer[] q1 = {0, 10, 9, 7, 8, 3, 5, 6, 1, 4};
        Integer[] q2 ={0, 10};
        PriorityQueueMaxK pqMax = new PriorityQueueMaxK();
        System.out.println(pqMax.extractKMax(q2, 0, q2.length));
    }

    public class Entry implements Comparator<Entry>, Comparable<Entry>{
        private Integer value;
        private Integer index;

        public Entry(Integer value, Integer index){
            this.value = value;
            this.index = index;
        }

        public Integer getValue(){
            return value;
        }
        public Integer getIndex(){
            return index;
        }
        public int compareTo(Entry T1){
            return T1.value.compareTo(this.value);
        }
        public int compare(Entry T1, Entry T2) {
            return (T2.value.compareTo(T1.value));
        }
    }
}
