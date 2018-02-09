package datastructures.array;

import java.util.Iterator;

public class Stack<Item> implements Iterable<Item> {
  private Item[] item = (Item[]) new Object[1]; // stack items
  private int n = 0;                            // number of items
  
  public boolean isEmpty() {
    return n == 0;
  }
  
  public int size() {
    return n;
  }
  
  // Add item to top of stack
  public void push(Item item) {
    if (n == this.item.length) {
      resize(2 * this.item.length);
    }
    
    this.item[n++] = item;
  }
  
  // Remove item from top of stack
  public Item pop() {
    Item newItem = item[--n];
    item[n] = null; // avoid loitering
    
    if (n > 0 && n == item.length / 4) {
      resize(item.length / 2);
    }
    
    return newItem;
  }
  
  public Iterator<Item> iterator() {
    return new StackIterator();
  }
  
  private void resize(int size) {
    Item[] newItem = (Item[]) new Object[size];
    
    for (int i = 0; i < n; i++) {
      newItem[i] = item[i];
    }
    
    item = newItem;
  }
  
  // Support LIFO iteration
  private class StackIterator implements Iterator<Item> {
    private int i = n;
    
    public boolean hasNext() {
      return i > 0;
    }
    
    public Item next() {
      return item[--i];
    }
    
    public void remove() {}
  }
}