package EPI;

/**
 * Created by vppriyad on 10/27/2015.
 */
public class BitOperations {

    // multiplication without + or *.
    // a. b =  a * (express in terms of sums of powers of two)
    // a * 2i + a * 2(i-1) + ... + a. 2 + a

    static int multiply (int a, int b){
        int sum = 0; boolean negative = false;
        if (a < 0) {
            a = -a;
            negative = true;
        }
        if (b < 0) {
            b = -b;
            negative = true;
        }
        while(b > 0){
            // if LSB of b is 1, then add to sum a*2i
            if ((b&1)> 0)
                sum = add(sum, a);
            a <<= 1;
            b >>= 1;
        }
        sum = negative ? -sum : sum;
        return sum;
    }

    // addition without + or any arithmetic.
    // add bit wise is xor ^
    // carry in and carry out.
    // does not support negative operands
    static int add(int a, int b){
        int sum = 0;
        int carryin = 0, carryout;
        int tempa = a, tempb = b;
        int ak, bk;
        int k = 1;
        while((tempa > 0) || (tempb > 0)){
            ak = a & k; bk = b & k;
            carryout = (ak & bk) | (carryin & ak) | (carryin & bk);
            sum |= (ak ^ bk ^ carryin);
            carryin = carryout << 1;
            tempa >>= 1; tempb >>= 1;
            k <<= 1;
        }
        return sum | carryin;
    }

    // pow (x,y) , O(log y), compare with O(y) brute force- x*x*x*... for y times
    static double pow (double x, int y){
        long power = y;
        // if (y is negative, x = 1/x, y = -y)
        if (y < 0){
            x = 1/x;
            power = -power;
        }
        double result = 1;
        while(power > 0){
            if ((power & 1)> 0){
                result *= x;
            }
            // note: it is not result* = result. Multiply with result only if LSB is 1.
            // height of tree is log(y) or number of bits in y.
            // bottom up, multiply x by x and keep multiplying prods like x.p(y/8), x.p(y/4), x.p(y/2), until you get to the top x.p(y).
            x *= x;
            power >>= 1;
        }

        return result;
    }

    // convert a string s1 from base b1 to b2 and return string
    static String convert(String s1, int b1, int b2){
        // special cases: negative and s1 = '0'
        boolean neg = (s1.charAt(0) == '-');
        // convert s1 from b1 to decimal integer sum;
        int sum = 0;
        for (int i = (neg? 1: 0); i < s1.length(); i++){
            sum *= b1;
            // char to integer.can be integer char or char.
            char r = s1.charAt(i);
            sum +=  Character.isDigit(r) ? (r - '0') : (r - 'A') + 10;
        }
        // convert decimal sum to string s2 in base b2;
        StringBuilder sb = new StringBuilder();
        while(sum > 0){
            // integer to char
            int digit = (sum % b2);
            boolean number = ((digit < 10) && (digit >=0 ));
            char r = number ? (char)('0' + digit) : (char)('A'+ (digit-10));
            sb.insert(0, r);
            sum /= b2;
        }
        if (neg)
            sb.insert(0, '-');
        String ans = sb.toString();
        return (ans.isEmpty())? "0": ans;
    }

    public static void main(String args[]){
        int x = 34;
        int y = 22;
        System.out.println("The power of x: "+ x+" and y: "+ y+ " is: "+ pow(x,y));
        System.out.println("The product of x: "+ x+" and y: "+ y+ " is: "+ multiply(x, y));
        System.out.println("The sum of x: "+ x+" and y: " + y+ " is: "+ add(x, y));
        String A = "-323AB";
        int b1 = 16; int b2 = 2;
        System.out.println("The conversion of A "+ A +" in base b1: " + b1 + " to base b2: "+ b2+ " is: "+ convert(A, b1, b2));
    }

}
