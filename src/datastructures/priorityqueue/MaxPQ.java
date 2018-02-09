package datastructures.priorityqueue;

import java.util.Iterator;

public class MaxPQ<Key extends Comparable<Key>> implements Iterable<Key> { // heap priority queue
  private Key[] pq;  // heap-ordered complete binary tree in pq[1...n] with pq[0] unused
  private int n = 0;
  
  public MaxPQ(int max) {
    pq = (Key[]) new Comparable[max + 1];
  }
  
  public MaxPQ() {
    this(1);
  }
  
  public boolean isEmpty() {
    return n == 0;
  }
  
  public int size() {
    return n;
  }
  
  public void insert(Key key) {
    if (n == pq.length - 1) {
      resize(2 * pq.length);
    }
    
    pq[++n] = key;
    swim(n);
  }
  
  public Key deleteMax() {
    Key max = pq[1];  // retrieve max key from top
    exchange(1, n--); // exchange with last item
    pq[n + 1] = null; // avoid loitering
    sink(1);          // restore heap property
    
    if (n > 0 && n == (pq.length - 1) / 4) {
      resize(pq.length / 2);
    }
    
    return max;
  }
  
  public Iterator<Key> iterator() {
    return new HeapIterator();
  }
  
  private void resize(int size) {
    Key[] temp = (Key[]) new Comparable[size];
    
    for (int i = 1; i <= n; i++) {
      temp[i] = pq[i];
    }
    
    pq = temp;
  }
  
  private boolean less(int i, int j) {
    return pq[i].compareTo(pq[j]) < 0;
  }
  
  private void exchange(int i, int j) {
    Key temp = pq[i];
    pq[i] = pq[j];
    pq[j] = temp;
  }
  
  private void swim(int k) {
    while (k > 1 && less(k / 2, k)) {
      exchange(k / 2, k);
      k = k / 2;
    }
  }
  
  private void sink(int k) {
    while (2 * k <= n) {
      int j = 2 * k;
      
      if (j < n && less(j, j + 1)) {
        j++;
      }
      
      if (!less(k, j)) {
        break;
      }
      
      exchange(k, j);
      
      k = j;
    }
  }
  
  private class HeapIterator implements Iterator<Key> {
    // Create a new priority queue
    private MaxPQ<Key> copy;
    
    // Add all items to copy of heap
    public HeapIterator() {
      copy = new MaxPQ<Key>(size());
      
      for (int i = 1; i <= n; i++) {
        copy.insert(pq[i]);
      }
    }
    
    public boolean hasNext() {
      return !copy.isEmpty();
    }
    
    public Key next() {
      return copy.deleteMax();
    }
    
    public void remove() {}
  }
}