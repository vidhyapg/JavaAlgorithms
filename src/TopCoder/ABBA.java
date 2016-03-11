package TopCoder;

/**
 * Created by vppriyad on 11/15/2015.
 */
public class ABBA {

    private String reverse(String A){
        char[] input = A.toCharArray();
        int n = A.length();
        int i = 0, j = n-1;
        while ( i < j){
            char t = input[i];
            input[i] = input[j];
            input[j] = t;
            i++;
            j--;
        }
        return new String(input);
    }
    /* The goal of the game is to find a sequence of valid moves that will change initial into target. There are two types of valid moves:

    Add the letter A to the end of the string.
    Reverse the string and then add the letter B to the end of the string.
            Return "Possible" (quotes for clarity) if there is a sequence of valid moves that will change initial into target. Otherwise, return "Impossible".
    */
    public String canObtain(String A, String B){
        if (canObtainB(A, B)){
            return "Possible";
        }else{
            return "Impossible";
        }
    }
    private boolean canObtainB(String A, String B){
        if(A.length() > B.length()){
            return false;
        }

        if (A.equals(B)){
            return true;
        }
        // if lengths are same and yet not equal, then they cannot be equal
        if (A.length() == B.length()) {
            return false;
        }

        if ((canObtainB(A.concat("A"), B) == true) ||
                (canObtainB(reverse(A).concat("B"), B) == true)){
            System.out.println(A);
            return true;
        }else{
            return false;
        }
    }

    public static void main(String args[]){
        ABBA ab = new ABBA();
        System.out.println(ab.canObtain("BBBBABABBBBBBA","BBBBABABBABBBBBBABABBBBBBBBABAABBBAA"));
        System.out.println(ab.canObtain("AB", "ABB"));
    }
}
