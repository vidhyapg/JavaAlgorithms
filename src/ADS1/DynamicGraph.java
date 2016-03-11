package ADS1;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

// vppriyad modified implementation to use LinkedList of LinkedLists.
/*************************************************************************
 *  Compilation:  javac DynamicGraph.java
 *  Execution:    java DynamicGraph input.txt
 *  Dependencies: Bag.java In.java System.out.java
 *  Data files:   http://algs4.cs.princeton.edu/41undirected/tinyG.txt
 *
 *  A graph, implemented using an array of sets.
 *  Parallel edges and self-loops allowed.
 *
 *  % java DynamicGraph tinyG.txt
 *  13 vertices, 13 edges
 *  0: 6 2 1 5
 *  1: 0
 *  2: 0
 *  3: 5 4
 *  4: 5 6 3
 *  5: 3 4 0
 *  6: 0 4
 *  7: 8
 *  8: 7
 *  9: 11 10 12
 *  10: 9
 *  11: 9 12
 *  12: 11 9
 *
 *  % java DynamicGraph mediumG.txt
 *  250 vertices, 1273 edges
 *  0: 225 222 211 209 204 202 191 176 163 160 149 114 97 80 68 59 58 49 44 24 15
 *  1: 220 203 200 194 189 164 150 130 107 72
 *  2: 141 110 108 86 79 51 42 18 14
 *  ...
 *
 *************************************************************************/


