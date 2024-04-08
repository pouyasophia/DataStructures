import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    // construct an empty randomized queue
    public RandomizedQueue(){

    };

    private class Node{
        Item item;
        Node next;
    }

    private Node first, last;
    private int size = 0;

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){

    }

    // remove and return a random item
    public Item dequeue(){

    }

    // return a random item (but do not remove it)
    public Item sample(){

    }

    private class ListIterator implements Iterator<Item>{
        public boolean hasNext(){

        }

        public Item next(){

        }

        public void remove(){
            
        }

    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args)
}
