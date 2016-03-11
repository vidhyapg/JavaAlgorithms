package EPI;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

/**
 * EPI 13.1
 * Given list of words in a dictionary, print groups of anagrams in that list.
 * Created by vppriyad on 10/11/2015.
 */
public class AnagramFinder {

    // Run time O(mn logm)
    // m is the mazimum length of any word.
    // n is the total number of words in the array
    public void printGroupofAnagrams(String[] dictionary){
        // for each word, sort it and get the key
        // insert word in the list mapped with key in the hashMap <String, LinkedList<String>>
        // <SortedWord, List of Anagram words with same set of characters as key>
        HashMap<String, LinkedList<String>> hashDict = new HashMap<>();
        String key;
        char[] cArray;
        for (String word : dictionary){
            cArray = word.toCharArray();
            // O(m logm)
            Arrays.sort(cArray);
            key = new String(cArray);
            if (hashDict.containsKey(key)){
                hashDict.get(key).addFirst(word);
            }
            else{
                LinkedList<String> lofAnagrams = new LinkedList<>();
                lofAnagrams.addFirst(word);
                hashDict.put(key, lofAnagrams);
            }
        }

        // iterate and print all values of lists in all keys in the hashMap. Those are the groups of anagrams.
        // O(mn)
        for (LinkedList<String> anagramlists: hashDict.values()){
            if (anagramlists.size()>=2){
                for (String anagram: anagramlists){
                    System.out.println(anagram);
                }
                System.out.println();
            }
        }
    }

    public static void main(String args[]){
        int n = 8;
        String[] dictionary = new String[n];
        AnagramFinder anagramFinder = new AnagramFinder();
        dictionary[0] = "god";
        dictionary[1] = "team";
        dictionary[2] = "siva";
        dictionary[3] = "mate";
        dictionary[4] = "dog";
        dictionary[5] = "meat";
        dictionary[6] = "eleven plus two";
        dictionary[7] = "twelve plus one";
        anagramFinder.printGroupofAnagrams(dictionary);
    }

}
