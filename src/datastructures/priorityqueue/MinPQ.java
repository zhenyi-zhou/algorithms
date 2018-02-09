package datastructures.priorityqueue;

import java.util.Iterator;

public class MinPQ<Key extends Comparable<Key>> implements Iterable<Key> { // heap priority queue
  private Key[] pq;  // heap-ordered complete binary tree in pq[1...n] with pq[0] unused
  private int n = 0;
  
  public MinPQ(int max) {
    pq = (Key[]) new Comparable[max + 1];
  }
  
  public MinPQ() {
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
  
  public Key deleteMin() {
    Key min = pq[1];
    exchange(1, n--);
    pq[n + 1] = null;
    sink(1);
    
    if (n > 0 && n == (pq.length - 1) / 4) {
      resize(pq.length / 2);
    }
    
    return min;
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
  
  private boolean greater(int i, int j) {
    return pq[i].compareTo(pq[j]) > 0;
  }
  
  private void exchange(int i, int j) {
    Key temp = pq[i];
    pq[i] = pq[j];
    pq[j] = temp;
  }
  
  private void swim(int k) {
    while (k > 1 && greater(k / 2, k)) {
      exchange(k / 2, k);
      k = k / 2;
    }
  }
  
  private void sink(int k) {
    while (2 * k <= n) {
      int j = 2 * k;
      
      if (j < n && greater(j, j + 1)) {
        j++;
      }
      
      if (!greater(k, j)) {
        break;
      }
      
      exchange(k, j);
      
      k = j;
    }
  }
  
  private class HeapIterator implements Iterator<Key> {
    // Create a new priority queue
    private MinPQ<Key> copy;
    
    // Add all items to copy of heap
    public HeapIterator() {
      copy = new MinPQ<Key>(size());
      
      for (int i = 1; i <= n; i++) {
        copy.insert(pq[i]);
      }
    }
    
    public boolean hasNext() {
      return !copy.isEmpty();
    }
    
    public Key next() {
      return copy.deleteMin();
    }
    
    public void remove() {}
  }
}