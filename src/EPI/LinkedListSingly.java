package EPI;
import java.io.Console;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * EPI 8.2
 * Created by vppriyad on 10/31/2015.
 */
/******************************************************************************
 *  Compilation:  javac LinkedQueue.java
 *  Execution:    java LinkedQueue < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/13stacks/tobe.txt
 *
 *  A generic queue, implemented using a singly-linked list.
 *
 *  % java Queue < tobe.txt
 *  to be or not to be (2 left on queue)
 *
 ******************************************************************************/

/**
 *  The <tt>LinkedQueue</tt> class represents a first-in-first-out (FIFO)
 *  queue of generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em>
 *  operations, along with methods for peeking at the first item,
 *  testing if the queue is empty, and iterating through
 *  the items in FIFO order.
 *  <p>
 *  This implementation uses a singly-linked list with a non-static nested class
 *  for linked-list nodes.  See {@link Queue} for a version that uses a static nested class.
 *  The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class LinkedListSingly<Item> implements Iterable<Item> {
    private int N;         // number of elements on queue
    private Node head;    // beginning of list
    /**
     * Initializes an empty queue.
     */
    public LinkedListSingly() {
        head = null;
        N = 0;
        assert check();
    }

    /**
     * Is this queue empty?
     * @return true if this queue is empty; false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of items in this queue.
     * @return the number of items in this queue
     */
    public int size() {
        return N;
    }

    /**
     * Returns the item least recently added to this queue.
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Node peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return head;
    }

    public Node end() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Node last = head;
        while (last.next != null)
            last = last.next;
        return last;
    }

    /**
     * Adds the item to this queue at the last.
     * @param item the item to add
     */
    public void add(Item item) {
        Node newNode = new Node(item);
        if (isEmpty()){
            head = newNode;
            N++;
            return;
        }
        Node last = head;

        while(last.next != null){
            last = last.next;
        }
        last.next = newNode;
        N++;
        assert check();
    }

    /**
     *
     * Retrieves and removes the head (first element) of this list.
     * @return the item on this queue that was least recently added
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item remove() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = head.item;
        head = head.next;
        N--;
        assert check();
        return item;
    }

    /**
     * Returns a string representation of this queue.
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    // check internal invariants
    private boolean check() {
        if (N == 0) {
            if (head != null) return false;
        }
        else if (N == 1) {
            if (head == null) return false;
            if (head.next != null) return false;
        }
        else {
            if (head.next == null) return false;

            // check internal consistency of instance variable N
            int numberOfNodes = 0;
            for (Node x = head; x != null; x = x.next) {
                numberOfNodes++;
            }
            if (numberOfNodes != N) return false;
        }

        return true;
    }


    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    // EPI 8.2
    // reverse a sorted list with node H as its head. Space is limited. Do not create new nodes and can modify H.
    public LinkedListSingly<Item> reverseSortedList(LinkedListSingly<Item> list){
        Node p1 = list.head;
        Node p2 = p1.next;
        //System.out.println("check if equal "+p1.equals(p2));
        p1.next = null;
        Node temp;

        while(p2 != null){
            temp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = temp;
        }
        list.head = p1;
        return list;
    }

    public void testreverseSortedList(){
        LinkedListSingly<Integer> ll = new LinkedListSingly<>();
        int N = 10;
        for (int i = 0; i < N; i++)
            ll.add(new Integer(i));

        for(Integer x: ll.reverseSortedList(ll)){
            System.out.println(x);
        }
    }

    public Node findIfListHasLoop(LinkedListSingly<Item> list){
        Node head = list.head;
        Node slow, fast;
        // important, if list is Empty or only one node, no loop
        if ((list.isEmpty()) ||(head.next == null)){
            return null;
        }
        // important to start consecutive nodes.
        slow = head;
        fast = head;
        boolean hasLoop = false;
        // important: if list has odd number of elements, can start at last node as fast.
        while ((fast != null) && (fast.next != null)) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow.equals(fast)){
                hasLoop = true;
                break;
            }
        }

        if (!hasLoop) {
            assert((fast == null || fast.next == null));
            return null;
        }

        // important:
        // both meet at the start of the loop
        slow = head;
        while(!(fast.equals(slow))){
            fast = fast.next; slow = slow.next;
        }
        return fast;
    }

    public void testfindIfListHasLoop(){
        LinkedListSingly<Integer> ll = new LinkedListSingly<>();

        int N = 5;
        for (int i = 0; i < N; i++) {
            ll.add(new Integer(i));
        }
        (ll.end()).next = ll.peek().next.next.next;

        LinkedListSingly.Node start = ll.findIfListHasLoop(ll);
        if ((start) == null)
            System.out.println("No loop");
        else
            System.out.println("Loop starts at: "+ start.item);

    }

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;

        Node(Item item){
            this.item = item;
            next = null;
        }

        // correct implementation of equals
        public boolean equals(Object obj) {
            if (obj == null)
                return false;
            // if you need to compare subclass and superclass use instanceof
            if (getClass() != obj.getClass())
                return false;
            return this.item.equals(((Node) obj).item);
        }
    }

    /**
     * Unit tests the <tt>LinkedQueue</tt> data type.
     */
    public static void main(String[] args) {
        LinkedListSingly<String> q = new LinkedListSingly<String>();
        //q.testreverseSortedList();
        q.testfindIfListHasLoop();
        /*Scanner c = new Scanner(System.in);
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }
        while (true) {
            String item = c.nextLine();
            if (item.equals("q")) break;
            else if (!item.equals("-")) q.add(item);
            else if (!q.isEmpty()) System.out.println(q.remove() + " ");
        }
        System.out.println("(" + q.size() + " left on queue)");*/

    }
}


/*************************************************************************
 *  Copyright 2002-2012, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4-package.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4-package.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4-package.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with algs4-package.jar.  If not, see http://www.gnu.org/licenses.
 *************************************************************************/

