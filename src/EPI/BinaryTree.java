package EPI;

import java.security.InvalidParameterException;
import java.security.spec.InvalidParameterSpecException;
import java.util.*;

/**
 * EPI 9:10
 * EPI 10.4, 10.5
 * Created by vppriyad on 11/1/2015.
 */
// print nodes in binary tree in order of depth.
public class BinaryTree<Item>{
    private Node<Item> root;

    public BinaryTree(){
        root = null;
    }
    public BinaryTree(Node node){
        root = node;
    }


    public void printInDepthOrder(){
        Queue<Node> procQ = new LinkedList<Node>();
        if (root == null){
            System.out.println("Tree is empty");
            return;
        }
        int count = 1;
        procQ.add(root);
        try{
            while(!(procQ.isEmpty())){
                //System.out.println("The queue has  n elements: "+procQ.size());
                Node p = procQ.remove();
                count--;
                System.out.print(p.getValue() + " ");
                if (p.getLeft() != null){
                    procQ.add(p.getLeft());
                }
                if (p.getRight() != null){
                    procQ.add(p.getRight());
                }
                if(count == 0){
                    System.out.println();
                    count = procQ.size();
                }
            }
        }catch(NoSuchElementException e){
            System.out.println(e);
        }

    }
    public void printInDepthOrderAlternating(){
        boolean forward = false;
        Queue<Node> procQ = new LinkedList<Node>();
        Stack<Node> procS = new Stack<Node>();
        if (root == null){
            System.out.println("Tree is empty");
            return;
        }
        int count = 1;
        procQ.add(root);
        try{
            while(!(procQ.isEmpty())){
                //   System.out.println("The queue has  n elements: "+procQ.size());
                Node p = procQ.remove();
                count--;
                if(forward){
                    System.out.print(p.getValue() + " ");
                }else{
                    procS.push(p);
                }

                if (p.getLeft() != null){
                    procQ.add(p.getLeft());
                }
                if (p.getRight() != null){
                    procQ.add(p.getRight());
                }
                if(count == 0){
                    if(!forward){
                        while(!(procS.isEmpty())){
                            System.out.print(procS.pop().getValue() + " ");
                        }
                    }
                    System.out.println();
                    count = procQ.size();
                    forward ^= true;
                }
            }
        }catch(NoSuchElementException e){
            System.out.println(e);
        }
    }

    public void printInBottomUpDepthOrder(){
        Queue<Node> procQ = new LinkedList<Node>();
        Stack<String> sStack = new Stack<>();
        if (root == null){
            System.out.println("Tree is empty");
            return;
        }
        StringBuilder sb = new StringBuilder();
        int count = 1;
        procQ.add(root);
        try{
            while(!(procQ.isEmpty())){
                //System.out.println("The queue has  n elements: "+procQ.size());
                Node p = procQ.remove();
                count--;
                sb.append(p.getValue().toString()+ " ");

                if (p.getLeft() != null){
                    procQ.add(p.getLeft());
                }
                if (p.getRight() != null){
                    procQ.add(p.getRight());
                }
                if(count == 0){
                    sStack.push(sb.toString());
                    sb = new StringBuilder();
                    // System.out.println();
                    count = procQ.size();
                }
            }
        }catch(NoSuchElementException e){
            System.out.println(e);
        }

        while(!(sStack.isEmpty())){
            System.out.println(sStack.pop());
        }

    }

    public void add(Node parent, Node child, Orientation relation){
        if (relation == Orientation.LEFT){
            parent.setLeft(child);
        }else{
            parent.setRight(child);
        }
    }
    public Node findLCA(Node root, Node L, Node R){
        if (root == null)
            return null;
        if ((root == L) || (root == R))
            return root;
        Node lRes = findLCA(root.getLeft(), L, R);
        Node rRes = findLCA(root.getRight(), L, R);

        if ((lRes != null) && (rRes != null)){
            return root;
        }
        if(lRes != null)
            return lRes;
        return rRes;
    }


    // returns the LCA for a and b in node lca
    // returns null in lca of either a or b is not present in the tree
    // find LCA when node do no have parent pointers
    private void findLCA(Node root, Node a, Node b, boolean[] found, List<Node> lca){
        // do a post order traversal recursively.
        // if a and b are found in the subtrees then the first root is the LCA.

        if(root == null){
            //lca = null;
            return;
        }

        boolean[] foundLeft = {false, false, false};
        boolean[] foundRight = {false, false, false};


        findLCA(root.getLeft(), a, b, foundLeft, lca);
        // if LCA is already in left subtree
        if(foundLeft[2]) {
            found[2] = true;
            return;
        }
        findLCA(root.getRight(), a, b, foundRight, lca);
        // if LCA is already in right subtree
        if(foundRight[2]) {
            found[2] = true;
            return;
        }

        for(int i =0; i <found.length; i++){
            found[i] = foundLeft[i] || foundRight[i];
        }

        if (root == a)
            found[0] = true;
        if (root == b)
            found[1] = true;

        // if A and B both are found in different subtrees, or in root, then root is LCA.
        System.out.println("Checking root: "+ root.getValue()+" "+found[0]+ " "+ found[1]);
        if(found[0] && found[1]){
            lca.add(root);
            found[2] = true;
            System.out.println("LCA is:  "+ root.getValue());
        }
    }

    // returns the LCA for a and b
    // throws invalid arg exception if a or b is not present in the tree
    public Node findLCA(Node a, Node b ){

        Node res = findLCA(root, a, b);
        if (res == null)
            throw new InvalidParameterException("res is null");
        return res;

        /*boolean[] foundAB = {false, false, false};
        List<Node> res = new ArrayList<>();
        if ((a == null) || (b == null))
            throw new InvalidParameterException("a or b is null");
        System.out.println(" Find LCA of A: "+ a.getValue() +" and B: "+ b.getValue());
        findLCA(root, a, b, foundAB, res);
        if (res.isEmpty())
            throw new InvalidParameterException("res is null");
        return res.remove(0);*/
    }


    public static void main(String args[]){

        Node<Integer> n0 = new Node<>(9);
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n5 = new Node<>(5);
        Node<Integer> n6 = new Node<>(6);
        BinaryTree<Integer> bt = new BinaryTree(n0);
        bt.add(bt.root, n1, Orientation.LEFT);
        bt.add(bt.root, n2, Orientation.RIGHT);
        bt.add(n1, n3, Orientation.LEFT);
        bt.add(n1, n4, Orientation.RIGHT);
        bt.add(n2, n5, Orientation.LEFT);
        bt.add(n2, n6, Orientation.RIGHT);

        System.out.println("printInDepthOrder");
        bt.printInDepthOrder();
        /*System.out.println("printInBottomUpDepthOrder");
        bt.printInBottomUpDepthOrder();
        System.out.println("printInDepthOrderAlternating");
        bt.printInDepthOrderAlternating();*/

        try{
            Node lca = bt.findLCA(n3, n6);
            if (lca != null)
                System.out.println("The LCA of a: "+ n3.getValue()+ " and b: "+ n6.getValue()+ " is: "+ lca.getValue());
        }catch (InvalidParameterException e){
            System.out.println(e);
        }
    }

}

class Node<Item>{
    private Item item;
    private Node left;
    private Node right;
    public Node(Item item){
        this.item = item;
        left = null; right = null;
    }

    public Item getValue(){
        return this.item;
    }
    public void setLeft(Node left){
        this.left = left;
    }
    public void setRight(Node right){
        this.right = right;
    }
    public Node getLeft(){
        return this.left;
    }
    public Node getRight(){
        return this.right;
    }
}

enum Orientation {LEFT, RIGHT};
