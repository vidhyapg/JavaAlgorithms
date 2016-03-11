package Core;

import java.lang.Comparable;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by vppriyad on 10/14/2015.
 */

public class BST<K extends Comparable <K>, V> {

    private TreeNode root;


    // returns true if this set did not already contain the specified element
    public void put(K k, V v){
        root = put(root, k, v);
    }

    private TreeNode put(TreeNode root, K k, V v){
        // this is the right position to insert the new node
        if (root == null){
            return new TreeNode(k, v, 1);
        }

        int comp = root.key.compareTo(k);
        // if k > root.key, it is in right subtree
        if (comp < 0){
            root.right = put(root.right, k, v);
        }
        // if k < root.key, it is in left subtree
        else if (comp > 0){
            root.left = put(root.left , k, v);
        }
        // if k == root.key, key already there in the tree, replace value and return false.
        else{
            root.value = v;
        }
        // update size of root.
        setSize(root, (size(root.left) + size(root.right) + 1));
        return root;
    }

    // Hard to maintain size. so use put recursive version.
    public void putIter (K k, V v){
        TreeNode curr = root;

        while(curr != null){
            int comp = curr.key.compareTo(k);
            // if k > curr.key, it is in right subtree
            if (comp < 0){
                curr = curr.right;
            }
            // if k < curr.key, it is in left subtree
            else if (comp > 0){
                curr = curr.left;
            }
            // if k == curr.key, key already there in the tree, replace value and return false.
            else{
                curr.value = v;
            }
        }
        // this is the right position to insert the new node
        if (curr == null){
            curr = new TreeNode(k, v, 1);
        }
        // update size of root.
        // setSize(root, (size(root.left) + size(root.right) + 1));
        // cannot do this. Hard to maintain size. so use put recursive version.
    }

    // Returns value associated with key k if this set contains the specified element.
    public V get(K k){
        TreeNode curr = root;

        while(curr != null){
            int comp = curr.key.compareTo(k);
            // if k > curr.key, it is in right subtree
            if (comp < 0){
                curr = curr.right;
            }
            // if k < curr.key, it is in left subtree
            else if (comp > 0){
                curr = curr.left;
            }
            // if k == curr.key, key already there in the tree, return value.
            else {
               return curr.value;
            }
        }
        // not found
        return null;
    }
    // left most non null node of the tree
    public K findMin(){
        return findMin(root).key;
    }

    // left most non null node of the tree
    private TreeNode findMin(TreeNode root){
        TreeNode x = root;
        TreeNode min = x;
        while (x != null){
            min = x;
            x = x.left;
        }
        return min;
    }

    public K findMax(){
        return findMax(root).key;
    }

    // right most non null node of the tree
    private TreeNode findMax(TreeNode root){
        TreeNode x = root;
        TreeNode max = x;
        while (x != null){
            max = x;
            x = x.right;
        }
        return max;
    }

    // largest key <= given key
    // key's predecessor if key not present
    //return null if no such key
    public K floor(K key){
        K max = null;
        TreeNode x = root;
        while(x != null) {
            int cmp = x.key.compareTo(key);
            if (cmp < 0) {
                if ((max == null) || (max.compareTo(x.key) < 0)){
                    max = x.key;
                }
                x = x.right;
            } else if (cmp > 0) {
                x = x.left;
            } else {        // found
                return x.key;
            }
        }
        // not found
        return max;
    }
    public TreeNode floor_rec (K key) {
        return floor_rec(key, root);
    }

    private TreeNode floor_rec (K key, TreeNode root){
        if (root == null){
            return null;
        }
        int cmp = root.key.compareTo(key);
        if (cmp == 0) // found the key return it
            return root;
        // root.key >  key, left
        if (cmp > 0)
            return floor_rec(key, root.left);
        // root.key < key, right
        TreeNode t = floor_rec(key, root.right);
        if (t != null) return t;
        else return root;
    }

