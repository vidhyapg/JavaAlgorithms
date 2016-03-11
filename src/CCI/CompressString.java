package CCI;

/**
 * Created by vppriyad on 8/1/2015.
 */
public class CompressString {

    String compress(String str){
        int compresslen = 0;
        int count = 1;
        int len = str.length();
        compresslen = countCompression(str);
        System.out.println("compressed string length is: " + compresslen);

        if (compresslen >= len)
            return str;

        StringBuffer newStr = new StringBuffer();
        char prev = str.charAt(0);
        for (int i = 1; i < len ; i++){
            // same as prev char, keep incrementing count
            if (str.charAt(i) == prev){
                count++;
            }
            // different char, increment final size by 1 for char and count size.
            // append prev and count to newStr
            // reset count to 1.
            else {
                newStr.append(prev);
                newStr.append(count);
                prev = str.charAt(i);
                count = 1;
            }
        }
        // very last set of repeated characters
        newStr.append(prev);
        newStr.append(count);
        return newStr.toString();
    }


    int countCompression(String str){
        // base case
        if (str == null || str.isEmpty()) return 0;
        int size = 0;
        int count = 1;
        int len = str.length();
        char prev = str.charAt(0);
        for (int i = 1; i < len ; i++){
            // same as prev char, keep incrementing count
            if (str.charAt(i) == prev){
                count++;
            }
            // different char, increment final size by 1 for char and count size.
            // append prev and count to newStr
            // reset count to 1.
            else {
                prev = str.charAt(i);
                size += 1 + String.valueOf(count).length();
                count = 1;
            }
        }
        // very last set of repeated characters
        size += 1 + String.valueOf(count).length();
        return size;
    }

    String compressEnhanced(String str){
        int compresslen = 0;
        int count = 1;
        int len = str.length();
        compresslen = countCompression(str);
        System.out.println("compressed string length is: " + compresslen);

        if (compresslen >= len)
            return str;

        char newStr[] = new char[compresslen];
        char prev = str.charAt(0);
        int index = 0;
        for (int i = 1; i < len ; i++){
            // same as prev char, keep incrementing count
            if (str.charAt(i) == prev){
                count++;
            }
            // different char, increment final size by 1 for char and count size.
            // append prev and count to newStr
            // reset count to 1.
            else {
                index = setChar(newStr, prev, count, index);
                prev = str.charAt(i);
                count = 1;
            }
        }
        // very last set of repeated characters
        index = setChar(newStr, prev, count, index);
        return String.valueOf(newStr);
    }

    int setChar(char[] newStr, char x, int count, int index){
        newStr[index] = x;
        index++;

        // convert count to String and to char array and then copy the chars one by one in newStr array
        // copy from biggest to smallest digits
        char[] countArray = String.valueOf(count).toCharArray();
        for (char c : countArray){
            newStr[index] = c;
            index++;
        }
        return index;
    }

    public static void main(String args[]) {
        String str = "aabccccccccccaaa";
        CompressString cs = new CompressString();
        System.out.println("The string before compression : " + str);
        System.out.println("The compressed string is : " + cs.compressEnhanced(str));
    }


}
