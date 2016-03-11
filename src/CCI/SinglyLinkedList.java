/**
 * Created by vppriyad on 4/12/2015.
 */
package CCI;
import java.io.IOException;
import java.lang.String;
import java.util.Scanner;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.HashSet;

// note the comparable for generics. compareTo can be used if Item extends Comparable.
public class SinglyLinkedList <Item extends Comparable<Item>> implements Iterable<Item>{

    private int N;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
    }

    public SinglyLinkedList() {
        N = 0;
        first = null;
        last = null;
    }

    /*  Size()
    *  returns number of elements in the linked list
    */
    public int size() {
        return N;
    }

    /*  IsEmpty()
    *   returns if list is empty
     */
    public boolean IsEmpty(){
        return (first == null);
    }

    /*  AddHead
    *   Add to head of linked list
        @param item to add to the list
     */
    public void AddHead(Item data){
        Node temp = new Node();
        temp.next = null;
        temp.item = data;
        N++;

        if (IsEmpty()){
            first = last = temp;
            return;
        }
        temp.next = first;
        first = temp;
    }

    /* AddTail
    *  Add element to the tail of the linked list
    *  @param item to add to the list
     */
    public void AddTail(Item data){
        Node temp = new Node();
        temp.next = null;
        temp.item = data;
        N++;
        if(IsEmpty()){
            first = last = temp;
            return;
        }
        last.next = temp;
        last = temp;
    }
    /*  DeleteAt
    *   Delete node at position p
    *   @param position p int
    *   @return Item  at position p which has been deleted.
     */
    public Item DeleteAt(int p){
        Node curr = first;
        // if empty
        if (IsEmpty())
            throw new NoSuchElementException("No Element to Delete");

        // if deleting first node
        if (p == 0) {
            first = first.next;
            // if there was only one node in the list. last == first.
            if(last == curr)
                last = null;
            N--;
            return curr.item;
        }
        int i = 0;
        while(curr.next!=null) {
            if (p == i+1) {
                Node temp = curr.next;
                curr.next = curr.next.next;
                // if deleting last node
                if (curr.next == null)
                    last = curr;
                N--;
                return temp.item;
            }
            curr = curr.next;
            i++;
        }
        // if position not reached, return null
        throw new NoSuchElementException("No such position index" + p);

    }


    // CCI 2.1
    // O(n) space and O(n) time
    public void RemoveDuplicates(){
        HashSet<Item> set = new HashSet<>();
        Node curr, prev;
        // if empty or contains only one item, return, no need to adjust first
        if (IsEmpty() || (first == last))
            return;

        curr = first;
        prev = null;

        while(curr!=null)
        {
            // if set contains the item, it is duplicate, remove it.
            if(set.contains(curr.item)){
                // prev can be null only for first item which cannot be a duplicate
                assert(prev!=null);
                prev.next = curr.next;
                N--;
                if(curr.equals(last))
                    last = prev;
            }
            // add the item, so it can be removed if found again
            else{
                set.add(curr.item);
                prev = curr;
            }
            curr = curr.next;
        }
    }
    // CCI 2.1
    // if temporary space is not allowed. O(1) space, O(n2) time. more accurately O(n2/2)
    public void RemoveDuplicatesLessSpace(){
        Node p1, p2;
        p1 = first;
        while (p1!=null){
            p2 = p1;
            while (p2.next!= null){
                if (p1.item.equals(p2.next.item))
                {
                    p2.next = p2.next.next;
                    N--;
                }
                else { // important- if node deleted pointer automatically moved.
                    p2 = p2.next;
                }
            }
            p1 = p1.next;
        }
        last = p1;
    }

    /**
     * CCI 2.2
     * find kth to the last element
     * return non null node
     * if k= 0 return last element
     * k can be at most N-1 or N should be at least k+1.
     * k < N is good, if N <= k throw exception.
     * throw exception if there are less or equal to k number of elements.
     */

    public Node KthtoLast(int k){
        Node kth, last;
        kth = last = first;
        int i = 0;

        while(i<k) {
            // total number of elements is less than k
            if (last == null) {
                throw new NoSuchElementException();
            }
            last = last.next;
            i++;
        }
        // there are exactly k elements in the list
        if (last==null)
        {
            throw new NoSuchElementException();
        }
        while(last.next!=null){
            last = last.next;
            kth = kth.next;
        }
        return kth;
    }

    public class CounterNode{
        private Node kth;
        private int i;
        CounterNode(){
            kth = null;
            i = 0;
        }
        void incrementCounter(){
            i++;
        }
        int getCounter(){
            return i;
        }
        void setKthNode(Node kth){
            this.kth = kth;
        }
        Node getKthNode(){
            return this.kth;
        }
    }
    /**
     *  CCI 2.2
     *  find kth to the last element
     *  KthtoLastRecur
     *  return non null node
     *  if k= 0 return last element
     */
    public Node KthtoLastRecur(int k){
        CounterNode kthNode = new CounterNode();
        KthtoLastCounterRecur(first, k, kthNode);
        Node kth = kthNode.getKthNode();
        if ( kth != null){
            return kth;
        }
        else{
            throw new NoSuchElementException();
        }
    }
    public void KthtoLastCounterRecur(Node head, int k, CounterNode kthNode){

        // we hit the next to last element
        if (head == null)
            return;

        KthtoLastCounterRecur(head.next, k, kthNode);
        // this  is the kth to the last element. set the kth node to this head.
        if (kthNode.getCounter() == k){
            kthNode.setKthNode(head);
        }

        // increment counter
        kthNode.incrementCounter();
    }


    /**
     * CCI 2.3
     * delete a node given only its access. No first or last.
     * if the node to be deleted is the last node, it cannot be done. point that out.
     * mark it as dummy.
     */
    public void deleteNode(Node toDel){
        // if toDel is the last node, operation cannot be performed. Mark it as dummy.

        // node to be deleted is last node or null
        if ((toDel == null)||(toDel.next == null))
            throw new NoSuchElementException();

        // copy data from next node to toDel
        toDel.item = toDel.next.item;

        // delete the next node.
        toDel.next = toDel.next.next;
        N--;
    }

    /**
     * CCI 2.4
     * partition a linked list around a value x, such that all nodes less than x come before
     * all nodes greater than x or equal to x.
     */
    public void partition(Item x){
        Node curr = first;

        while(curr != null){
            // if curr.item < pivot
            // note the comparable for generics. compareTo can be used if Item extends Comparable.
            if (curr.item.compareTo(x) < 0){
                AddHead(curr.item);
                deleteNode(curr);
            }
            else {
                curr = curr.next;
            }
        }
    }


    /**
     * Returns a string representation of this list.
     * @return the sequence of items, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    public Iterator iterator(){return new SinglyListIterator(); }

    private class SinglyListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return (current != null);
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
        public Item next(){
            if(!hasNext()) { throw new NoSuchElementException();}
            Node prev = current;
            current = current.next;
            return prev.item;
        }
    }

    public static void main(String args[]) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> listdup = new SinglyLinkedList<>();
        System.out.println("list.size() is : " + list.size());
        System.out.println("list.IsEmpty() is : " + list.IsEmpty());
        Scanner sc = new Scanner(System.in);
        // Read integers only, ignoring characters until q is read.
        System.out.println("Enter a integer to insert in linked list, press q when done");
        while(sc.hasNext()) {
            System.out.println("Enter a integer to insert in linked list, press q when done");
            String input = sc.next();
            if(input.compareTo("q")==0)
                break;
            try {
                int data = Integer.parseInt(input);
                list.AddTail(data);
            } catch (NumberFormatException e) {
                System.out.println("enter only integer");
            }
        }
        System.out.println("list.size() is : " + list.size());
        System.out.println("list is : " + list);

        list.partition(4);
        System.out.println("After Partition around 4");
        System.out.println("list.size() is : " + list.size());
        System.out.println("list is : " + list);
        /*int i = list.size()-1;
        while(i >=0 ){
            System.out.println("KthtoLastRecur(i) for i = "+i+"is: "+ list.KthtoLastRecur(i).item);
            if (i == 4) list.deleteNode(list.KthtoLastRecur(i));
            i--;
        }
        System.out.println("list.size() is : " + list.size());
        System.out.println("list after deleting 5th from last is : " + list);
        */
        /*
        listdup = list;
        list.RemoveDuplicates();
        list.RemoveDuplicatesLessSpace();
        System.out.println("After removing duplicates list.size() is : " + list.size());
        System.out.println("list is : " + list);
        System.out.println("After removing duplicates listdup.size() is : " + listdup.size());
        System.out.println("listdup is : " + listdup);
        */
    }
}
