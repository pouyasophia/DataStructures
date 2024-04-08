import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Deque<Item> implements Iterable<Item> {

        private class Node{
            Item item;
            Node next, previous;
        };

        private Node first, last;
        private int size = 0;


        // construct an empty deque
        public Deque(){}

        // is the deque empty?
        public boolean isEmpty(){
            return size ==0;
        }

        private void assertNodeNotNull(Item item) {
            if (item == null) throw new NullPointerException();
        }

        private void assertNodeNotEmpty() {
            if (isEmpty()) throw new NoSuchElementException();
        }
    
        // return the number of items on the deque
        public int size(){
            return size;
        }
    
        // add the item to the front
        public void addFirst(Item item){
            assertNodeNotNull(item);
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            if(size>0){
                oldFirst.previous = first;
            }
            else{
                last = first;
            }
            size++;
        }
    
        // add the item to the back
        public void addLast(Item item){
            assertNodeNotNull(item);
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.previous = oldLast;
            if(size>0){
                oldLast.next = last;
            }
            else{
                first = last;
            }
            size++;
        }
    
        // remove and return the item from the front
        public Item removeFirst(){
            assertNodeNotEmpty();

            Item firstItem = first.item;

            if(size>1){
                first = first.next;
                first.previous = null;
            }
            else{
                first = null;
                last = null;
            }
            size--;
            return firstItem;
        }
    
        // remove and return the item from the back
        public Item removeLast(){
            assertNodeNotEmpty();
            
            Item lastItem = last.item;

            if(size > 1){
                last = last.previous;
                last.next = null;
            }
            else{
                first = null;
                last = null;
            }
            size--;
            return lastItem;
        }

        private class ListIterator implements Iterator<Item>{

            private Node current = first;

            public boolean hasNext(){
                return current != null;
            }

            public Item next(){
                if (current == null) throw new NoSuchElementException();
                Item currentValue = current.item;
                current = current.next;
                return currentValue;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
    
        }
    
        // return an iterator over items in order from front to back
        public Iterator<Item> iterator(){
            return new ListIterator();
        }
    
        // unit testing (required)
        public static void main(String[] args){
            Deque<Integer> newDeque = new Deque<>();
            newDeque.addLast(10);
            newDeque.addFirst(15);
            newDeque.addLast(9);
            newDeque.addLast(13);
            newDeque.addLast(11);
            newDeque.addLast(18);
            newDeque.removeLast();
            newDeque.removeFirst();
            for(int i: newDeque){
                System.out.print(i+" ");
            }
        
        }
    
}