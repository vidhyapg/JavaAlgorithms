package Interview;

import java.util.*;

/**
 * Created by vppriyad on 11/4/2015.
 * find the smallest number which fits the signature
 1. every element appears only once
 2. element >=1
 */
public class Signature {
    // each element can be of range 1 to MAX
    private int MAX = Integer.MAX_VALUE;
    // number of chars in signature processed so far.
    private int count = 0;
    // actual signature
    private char[] sign;
    // total number of characters in signature
    private int num;

    Signature (char[] sign){
        this.sign = sign;
        num = sign.length+1;
    }

    public Collection<Integer> findSmallestMatch(){
        for (int i = 1; i < MAX; i++){
            Stack<Integer> res = new Stack<>();
            if (findSmallestMatch(i, res))
                return res;
        }
        // if reached MAX and did not get the smallest number satisfying the criteria , return null
        return null;
    }

    private boolean findSmallestMatch(int pv, Stack<Integer> res){
        res.push(pv);
        count++;

        if (count == num){
            return true;
        }

        if (sign[count-1] == 'D'){
            for (int i = 1; i < pv; i++){
                // this candidate next value is already in the list.
                if (res.search(i) > -1)
                    continue;
                if (findSmallestMatch(i, res))
                    return true;
            }
        }else{
            for (int i = pv+1; i < MAX ; i++){
                // this candidate next value is already in the list.
                if (res.search(i) > -1)
                    continue;
                if(findSmallestMatch(i, res))
                    return true;
            }
        }
        // none of the ranges worked out for the pv, so pop it from res and dec count and ret false.
        res.pop();
        count--;
        return false;
    }

    public String getSignature(){
        return new String(sign);
    }

    public static void main(String args[]){
        char[] signature = "DIDIDID".toCharArray();
        Signature s = new Signature(signature);
        System.out.print("The smallest element that matches the signature: "+ s.getSignature() + " is: ");
        for(Integer e: s.findSmallestMatch()){
            System.out.print(e+ " ");
        }
        System.out.println();
    }

}
