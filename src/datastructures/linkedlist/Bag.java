package datastructures.linkedlist;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
  private Node first;
  private int n = 0;
  
  public boolean isEmpty() {
    return first == null;
  }
  
  public int size() {
    return n;
  }
  
  // Add item to bag
  public void add(Item item) {
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
    n++;
  }
  
  public Iterator<Item> iterator() {
    return new BagIterator();
  }
  
  private class Node {
    Item item;
    Node next;
  }
  
  private class BagIterator implements Iterator<Item> {
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