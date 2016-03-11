package EPI;
import java.util.Random;
/**
 * EPI 6.22
 * Created by vppriyad on 10/28/2015.
 */
public class SpiralArray{

    private static void printOutline(int n, int x, int[][] a){
        //System.out.println("x is: "+x +"and n is: "+ n );
        if (x > (n-1)){
            return;
        }
        if (n == 1){
            System.out.print(a[x][x]+ " ");
        }
        int i = x, j= x;

        for (j = x; j <= n-1; j++){
            System.out.print(a[i][j] + " ");
        }
        j = n-1;
        for (i = x+1; i <= n-1; i++){
            System.out.print(a[i][j]+ " ");
        }
        i = n-1;
        for (j = n-2; j >= x; j--){
            System.out.print(a[i][j]+ " ");
        }
        j = x;
        for (i = n-2; i >= x+1; i--){
            System.out.print(a[i][j]+ " ");
        }
        //System.out.println("End x is: "+x +"and n is: "+ n );
        printOutline(n-1,x+1,a);
    }

    public static void printSpiral(int[][] a, int n){
        printOutline(n, 0, a);
    }

    public static void main(String args[]){
        int n = 6;
        int[][] input = new int[n][n];
        Random rand = new Random();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                input[i][j] = rand.nextInt(100);
                System.out.print(input[i][j]+ " ");
            }
            System.out.println();
        }
        printSpiral(input, n);
    }

}