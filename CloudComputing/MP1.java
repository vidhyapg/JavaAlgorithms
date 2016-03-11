package CloudComputing;
import java.io.*;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by vppriyad on 9/20/2015.
 * Coursera User ID: 3566241
 */
public class MP1 {
    Random generator;
    String userName;
    String inputFileName;
    String delimiters = " \t,;.?!-:@[](){}_*/";
    String[] stopWordsArray = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours",
            "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its",
            "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that",
            "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having",
            "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while",
            "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before",
            "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again",
            "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each",
            "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than",
            "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"};
    Set<String> stopWordsSet;

    void initialRandomGenerator(String seed) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        messageDigest.update(seed.toLowerCase().trim().getBytes());
        byte[] seedMD5 = messageDigest.digest();

        long longSeed = 0;
        for (int i = 0; i < seedMD5.length; i++) {
            longSeed += ((long) seedMD5[i] & 0xffL) << (8 * i);
        }

        this.generator = new Random(longSeed);
    }

    Integer[] getIndexes() throws NoSuchAlgorithmException {
        Integer n = 10000;
        Integer number_of_lines = 50000;
        Integer[] ret = new Integer[n];
        this.initialRandomGenerator(this.userName);
        for (int i = 0; i < n; i++) {
            ret[i] = generator.nextInt(number_of_lines);
        }
        return ret;
    }

    public MP1(String userName, String inputFileName) {
        this.userName = userName;
        this.inputFileName = inputFileName;
        stopWordsSet= new HashSet<String>(Arrays.asList(stopWordsArray));
    }

    private boolean isACommonWord(String token){
        // if token in stopWordsSet, return true, else false
        return stopWordsSet.contains(token);
    }

    public String[] process() throws Exception {
        String[] ret = new String[20];
        // to hold all lines read from the file
        ArrayList<String> lineList = new ArrayList<>();
        // to hold list of indexes to read from the file and process
        Integer[] indexes = getIndexes();

        // This map contains words and count mapping
        Map<String,Integer> map = new HashMap<>();


        // read the file line by line and store in Array List
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(inputFileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                lineList.add(line);
            }
            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            inputFileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + inputFileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        // for each line indexed in indexes array, count the word frequencies.
        for (int ln = 0; ln < indexes.length; ln++){
            // get line corresponding to index in index array.
            // same index may be processed multiple times
            if((line = lineList.get(indexes[ln])) != null) {
                StringTokenizer st = new StringTokenizer(line, delimiters);
                // for each token, make it lower case, trim leading and trailing spaces, ignore if it is in stopWordsArray
                // if not, store in Map, keeping count of frequency of occurrence.
                while (st.hasMoreTokens()) {
                    String token = st.nextToken().trim().toLowerCase();
                    if (isACommonWord(token))
                        continue;

                    if (map.containsKey(token)){
                        map.put(token,map.get(token)+1);
                    }
                    else{
                        map.put(token,1);
                    }
                } // end of each token
                // System.out.println(indexes[ln]);
            }
        } // end of each line


        // sort the map on frequency of occurrence of each word.
        ArrayList<Map.Entry<String,Integer>> top = new ArrayList(map.entrySet());
        MapComparator mc = new MapComparator<String,Integer>();
        Collections.sort(top,mc);

        // fill 20 Keys in ret
        int i = 0;
        for(Map.Entry<String, Integer> item: top){
            ret[i] = item.getKey();
            //System.out.println("Top "+ i +"th word is: "+ret[i]+ " freq is: "+item.getValue());
            i++;
            if (i>=20){
                break;
            }
        }
        //TODO
        return ret;
    }

    // Declare K and V as generic type parameters to ScoreComparator
    // Read this
    // https://docs.oracle.com/javase/tutorial/java/generics/methods.html
    //ScoreComparator takes two generic type arguments K and V. Map.Entry<K, V> is not a valid generic type definition, but you may well use it to bind to Comparator<T>'s T type.
    // Note that V must extend Comparable<V>, in order to be able to call compareTo() on o1.getValue().
    // can also replace K by ? in Comparator
    class MapComparator<K extends Comparable<K>, V extends Comparable<V>>
    // Let your class implement Comparator<T>, binding Map.Entry<K, V> to T
            implements Comparator<Map.Entry<K, V>> {
        public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
            // reverse sorting
            // Call compareTo() on V, which is known to be a Comparable<V>
            // Sort the list by descending order of frequency. If two words have same frequency, sort lexicographically.
            int freq_diff =  o2.getValue().compareTo(o1.getValue());
            if (freq_diff == 0){
                // note change in order of o1 and o2, for lexicographic sorting
                int lex_diff = (o1.getKey()).compareTo(o2.getKey());
                return lex_diff;
            }
            else{
                return freq_diff;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1){
            System.out.println("MP1 <User ID>");
        }
        else {
            String userName = args[0];
            String inputFileName = "inputMP1.txt";
            // System.out.println("File Path: "+ new File("siva.txt").getAbsolutePath());
            MP1 mp = new MP1(userName, inputFileName);
            String[] topItems = mp.process();
            for (String item: topItems){
                System.out.println(item);
            }
        }
    }
}
