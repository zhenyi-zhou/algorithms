package datastructures.linkedlist;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
  private Node first;
  private Node last;
  private int n = 0;
  
  public boolean isEmpty() {
    return first == null;
  }
  
  public int size() {
    return n;
  }
  
  // Add item to end of queue
  public void enqueue(Item item) {
    Node oldLast = last;
    last = new Node();
    last.item = item;
    last.next = null;
    
    if (isEmpty()) {
      first = last;
    } else {
      oldLast.next = last;
    }
    
    n++;
  }
  
  // Remove item from start of queue
  public Item dequeue() {
    Item item = first.item;
    first = first.next;
    
    if (isEmpty()) {
      last = null;
    }
    
    n--;
    return item;
  }
  
  public Iterator<Item> iterator() {
    return new QueueIterator();
  }
  
  private class Node {
    Item item;
    Node next;
  }
  
  // Support FIFO iteration
  private class QueueIterator implements Iterator<Item> {
    private Node node = first;
    
    public boolean hasNext() {
      return node != null;
    }
    
    public Item next() {
      Item item = node.item;
      node = node.next;
      return item;
    }
    
    public void remove() {}
  }
}