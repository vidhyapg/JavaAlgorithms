package EPI;

import java.util.HashMap;

/**
 * Created by vppriyad on 11/2/2015.
 */
public class Magazine{
    private String M;
    HashMap<Character, Integer> freqMap = new HashMap<>();
    // run time: O(n)
    public Magazine(String M){
        this.M = M;
        // build freqMap
        for(char c: M.toCharArray()){
            Character letter = new Character(c);
            if (freqMap.containsKey(letter)){
                int freq = freqMap.get(letter);
                freqMap.put(letter,++freq);
            }else{
                freqMap.put(letter, 1);
            }
        }
    }

    // run time: O(k)
    public boolean isWordPossible(String L){
        System.out.println(L);
        for(char c: L.toCharArray()){
            Character letter = new Character(c);
            if (freqMap.containsKey(letter)){
                int freq = freqMap.get(letter);
                if (freq > 0)
                    freqMap.put(letter,--freq);
                else
                    return false;
            }else{
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]){
        String magText = "there was a video";
        Magazine mag =  new Magazine(magText);

        System.out.println("Word test: "+ mag.isWordPossible("siva"));
        System.out.println("Word test: "+ mag.isWordPossible("sivam"));
        System.out.println("Word test: "+ mag.isWordPossible("sivav"));

    }

}
