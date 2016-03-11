package TopCoder;

import java.util.HashSet;

/**
 * Created by vppriyad on 11/30/2015.
 */
public class RelationClassifier {
    public String isBijection(int[] domain, int[] range){
        boolean bijection = true;
        HashSet<Integer> setDomain = new HashSet<>();
        HashSet<Integer> setRange = new HashSet<>();

        for (int i = 0; i < domain.length; i++){
            if (setDomain.contains(domain[i])) {
                bijection = false;
                break;
            }
            else
                setDomain.add(domain[i]);

            if (setRange.contains(range[i])) {
                bijection = false;
                break;
            }
            else
                setRange.add(range[i]);
        }
        return ((bijection == true)? "Bijection": "Not");
    }

    public static void main(String args[]){
        RelationClassifier rc = new RelationClassifier();
        int[] domain = {14, 12, 10, 13, 20, 18, 9, 17};
        int[] range = {18,  6,  8,  15, 2,  14, 10,13};
        System.out.println(rc.isBijection(domain,range));
    }
}