    // smallest key >= given key
    // key's successor if key not present
    // return null if no such key
    public K ceiling(K key){
        K min = null;
        TreeNode x = root;
        while(x != null) {
            int cmp = x.key.compareTo(key);
            if (cmp < 0) {
                x = x.right;
            } else if (cmp > 0) {
                if ((min == null) || (min.compareTo(x.key) > 0)){
                    min = x.key;
                }
                x = x.left;
            } else {        // found
                return x.key;
            }
        }
        // not found
        return min;
    }
    public TreeNode ceil_rec (K key) {
        return ceil_rec(key, root);
    }

    private TreeNode ceil_rec (K key, TreeNode root){
        if (root == null){
            return null;
        }
        int cmp = root.key.compareTo(key);
        if (cmp == 0) // found the key return it
            return root;
        // root.key < key, right
        if (cmp < 0)
            return ceil_rec(key, root.right);

        // root.key >  key, left
        TreeNode t = ceil_rec(key, root.left);
        if (t != null) return t;
        else return root;
    }

    public int rank (K k){
        return rank(root, k);
    }

    // rank, find no of keys less than given key.
    private int rank(TreeNode root, K k){
        // The key is not found, so rank is 0
        if (root == null){
            return 0;
        }
        int comp = root.key.compareTo(k);
        // if k > root.key, it is in right subtree
        if (comp < 0){
            return (rank(root.right, k)+rank(root, root.key) + 1);
        }

        // if k < root.key, it is in left subtree
        else if (comp > 0){
            return rank(root.left, k);
        }
        // if k == root.key,
        else{
            return size(root.left);
        }
    }