/**
 *  The <tt>DynamicGraph</tt> class represents an undirected graph of vertices
 *  named 0 through <em>V</em> - 1.
 *  It supports the following two primary operations: add an edge to the graph,
 *  iterate over all of the vertices adjacent to a vertex. It also provides
 *  methods for returning the number of vertices <em>V</em> and the number
 *  of edges <em>E</em>. Parallel edges and self-loops are permitted.
 *  <p>
 *  This implementation uses an adjacency-lists representation, which
 *  is a vertex-indexed array of {@link Bag} objects.
 *  All operations take constant time (in the worst case) except
 *  iterating over the vertices adjacent to a given vertex, which takes
 *  time proportional to the number of such vertices.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/41undirected">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DynamicGraph {
    private int V;
    private int E;
    private HashMap<Integer, LinkedList<Integer>> adj;

    /**
     * Initializes an empty graph with 0 vertices and 0 edges.
     * */
    public DynamicGraph() {
        this.V = 0;
        this.E = 0;
        adj = new HashMap<Integer, LinkedList<Integer>>();
    }

    /**
     * Initializes a graph from an input stream.
     There are 200 vertices labeled 1 to 200. The first column in the file represents the vertex
     label, and the particular row (other entries except the first column) tells all the vertices
     that the vertex is adjacent to. So for example, the 6th row looks like :
     "6	155	56 52 120	......". This just means that the vertex with label 6 is adjacent to (i.e.,
     shares an edge with) the vertices with labels 155,56,52,120,......,etc
     * @param in the input stream
     * @throws java.lang.IndexOutOfBoundsException if the endpoints of any edge are not in prescribed range
     * @throws java.lang.IllegalArgumentException if the number of vertices or edges is negative
     */
    public DynamicGraph(String fileName) {
        this();
        // int V = in.readInt();
        // int E = in.readInt();
        // if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");

        // read from in, put first one after \n in K, remaining after K in list V until \n. Create a map entry <K,V>
        // addVertex(<K,V>).

        // The name of the file to open.
        // System.out.println(new File(".").getAbsolutePath());

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            int edge_doublecount = 0;
            //int vertex_count = 0;
            while((line = bufferedReader.readLine()) != null) {
                // System.out.println(line);
                String[] currentLine = line.trim().split("\\s+");
                //System.out.println(currentLine[0]);
                // first one is the vertex.
                Integer K = Integer.parseInt(currentLine[0]);

                LinkedList<Integer> edges = addNewVertex(K);
                for (int i = 1; i < currentLine.length; i++) {
                    edges.add(Integer.parseInt(currentLine[i]));
                    //System.out.println(currentLine[i]);
                    edge_doublecount++;
                }
                //addVertex(K, edges);
                //vertex_count++;
                //adj.put(K, new LinkedList<Integer>());
            }

            this.E = edge_doublecount/2;
            //this.V = vertex_count;
            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }

    /**
     * Initializes a new graph that is a deep copy of <tt>G</tt>.
     * @param G the graph to copy
     */
    public DynamicGraph(DynamicGraph G) {
        this();
        //this.V = G.V();
        this.E = G.E();
        // had a bug when I just set <K,V> as entry.getKey() and entry.getValue(). It was assigning references from old graph G to new graph.
        // We need to just copy the values.
        for (Map.Entry<Integer, LinkedList<Integer>> entry : G.adj.entrySet()){
            LinkedList<Integer> adjlist = addNewVertex(entry.getKey());
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : entry.getValue()) {
                reverse.push(w);
            }
            for (int w : reverse) {
                 // same as below this.adj.get(entry.getKey()).add(w);
                adjlist.add(w);
            }
            // addVertex(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Returns the number of vertices in the graph.
     * @return the number of vertices in the graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in the graph.
     * @return the number of edges in the graph
     */
    public int E() {
        return E;
    }


    // throw an IndexOutOfBoundsException unless v is the adj Hash Map
    private void validateVertex(Integer v) {
        if (!adj.containsKey(v))
            throw new IllegalArgumentException("vertex " + v + " is not in the DynamicGraph");
    }

    // return false if there is no edge u-v in the graph
    public boolean isValidEdge(Integer u, Integer v) {
        // vertex u not in G
        if (!adj.containsKey(u))
            return false;
        // vertex v not in G
        if (!adj.containsKey(v))
            return false;

        // both are in G, but vertex v not in adj list of u, then there is no edge u-v
        if (!adj.get(u).contains(v))
            return false;
        // if none of the above
        return true;
    }

    /**
     * Adds the undirected edge v-w to the graph.
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     * @throws java.lang.IllegalArgumentException() unless both vertices are in the graph
     */
    public void addEdge(Integer v, Integer w) {
        validateVertex(v);
        validateVertex(w);

        // find key v, and append w to list mapped to that key
        this.adj.get(v).add(w);
        // find key w, and append v to list mapped to that key
        this.adj.get(w).add(v);
        E++;
    }

    /**
     * Removes first occurence of undirected edge v-w from the graph.
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     * @throws java.lang.IllegalArgumentException() unless both vertices are in the graph
     */
    public void removeEdge(Integer v, Integer w) {
        validateVertex(v);
        validateVertex(w);
        E--;

        // find key v, and remove first occurence of w from the list mapped to that key
        this.adj.get(v).remove(w);
        // find key w, and remove first occurence of v from the list mapped to that key
        this.adj.get(w).remove(v);
    }

    /**
     * Removes all occurences of undirected edge v-w from the graph.
     * There can be more than one edge v - w as parallel edges are allowed.
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     * @throws java.lang.IllegalArgumentException() unless both vertices are in the graph
     */
    public void removeEdges(Integer v, Integer w){
        validateVertex(v);
        validateVertex(w);
        int i = 0;
        int j = 0;
        // find key v, and repeatedly remove w from the list mapped to that key
        while(adj.get(v).remove(w)){i++;}
        // find key w, and repeatedly remove v from the list mapped to that key
        while(adj.get(w).remove(v)){j++;}
        // number of occurences of w in v's adj list should be same as that of v in w's adj list.
        assert(i==j);
        // update number of edges.
        E = E-i;
    }


    /**
     * Removes first occurence of edge v-w from the graph, by updating only w's adj list.
     * Used for special case of deleting a vertex v. Still decrement E.
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     * @throws java.lang.IllegalArgumentException() unless both vertices are in the graph
     */
    public void removeEdgeFromDest(Integer v, Integer w) {
        validateVertex(v);
        validateVertex(w);
        E--;
        this.adj.get(w).remove(v);
        //System.out.println("Done removing edge from: "+ w +" to: "+ v);
    }

    /**
     * Adds the vertex v to the graph.
     * @param v vertex
     * @throws java.lang.IndexOutOfBoundsException unless 0 <= v <= V
     */
    public LinkedList<Integer> addNewVertex(Integer v) {
        // insert a new element at index v to represent vertex v.
        LinkedList<Integer> adjlist = new LinkedList<Integer>();
        adj.put(v, adjlist);
        this.V++;
        return adjlist;
    }

    /*
    // used only during graph building in constructor, so dont add edges from adj vertices or update E or V.
    public void addVertex(Integer K, LinkedList<Integer> V){
        this.adj.put(K,V);
        // this.V++;
        // E += V.size();
    }
    */

    /**
     * Removes the vertex v from the graph.
     * @param v vertex
     * @throws java.lang.IllegalArgumentException() unless vertex v is in the graph
     * */
    public void removeVertex(Integer v) {
        validateVertex(v);
        // parse through adj list of v and remove all edges from w to v
        for (int w : adj.get(v)) {
            //System.out.println("removing edge from: "+ v +" to: "+ w);
            removeEdgeFromDest(v, w);
        }
        // Removes the element at the specified position in this list. Element is a linked list
        this.adj.remove(v);
        V--;
    }
    /**
     * Returns the vertices adjacent to vertex <tt>v</tt>.
     * @return the vertices adjacent to vertex <tt>v</tt> as an Iterable
     * @param v the vertex
     * @throws java.lang.IllegalArgumentException() unless vertex v is in the graph
     * */
    public Iterable<Integer> adjacent(int v) {
        validateVertex(v);
        return this.adj.get(v);
    }

    /**
     * Returns the degree of vertex <tt>v</tt>.
     * @return the degree of vertex <tt>v</tt>
     * @param v the vertex
     * @throws java.lang.IllegalArgumentException() unless vertex v is in the graph
     */
    public int degree(int v) {
        validateVertex(v);
        return adj.get(v).size();
    }


    /**
     * Returns a string representation of the graph.
     * This method takes time proportional to <em>E</em> + <em>V</em>.
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *    followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + E + " edges " + NEWLINE);

        for (Map.Entry<Integer, LinkedList<Integer>> entry : adj.entrySet()){
            // addVertex(entry.getKey(), entry.getValue());
            Integer v = entry.getKey();
            s.append(v + ": ");
            for (Integer w : entry.getValue()) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * Unit tests the <tt>DynamicGraph</tt> data type.
     */
    public static void main(String[] args) {
        String fileName = "ListGraphInput.txt";
        DynamicGraph G1 = new DynamicGraph(fileName);
        System.out.println("G1 Start: ");
        System.out.println(G1);

        DynamicGraph G = new DynamicGraph(G1);
        System.out.println("G Start: ");
        System.out.println(G);
/*

        int u = 4, v = 2;
        // There can be more than one edge u - v as parallel edges are allowed, remove all of them, there by eliminating self loops.
        G.removeEdges(u, v);
        // for all vertices w adj to v
        for (Integer w: G.adjacent(v)){
            // addEdge(u, w);
            G.addEdge(u, w);
        }
        // removeVertex(v); decrement V.
        G.removeVertex(v);
        System.out.println("G After contracting edge u: "+ u +" v: "+ v);
        System.out.println(G);

        System.out.println("G1 After contracting edge u: "+ u +" v: "+ v);
        System.out.println(G1);
*/

        /*
        u = 4;
        v = 3;
        // There can be more than one edge u - v as parallel edges are allowed, remove all of them, there by eliminating self loops.
        G.removeEdges(u, v);
        // for all vertices w adj to v
        for (Integer w: G.adjacent(v)){
            // addEdge(u, w);
            G.addEdge(u, w);
        }
        // removeVertex(v); decrement V.
        G.removeVertex(v);
        System.out.println("After contracting edge u: "+ u +" v: "+ v);
        System.out.println(G); */




        int v1 =2, v2 = 3, v3 = 1, v4 = 4, v5 = 5, v6 = 2, v7 = 3;

        System.out.println("Add edge from: "+ v1 +" to: "+ v2);
        G.addEdge(v1, v2);
        System.out.println(G);


        System.out.println("removing edge from: "+ v3 +" to: "+ v4);
        if (G.isValidEdge(new Integer(v3), new Integer(v4)))
            G.removeEdge(v3, v4);
        System.out.println(G);


        System.out.println("Add Vertex: "+ v5);
        G.addNewVertex(v5);
        System.out.println(G);


        System.out.println("Add edge from: "+ v5 +" to: "+ v6);
        G.addEdge(v5,v6);
        System.out.println(G);


        System.out.println("Add edge from: "+ v5 +" to: "+ v7);
        G.addEdge(v5,v7);
        System.out.println(G);


        System.out.println("Remove all edges from: "+ v1 +" to: "+ v2);
        G.removeEdges(v1,v2);
        System.out.println(G);


        System.out.println("Remove Vertex: "+ v1);
        G.removeVertex(v1);
        System.out.println(G);
        System.out.println("Remove Vertex: "+ v2 + " and "+ v3);
        G.removeVertex(v2);
        G.removeVertex(v3);
        System.out.println(G);
        System.out.println("Remove Vertex: "+ v4 + " and "+ v5);
        G.removeVertex(v4);
        G.removeVertex(v5);
        System.out.println(G);

        System.out.println("G1 After all changes");
        System.out.println(G1);
    }
}
