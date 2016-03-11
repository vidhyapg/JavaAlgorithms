package EPI;

/**
 * EPI 10.1
 * Runtime O(n)
 * Space O(h) height if the tree, post order traversal at anytime the call stack height can go up to height of the tree.
 * Created by vppriyad on 10/4/2015.
 */
public class CheckBST {

    // returns -1 if diff in height of right and left subtree is more than 1 or left or right subtree is unbalanced.
    // else returns the height of the tree with root as root.
    // empty tree height is 0.
    public static int checkHeight(TreeNode root){
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


    public static boolean checkBST(TreeNode root){
        if (root == null){
            return true;
        }
        if (checkHeight(root) == -1){
            return false;
        }
        return true;
    }

    private static class TreeNode{
        private int data;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(){

        }
        private TreeNode(int val){
            data = val;
            left = null;
            right = null;
        }
    }

    public static void main(String args[]){
        CheckBST.TreeNode root = new CheckBST.TreeNode(10);
        System.out.println("The tree is Balanced: "+ checkBST(root));

    }
}
