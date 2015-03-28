package org.intelligentjava.algos.simplestructures;

/**
 * Standard simple non circular doubly linked Linked List implementation.
 * 
 * @author Ignas Lelys
 * @created May 4, 2011
 *
 */
// TODO iterator
public class LinkedList {
    
    private Entry head;
    
    // TODO error checking
    public int get(int index) {
        Entry entry = head;
        for (int i = 0; i < index; i++) {
            entry = entry.next;
        }
        return entry.element;
    }
    
    public void insert(int element) {
        Entry oldHead = this.head;
        this.head = new Entry(null, oldHead, element);
        if (oldHead != null) {
            oldHead.previous = head;
        }
    }
    
    public void delete(int element) {
        Entry elementEntry = search(element);
        if (elementEntry != null) {
            if (elementEntry.previous != null) {
                elementEntry.previous.next = elementEntry.next;
            } else {
                this.head = elementEntry.next; // if no previous, that means we are deleting head
            }
            if (elementEntry.next != null) {
                elementEntry.next.previous = elementEntry.previous;
            }
        }
    }
    
    private Entry search(int key) {
        Entry entry = head;
        while (entry != null) {
            if (entry.element == key) {
                return entry;
            }
            entry = entry.next;
        }
        return null;
    }
    
    private static class Entry {
        public Entry(Entry previous, Entry next, int element) {
            super();
            this.previous = previous;
            this.next = next;
            this.element = element;
        }
        public Entry previous;
        public Entry next;
        public int element;
    }
}
