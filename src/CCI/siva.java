package CCI;
/**
 * Created by vidhyapg on 3/26/2015.
 */
public class siva {

    private int i;

    siva()
    {
        i = 11;
    }

    private int get()
    {
        return i;
    }
    public static void main(String args[])
    {
        siva s = new siva();
        System.out.println("Hello Siva " +s.get());
    }
}
