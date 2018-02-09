package datastructures.linkedlist;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
  private Node top; // top of stack
  private int n = 0;  // number of items
  
  public boolean isEmpty() {
    return top == null;
  }
  
  public int size() {
    return n;
  }
  
  // Add item to top of stack
  public void push(Item item) {
    Node oldTop = top;
    top = new Node();
    top.item = item;
    top.next = oldTop;
    n++;
  }
  
  // Remove item from top of stack
  public Item pop() {
    Item item = top.item;
    top = top.next;
    n--;
    return item;
  }
  
  public Iterator<Item> iterator() {
    return new StackIterator();
  }
  
  private class Node {
    Item item;
    Node next;
  }
  
  // Support LIFO iteration
  private class StackIterator implements Iterator<Item> {
    private Node node = top;
    
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