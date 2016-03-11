package Interview;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vppriyad on 10/24/2015.
 */
public class TrieFindWords {
    private Node root;

    public TrieFindWords(){
        root = new Node();
    }

    public class Node{
       // char c;
        private HashMap<Character, Node> nodemap;
        public Node(){
            // this.c = c;
            nodemap = new HashMap<>();
        }
        public boolean childrenHasChar(char x){
            return this.nodemap.containsKey(new Character(x));
        }

        public Node getNodeForChar(char x) {
            return this.nodemap.get(new Character(x));
        }

        // insert a <char, Node> map in the curr node, and return Node.
        public Node insertNodeforChar(char x){
            Node child = new Node();
            this.nodemap.put(new Character(x), child );
            return child;
        }
    }


    public void insert(String word){
        Node curr = root;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            // System.out.println("inserting char: "+c + " at i: "+ i);
            curr = curr.insertNodeforChar(c);
        }
    }

    // if the String is empty, or if the dictionary is empty throw exception.
    public List<String> findValidWords(String input){
        StringBuilder resString = new StringBuilder();
        ArrayList<String> resList = new ArrayList<String>();
        if ((root == null) || (input == null)) {
            throw new InvalidParameterException();
        }
        findValidWords(root, input, 0, resString, resList);
        return resList;
    }
    // "CATSANDDOGS" -> "CATS", "AND", "DOGS"
    private void findValidWords(Node curr, String input, int i, StringBuilder resString, ArrayList<String> resList){
        if ((i > input.length()-1)|| (curr == null)){
            resList.add(resString.toString());
            return;
        }

        char currChar = input.charAt(i);
        //System.out.println("Looking for char: "+ currChar+ " at "+ i);
        if (curr.childrenHasChar(currChar)){
            // System.out.println("has char: "+ currChar);
            resString.append(currChar);
            Node next = curr.getNodeForChar(currChar);
            findValidWords(next, input, ++i, resString, resList);
        }
        // if char not found in map of intermediate node, flush out resString to list and start looking from root- this may be a new word.
        // if char not found in map of root, then this letter is not a valid starting letter, so look from next char.
        else {
            if(curr.equals(root)){
                //System.out.println("root");
                findValidWords(root, input, ++i, resString, resList);
            }
            else{
                //System.out.println("not root");
                resList.add(resString.toString());
                resString = new StringBuilder();
                // start finding word starting at i, from root
                findValidWords(root, input, i, resString, resList);
            }
        }
    }

    public void printNodes(){
        printNodes(root);
    }
    private void printNodes(Node curr){
        if (curr == null){
            return;
        }
        System.out.println(curr.nodemap.keySet());
        for (Node next: curr.nodemap.values())
            printNodes(next);
    }

    public static void main(String args[]){
        TrieFindWords trie = new TrieFindWords();
        trie.insert("CATS");
        trie.insert("AND");
        trie.insert("DOGS");
        trie.insert("CAN");

        trie.printNodes();

        List<String> outList = trie.findValidWords("ERRORANDCATSORDOGS");
        for (String outWord: outList){
            System.out.println(outWord);
        }
    }
}