    private K select (int p){
        if ((p < 0) || (p > size()))
            return null;
        return select(p, root);
    }
    // find the key in pth position of the sorted list.
    private K select (int p, TreeNode root){
        if (root == null){
            return null;
        }

        int leftSize = size(root.left);
        if (p > leftSize){
            return select ((p-leftSize-1), root.right);
        }
        // it is the current node.
        else if (p == leftSize){
            return root.key;
        }
        else{
            return select(p, root.left);
        }
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private TreeNode deleteMin(TreeNode root){
        if (root.left == null)
            return root.right;
        root.left = deleteMin(root.left);
        setSize(root, (1 + size(root.left) + size(root.right)));
        return root;
    }

    public void delete(K k){
        root = delete(k, root);
    }

    // deleta a node with key k.
    private TreeNode delete (K k, TreeNode root){
        int comp = root.key.compareTo(k);
        // if k is in right subtree
        if (comp < 0){
            root.right = delete(k, root.right);
        }
        // if k is in left subtree
        else if (comp > 0){
            root.left = delete (k, root.left);
        }
        // if k is root.key
        else {
            // case 1: root has no children, return null
            // case 2: root has only one child, return the other child.
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;
            // case 3: root has two children, deleteMin of right subtree, replace root with min.
            // bit complex here. look at the slides example. Do not just get Key value of the min node, coz u have Value also associated. Make that min node as new root.
            TreeNode t = root;
            root = findMin(root.right);
            root.right = deleteMin(t.right);
            root.left = t.left;
            setSize(root, (1 + size(root.left) + size(root.right)));
        }
        return root;
    }

    public boolean checkBST (){
        return checkBST(root, null, null);
    }

    // check if BST.
    // O(n) running
    // O(h) space
    //
    private boolean checkBST(TreeNode root, K min, K max){
        if (root == null)
            return true;
        if ((min != null) && (root.key.compareTo(min) < 0)){
            return false;
        }
        if ((max != null) && (root.key.compareTo(max) > 0)){
            return false;
        }
        return checkBST(root.left, min, root.key) && checkBST(root.right, root.key, max);
    }
    public boolean checkBSTinLevelOrder(){
        return checkBSTinLevelOrder(root);
    }
    //  O(n) running time
    // O(n) space - last level can have n/2 nodes.
    // but catches BST violation early if it in nodes near top of the tree
    private boolean checkBSTinLevelOrder (TreeNode root){
        // Add nodes with root.key, min and max value to queue and process in level order.
        class PNode{
            private TreeNode node;
            private K min;
            private K max;
            private PNode(TreeNode node, K min, K max){
                this.node = node;
                this.min = min;
                this.max = max;
            }
        }

        if (root == null){
            return true;
        }

        Queue<PNode> pNodesQ = new LinkedList<>();

        pNodesQ.add(new PNode(root, null, null));

        while (!(pNodesQ.isEmpty())){
            // remove top node
            PNode pNode = pNodesQ.poll();

            // process top node
            if ((pNode.min != null) && (pNode.node.key.compareTo(pNode.min) < 0)){
                return false;
            }
            if ((pNode.max != null) && (pNode.node.key.compareTo(pNode.max) > 0)){
                return false;
            }
            // add children of top node to queue
            if (pNode.node.left != null)
                pNodesQ.add(new PNode(pNode.node.left, pNode.min, pNode.node.key));
            if (pNode.node.right != null)
                pNodesQ.add(new PNode(pNode.node.right, pNode.node.key, pNode.max));
        }
        return true;
    }

    // from EPI solution EPI 10.1
    // returns -1 if diff in height of right and left subtree is more than 1 or left or right subtree is unbalanced.
    // else returns the height of the tree with root as root.
    // empty tree height is 0.
    public int checkHeight(TreeNode root){
        int left_h, right_h;
        if (root == null){
            return 0;
        }

        left_h = checkHeight(root.left);
        right_h = checkHeight(root.right);

        if ((left_h == - 1) || (right_h == - 1))
            return -1;

        if (Math.abs(left_h - right_h) > 1){
            return -1;
        }

        return (Math.max(left_h, right_h)+1);
    }


    public boolean checkIfBalanced(){
        if (root == null){
            return true;
        }
        if (checkHeight(root) == -1){
            return false;
        }
        return true;
    }

    public int size(){
        return size(root);
    }

    private int size(TreeNode root){
        if (root == null) return 0;
        else return root.count;
    }

    public void setSize(TreeNode root, int count){
        if (root == null) throw new InvalidParameterException();
        root.count = count;
    }

    private class TreeNode{
        private K key;
        private V value;
        private TreeNode left;
        private TreeNode right;
        private int count;

        public TreeNode(K k, V v, int count){
            key = k;
            value = v;
            this.count = count;
        }

    }

    public static void main(String args[]){
        BST<Integer, Integer> bst = new BST<>();
        bst.put(4,23);
        bst.put(6,45);
        bst.put(8,89);
        bst.put(2,42);
        bst.put(3,87);
        bst.put(1,85);
        bst.put(10,12);

        System.out.println(bst.findMin());
        System.out.println(bst.findMax());
        /*
        System.out.println(bst.floor(9));
        System.out.println(bst.ceiling(5));
        System.out.println(bst.floor_rec(9).key);
        System.out.println(bst.ceil_rec(5).key);
        */
        System.out.println(bst.size());

        System.out.println(bst.rank(1));
        System.out.println(bst.rank(2));
        System.out.println(bst.rank(3));
        System.out.println(bst.rank(4));
        System.out.println(bst.rank(6));
        System.out.println(bst.rank(8));
        System.out.println(bst.rank(10));
        System.out.println("checkBSTinLevelOrder: " +  ((bst.checkBSTinLevelOrder())? "true": "false"));
        //System.out.println("checkBST: " +  ((bst.checkBST())? "true": "false"));
        System.out.println("checkIfBalanced: " +  ((bst.checkIfBalanced())? "true": "false"));
        System.out.println("*************************************************************************");
        System.out.println(bst.select(0));
        System.out.println(bst.select(1));
        System.out.println(bst.select(2));
        System.out.println(bst.select(3));
        System.out.println(bst.select(4));
        System.out.println(bst.select(5));
        System.out.println(bst.select(6));
        System.out.println("*************************************************************************");
        bst.delete(1);
        bst.delete(4);
        bst.delete(6);
        System.out.println("checkBSTinLevelOrder: " +  ((bst.checkBSTinLevelOrder())? "true": "false"));
        //System.out.println("checkBST: " +  ((bst.checkBST())? "true": "false"));
        System.out.println("checkIfBalanced: " +  ((bst.checkIfBalanced())? "true": "false"));
        System.out.println(bst.findMin());
        System.out.println(bst.findMax());
        System.out.println(bst.select(3));
    }

}
