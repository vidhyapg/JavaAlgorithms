package EPI;


/**
 * Created by vppriyad on 10/3/2015.
 *  Prob 7.1 First edition
 */
public class StringConverter {

    public static String intToString(int val){
        // 123 to “123”
        // -123 to “-123”
        boolean negative = false;
        StringBuilder str_buf = new StringBuilder();

        if (val == 0){
            return "0";
        }

        if (val < 0){
            negative = true;
            val = -val;
        }

        while (val > 0){
            str_buf.append(val % 10);
            val /= 10;
        }

        if (negative)
            str_buf.append('-');

        return str_buf.reverse().toString();
    }

    public static int stringToInt(String str){
        // “123” to 123
        // “-123” to -123
        int i = 0;
        boolean negative = false;
        if (str.isEmpty()){
            throw new IllegalArgumentException();
        }
        if (str.charAt(0) == '-'){
            negative = true;
            i++;
        }
        int digit;
        int sum = 0;
        for (; i < str.length(); i++){
            char c = str.charAt(i);
            if ((c> '9') || (c < '0')){
                throw new IllegalArgumentException();
            }
            digit = c - '0';
            sum  = sum * 10 + digit;
        }
        return (negative)? -sum: sum;
    }

    public static void main(String args[]){
        String str = "-132432";
        int val = 234324;

        int conv_int = stringToInt(str);
        String conv_str = intToString(val);

        System.out.println("Str to Int: "+ conv_int);
        System.out.println("Int to Str: " + conv_str );
    }

}
