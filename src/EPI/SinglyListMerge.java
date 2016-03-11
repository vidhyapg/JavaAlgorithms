package EPI;

import java.util.LinkedList;

/**
 * EPI 8.1
 * Created by vppriyad on 10/3/2015.
 */

public class SinglyListMerge {

    // O(m+n) runtime
    // space O(1) - one node extra
    public Node mergeSortedLists(Node L, Node R){
        Node head = new Node(0); // created a dummy head node
        Node tail = head;
        // L, R empty or containing one node is taken care of.
        while ((L !=  null) && (R != null)){
            if (L.getVal() <= R.getVal()){
                tail = appendNode(L, tail);
                L = L.next;
            }
            else {
                tail = appendNode(R, tail);
                R = R.next;
            }
        }

        if (L != null){
            tail.next = L;
        }
        else if ( R!= null){
            tail.next = R;
        }

        return head.next;
    }

    private Node appendNode (Node newNode, Node tail){
        tail.next = newNode;
        tail = tail.next;
        return tail;
    }

    private static class Node{
        private int val;
        private Node next;

        private Node(int val){
            this.val = val;
            next = null;
        }
        private int getVal(){
            return val;
        }

        private String toString(Node m){

            return String.valueOf(m.getVal());
        }
    }

    public static void main(String args[]){
        SinglyListMerge listMerge = new SinglyListMerge();
        SinglyListMerge.Node l = new SinglyListMerge.Node(9);
        SinglyListMerge.Node r = new SinglyListMerge.Node(2);
        SinglyListMerge.Node m = listMerge.mergeSortedLists(l,r);
        //System.out.println(m.getVal());
    }

}

