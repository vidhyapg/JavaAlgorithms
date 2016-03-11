package Core;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by vppriyad on 10/8/2015.
 * Iterator
 * Resizing
 */
public class HashSetSC<K, V>{
    private int capacity;
    private double loadFactor;
    private LinkedList<Map.Entry<K,V>>[] hArray;
    public HashSetSC(int cap){
        // choose n to be a prime within constant factor from no of objects in the table.
        // not too close to power of 2 or power of 10.

        capacity = cap;
        loadFactor = 0.75;
        hArray = new LinkedList[capacity];
        for (int i = 0; i < hArray.length; i++){
            hArray[i] = new LinkedList();
        }
    }

    public void add (K key, V value){
        int bucket = (key.hashCode()) % capacity;
        // check if Linked List of entry contains key k, if so update the value.
        for (Map.Entry entry : hArray[bucket])
        {
            if (key.equals(entry.getKey())) {
                entry.setValue(value);
                return;
            }
        }
        // the LinkedList of Entry does not contain key k. Add now Set no duplicates
        hArray[bucket].addFirst(new AbstractMap.SimpleEntry<K, V>(key, value));
    }
    public boolean containskey(K key){
        int bucket = (key.hashCode()) % capacity;
        // check if Linked List of entry contains key k
        for (Map.Entry entry : hArray[bucket])
        {
            if (key.equals(entry.getKey())) {
                return true;
            }
        }
        return false;
    }

    public V get(K key){
        int bucket = (key.hashCode()) % capacity;

        for (Map.Entry entry : hArray[bucket])
        {
            if (key.equals(entry.getKey())) {
                return (V) (entry.getValue());
            }
        }
        return null;
    }

    public boolean remove (K key){

        int bucket = (key.hashCode()) % capacity;

        for (Map.Entry entry : hArray[bucket])
        {
            if (key.equals(entry.getKey())) {
                return hArray[bucket].remove(entry);
            }
        }
        return false;
    }

    public static void main(String arg[]){
        HashSetSC<Integer, String> hashSet = new HashSetSC(10);
        hashSet.add(20, "Siva");
        hashSet.add(34, "John");
        hashSet.add(98, "Vidhya");
        hashSet.add(34, "Priya");
        System.out.println(hashSet.get(34));
        System.out.println(hashSet.get(98));
        hashSet.remove(34);
        hashSet.remove(98);
        System.out.println(hashSet.containskey(34));
        System.out.println(hashSet.containskey(98));
        System.out.println(hashSet.get(34));
        System.out.println(hashSet.get(98));
    }
}

