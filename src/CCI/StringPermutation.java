package CCI;

/**
 * Created by vppriyad on 4/9/2015.
 * cracking coding interview 1.3
 */
import java.util.HashMap;
import java.util.Collection;

public class StringPermutation {
    /*
    *   given two strings , method to tell whether one is permutation of other
    */

    public boolean IsPermutation(String a, String b)
    {
        // create a hashmap of each character of a and its frequency.
        HashMap<Character, Integer> hash = new HashMap();
        for(int i = 0; i < a.length(); i++)
        {
            char ca = a.charAt(i);
            if (!(hash.containsKey(ca)))
                hash.put(ca,1);
            else
            {
                int val = hash.get(ca);
                hash.remove(ca);
                hash.put(ca,++val);
            }
        }


        // verify if each character of b is in the hashmap and if found decrement its frequency(if frequency 0 return false),
        // if not found return false.

        for(int j = 0; j < b.length(); j++)
        {
            char cb = b.charAt(j);
            if(hash.containsKey(cb))
            {
                int val = hash.get(cb);
                if (val == 0)
                    return false;
                hash.remove(cb);
                hash.put(cb,--val);
            }
            else
            {
                return false;
            }
        }

        // iterate over hashmap and check if all frequencies are exactly 0, else return false
        Collection<Integer> values = hash.values();

        for(int v : values)
        {
            if (v!=0)
                return false;
        }

        return true;
    }

    public static void main(String args[])
    {
        StringPermutation sp = new StringPermutation();
        String a = "sifviiinasvhhyui";
        String b = "ihviysvansfhuiii";
        System.out.println("String A: "+a+ " is permutation of String B: "+b+" ?:"+sp.IsPermutation(a,b));
    }

}
