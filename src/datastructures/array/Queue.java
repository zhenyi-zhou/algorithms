package datastructures.array;

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
  private Item[] item = (Item[]) new Object[1];
  private int n = 0;
  private int first = 0;
  private int last = 0;
  
  public boolean isEmpty() {
    return n == 0;
  }
  
  public int size() {
    return n;
  }
  
  // Add item to end of queue
  public void enqueue(Item item) {
    if (n == this.item.length) {
      resize(2 * this.item.length);
    }
    
    this.item[last++] = item;
    
    if (last == this.item.length) {
      last = 0; // wrap-around
    }
    
    n++;
  }
  
  // Remove item from start of queue
  public Item dequeue() {
    Item newItem = item[first];
    item[first] = null; // avoid loitering
    n--;
    first++;
    
    if (first == item.length) {
      first = 0; // wrap-around
    }
    
    if (n > 0 && n == item.length / 4) {
      resize(item.length / 2);
    }
    
    return newItem;
  }
  
  public Iterator<Item> iterator() {
    return new QueueIterator();
  }
  
  private void resize(int size) {
    Item[] newItem = (Item[]) new Object[size];
    
    for (int i = 0; i < n; i++) {
      newItem[i] = item[(first + i) % item.length];
    }
    
    item = newItem;
    first = 0;
    last = n;
  }
  
  // Support FIFO iteration
  private class QueueIterator implements Iterator<Item> {
    private int i = 0;
    
    public boolean hasNext() {
      return i < n;
    }
    
    public Item next() {
      Item newItem = item[(i + first) % item.length];
      i++;
      return newItem;
    }
    
    public void remove() {}
  }
}