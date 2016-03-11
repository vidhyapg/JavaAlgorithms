package EPI;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * Created by vppriyad on 10/29/2015.
 */
public class StringOperations{

    // replace 'a' by "dd" and remove 'b' from a string and return result.
    // O(1) space and O(n) run time.
    // assume input array has enough space.
    public char[] replaceChars( char[] arr){
        int n = 0;
        int na = 0, nb = 0;
        // find n of final array.
        // n = x number of elements + number of a's - number of b's
        // delete all b's first. if final array size is less than init array size algo does not work.
        int s = 0; int x = 0;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] != '\u0000')
            {
                if (arr[i] != 'b'){
                    arr[x++] = arr[i];
                }
                if (arr[i] == 'a'){
                    na++;
                }
                s++;
            }
        }
        n = x + na;
        System.out.println("The number of elements in orig array is "+ s);
        System.out.println("The number of non b elements in orig array is "+ x);
        System.out.println("The number of as "+ na);
        System.out.println("The number of elements in final array is "+ n );
        System.out.println("The array with b removed "+ Arrays.toString(arr));

        // this is important. if there are extra invalid elements in the middle array due to removing b.
        // resize array to n.
        arr = Arrays.copyOf(arr, n);

        int j = n-1, i = x-1;
        while ((i >=0) && (j > 0)){
            if (arr[i] == 'a'){
                arr[j--] = 'd';
                arr[j--] = 'd';
            }else{
                arr[j--] = arr[i];
            }
            i--;
        }

        return arr;
    }
    // find all mneumonics ending at digit at n.
    private List<String> findAllMneumonicsAtN (String number, HashMap<Character, String> cellPad, int n){
        List<String> res = new ArrayList<String>();
        char digit = number.charAt(n);
        // check if nth digit is a valid digit
        if (!Character.isDigit(digit))
            throw new InvalidParameterException("char at"+ n + "is not a digit: "+ digit);

        if (n == 0){
            for (char c: cellPad.get(number.charAt(n)).toCharArray()){
                res.add(String.valueOf(c));
            }
            return res;
        }

        List<String> tres = findAllMneumonicsAtN(number, cellPad, n-1);
        for (String tstr : tres){
            for (char c: cellPad.get(digit).toCharArray()){
                res.add(tstr+c);
            }
        }

        return res;
    }

    public List<String> findAllMneumonics(String number, HashMap<Character, String> cellpad){
        List<String> allres = null;
        try {
            allres = findAllMneumonicsAtN(number, cellpad, number.length() - 1);
        }catch(InvalidParameterException e){
            System.out.println(e);
        }
        return allres;
    }

    public void testFindAllMneumonics(){
        HashMap<Character, String> cellpad = new HashMap<>();
        String number = "23";
        cellpad.put('0', "");
        cellpad.put('1', "");
        cellpad.put('2', "ABC");
        cellpad.put('3', "DEF");
        cellpad.put('4', "GHI");
        cellpad.put('5', "JKL");
        cellpad.put('6', "MNO");
        cellpad.put('7', "PQRS");
        cellpad.put('8', "TUV");
        cellpad.put('9', "WXYZ");

        for(String resStr : findAllMneumonics(number, cellpad)){
            System.out.println(resStr);
        }
    }

    public static void main(String args[]){
        StringOperations so = new StringOperations();;
        /*
        int n= 20; int x = 10;
        char[] input = new char[20];
        Random rand = new Random();
        input[x-1] = 'b';
        for(int i = 0; i < x-1 ; i++){
            input[i] = (char) ('a' + (rand.nextInt(10)));
        }
        System.out.println("The ini array is "+ Arrays.toString(input));
        System.out.println("The fin array is " + Arrays.toString(so.replaceChars(input)));
        */
        so.testFindAllMneumonics();
    }
}
