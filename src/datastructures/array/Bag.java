package datastructures.array;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item> {
  private Item[] item = (Item[]) new Object[1];
  private int n = 0;
  
  public boolean isEmpty() {
    return n == 0;
  }
  
  public int size() {
    return n;
  }
  
  // Add item to bag
  public void add(Item item) {
    if (n == this.item.length) {
      resize(2 * this.item.length);
    }
    
    this.item[n++] = item;
  }
  
  public Iterator<Item> iterator() {
    return new BagIterator();
  }
  
  private void resize(int size) {
    Item[] newItem = (Item[]) new Object[size];
    
    for (int i = 0; i < n; i++) {
      newItem[i] = item[i];
    }
    
    item = newItem;
  }
  
  private class BagIterator implements Iterator<Item> {
    private int i = 0;
    
    public boolean hasNext() {
      return i < n;
    }
    
    public Item next() {
      return item[i++];
    }
    
    public void remove() {}
  }
}