package EPI;
import java.util.*;
import java.util.Map.*;
/**
 * EPI 9.1
 * Created by vppriyad on 10/4/2015.
 */
public class StackMax<E extends Comparable<E>> {
    // stack of elements and booean indicating whether this was the max element.
    private Stack<Entry<E,Boolean>> elements;
    // stack of max entries
    private Stack<E> max_entries;
    public StackMax(){
        elements = new Stack<>();
        max_entries = new Stack<>();
    }

    public E pop (){
        // pop top of elements. if this element caused a max entry and if peek of max_entries is same as this element
        // then , pop max_entries.
        if (elements.isEmpty()){
            throw new EmptyStackException();
        }
        Entry<E,Boolean> curr_entry= elements.pop();
        E curr_item = curr_entry.getKey();
        if (curr_entry.getValue() && (curr_item.equals(max_entries.peek()))){
            max_entries.pop();
        }
        return curr_item;
    }
    public E push(E item){
        boolean max= false;
        // push this item in elements stack along with boolean indicating whether this entry is causing push to
        // max_entries stack. i.e, when top of max_entries is strictly < item. if same it is not causing addition to
        // max_entries stack. push the item in max_entries stack if max is true.

        // if max_entries is null, item is the max, so push in max_entries
        if (max_entries.isEmpty()){
            max = true;
            max_entries.push(item);
        }
        else if (max_entries.peek().compareTo(item) < 0) {
            max = true;
            max_entries.push(item);
        }
        Entry entry = new AbstractMap.SimpleEntry(item,new Boolean(max));
        elements.push(entry);
        return item;
    }

    public E peek(){
        if (elements.isEmpty()){
            throw new EmptyStackException();
        }
        return elements.peek().getKey();
    }

    public E max(){
        if (elements.isEmpty()){
            throw new EmptyStackException();
        }
        return max_entries.peek();
    }

    public static void main(String args[]){
        StackMax<Integer> stack = new StackMax<>();
        try {
            stack.push(3);
            stack.push(6);
            stack.push(2);
            stack.push(9);
            stack.push(8);
            stack.push(4);
            stack.push(5);
            stack.push(10);
            stack.push(9);
            stack.push(10);
            System.out.println("Max element is: " + stack.max() + " Top element is: " + stack.peek());
            stack.pop();
            System.out.println("Max element is: " + stack.max() + " Top element is: " + stack.peek());
            stack.pop();
            System.out.println("Max element is: " + stack.max() + " Top element is: " + stack.peek());
            stack.pop();
            System.out.println("Max element is: " + stack.max() + " Top element is: " + stack.peek());
            stack.pop();
            System.out.println("Max element is: " + stack.max() + " Top element is: " + stack.peek());
            stack.pop();
            System.out.println("Max element is: " + stack.max() + " Top element is: " + stack.peek());
            stack.pop();
            System.out.println("Max element is: " + stack.max() + " Top element is: " + stack.peek());
            stack.pop();
            System.out.println("Max element is: " + stack.max() + " Top element is: " + stack.peek());
            stack.pop();
            System.out.println("Max element is: " + stack.max() + " Top element is: " + stack.peek());
            stack.pop();
            System.out.println("Max element is: " + stack.max() + " Top element is: " + stack.peek());
            stack.pop();
            System.out.println("Max element is: " + stack.max() + " Top element is: " + stack.peek());
        } catch(EmptyStackException E){
            System.out.println("Stack is empty "+E);
        }
    }
}
