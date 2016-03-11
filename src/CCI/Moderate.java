package CCI;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by vppriyad on 11/25/2015.
 */
public class Moderate {

    /*
    CCI 17.5 Game of Master of Mind
    return number of hits and psuedo hits given a guess and solution containing characters of
    {'R','G','B','Y'}
    eg
    guess:  GGRR
    sol:    RGBY
    hits:   1
    phits:  1
     */
    private int code(char c){
        switch(c){
            case 'R':
                return 0;
            case 'G':
                return 1;
            case 'B':
                return 2;
            case 'Y':
                return 3;
            default:
                return -1;
        }
    }

    private boolean isCodeValid(int code){
        if ((code < 0) || (code > 3)){
            return false;
        }
        return true;
    }
    // returns null if invalid input.
    public int[] findHitsAndPsuedoHitsSimple(String guess, String sol){
        // instead of using hashMap for a map that will contain at most 4 elements, use a simple array if integers for frequency and index mapping to a char.
        // error check for guess/Solution having a char not in RGBY
        // error check if lengths of guess and sol are not same
        if (guess.length() != sol.length()){
            return null;
        }

        // hits[0] is hits, hits[1] is psuedohits
        int[] hits = new int[2];
        int[] freq = new int[4];
        for (int i = 0; i < guess.length(); i++){
            char solChar = sol.charAt(i);
            char guessChar = guess.charAt(i);
            if (solChar == guessChar){
                // increment hits
                hits[0]++;
            }
            // increment the frequency value of the solChar
            else{
                int code = code(solChar);
                if(!(isCodeValid(code))){
                    return null;
                }
                freq[code]++;
            }
        }
        for (int i = 0; i < guess.length(); i++){
            char solChar = sol.charAt(i);
            char guessChar = guess.charAt(i);
            int code = code(guessChar);
            if(!(isCodeValid(code))){
                return null;
            }
            // if enough freq of a guessChar and it is not a hit, increment psuedo hit.
            if ((freq[code] > 0) && (solChar != guessChar)){
                hits[1]++;
                freq[code]--;
            }
        }
        return hits;


    }

    // not simple and missing error check on length
    public int[] findHitsAndPsuedoHits(String guess, String sol){
        // hits[0] is hits, hits[1] is psuedohits
        int[] hits = new int[2];

        HashMap<Character, Integer> mapCharFreq = new HashMap<>();
        for (int i = 0; i < sol.length(); i++){
            Character solChar = new Character(sol.charAt(i));
            Character guessChar = new Character(guess.charAt(i));
            if (solChar.equals(guessChar)){
                // increment hits
                hits[0]++;
            }else{
                if (mapCharFreq.containsKey(solChar)){
                    mapCharFreq.put(solChar, (mapCharFreq.get(solChar)+1));
                }else{
                    mapCharFreq.put(solChar, 1);
                }
            }
        }
        for (int i = 0; i < guess.length(); i++){
            Character guessChar = new Character(guess.charAt(i));
            Character solChar = new Character(sol.charAt(i));
            if ((mapCharFreq.containsKey(guessChar)) && (!(solChar.equals(guessChar)))){
                hits[1]++;
                if(mapCharFreq.get(guessChar) <= 1){
                    mapCharFreq.remove(guessChar);
                }
                else{
                    mapCharFreq.put(guessChar, (mapCharFreq.get(guessChar) - 1));
                }
            }
        }
        return hits;
    }
    public void testFindHitsAndPsuedoHits(){
        String guess =  "GGRRRBGR";
        String sol =    "RGBYRBYB";
        int[] hits;
        if ((hits = findHitsAndPsuedoHits(guess, sol))== null){
            System.out.println("Invalid Input");
        }else{
            for(int a: hits){
                System.out.println("Hits "+ a);
            }
        }

        if ((hits = findHitsAndPsuedoHitsSimple(guess, sol))== null){
            System.out.println("Invalid Input Simple");
        }else{
            for(int a: hits){
                System.out.println("Hits Simple: "+ a);
            }
        }
    }

    public static void main(String args[]){
        Moderate mod = new Moderate();
        mod.testFindHitsAndPsuedoHits();
    }
}
