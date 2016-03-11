package ADS1;

import java.util.Random;

/**
 * Created by vppriyad on 8/2/2015.
 */
public class MinCut {

    int randomContraction(DynamicGraph G, long seed) {
        Random random = new Random();
        random.setSeed(seed);
        int N = G.V();
        //System.out.println("Starting random Contraction again with graph G with vertex: " + N);

        // while there are more than 2 vertices.
        while (G.V() > 2) {
            // uniformly randomly select two edges from the graph G.
            Integer u = new Integer(random.nextInt(N)+1);
            Integer v = new Integer(random.nextInt(N)+1);
            //!G.isValidEdge(u, v)
            while (!G.isValidEdge(u,v)) {
                // System.out.println("Checking if valid edge to contract u: "+ u + " v: "+ v);
                u = random.nextInt(N)+1;
                v = random.nextInt(N)+1;
            }
            //System.out.println("Found valid edge to contract u: "+ u + " v: "+ v);

            // here u - v is a valid edge in the graph G.
            // Contract u and v into single vertex, removing self loops.
            contractEdges(G, u, v);
        }
        /*
        System.out.println("End Contracted graph G has V:  "+ G.V() + " E: "+ G.E());
        System.out.println("End Contracted graph G is: ");
        System.out.println(G);*/

        return G.E();
    }

    void contractEdges(DynamicGraph G, Integer u, Integer v ){
        // There can be more than one edge u - v as parallel edges are allowed, remove all of them, there by eliminating self loops.
        G.removeEdges(u, v);
        // for all vertices w adj to v
        for (Integer w: G.adjacent(v)){
            // addEdge(u, w);
            G.addEdge(u, w);
        }
        // removeVertex(v); decrement V.
        G.removeVertex(v);
        /*System.out.println("After contracting edge u: "+ u +" v: "+ v);
        System.out.println(G);*/
    }

    public static void main(String args[]){
        String fileName = "kargerMinCut.txt";
        DynamicGraph Gin = new DynamicGraph(fileName);
        int N = Gin.V();
        // int N = 1;
        MinCut mincutA = new MinCut();
        System.out.println("Hello Siva");
        Random randmain = new Random();
        int cut = 0;
        int min_cutN = 0;
        for (int i = 0 ; i < N*N ; i++) {
            DynamicGraph G = new DynamicGraph(Gin);
            long seed = randmain.nextLong();
            // System.out.println("Random seed is: " + seed);
            cut = mincutA.randomContraction(G, seed);
            if (i == 0)
                min_cutN = cut;
            if ((i == (N*N/4))||(i == (N*N/2))||(i == (N*N/8) )||(i == (N*N/16))||(i == N))
                System.out.println("A cut of graph is: " + cut + " when i is: "+ i);
            //System.out.println("**********************************************");
            if (cut < min_cutN)
                min_cutN = cut;
        }
        System.out.println("Min cut of graph is: " + min_cutN);
    }
}
