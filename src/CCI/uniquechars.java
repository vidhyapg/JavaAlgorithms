import java.util.HashMap;

/**
 * Created by vppriyad on 3/26/2015.
 * Cracking Coding Interview 1.1
 */

/*
 * Determine if the string has all unique characters.
 */
public class uniquechars {
    private String str;
    uniquechars()
    {
         str = "asdfghjklqwertyuiopzxcvbnmd";
        // str = "";
    }

    private boolean IsUniqueUsingArray()
    {
        boolean[] map = new boolean[128];

        for (int i =0 ; i < str.length(); i++)
        {
            Character charac = str.charAt(i);
            if (map[charac])
            {
                return false;
            }
            else
            {
                map[charac] = true;
            }
        }
        return true;
    }

    private boolean IsUniqueUsingBits()
    {
        // 4 bytes = 32 bits - 26 alphabets
        int map = 0;
        // optimization
        if (str.length() > 128)
            return false;

        for (int i =0 ; i < str.length(); i++)
        {
            int val = str.charAt(i)-'a';
            if ((map & (1 << val))> 0)
            {
                return false;
            }
            else
            {
                map |= (1 << val);
            }
        }
        return true;
    }


    private boolean IsUniqueUsingHashmap()
    {
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        for (int i =0 ; i < str.length(); i++)
        {
            Character charac = str.charAt(i);
            if(!(map.containsKey(charac)))
            {
                map.put(charac, 1);
            }
            else
            {
                return false;
            }
        }
        return true;
    }
    public static void main(String args[])
    {
        uniquechars uc = new uniquechars();
        System.out.println("IsUniqueUsingBits "+uc.str +" "+ uc.IsUniqueUsingBits());
        System.out.println("IsUniqueUsingArray "+uc.str +" "+ uc.IsUniqueUsingArray());
        System.out.println("IsUniqueUsingHashmap "+uc.str +" "+ uc.IsUniqueUsingHashmap());
    }
}
