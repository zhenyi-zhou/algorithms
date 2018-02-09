package datastructures.priorityqueue;

import java.util.Iterator;

public class IndexMaxPQ<Key extends Comparable<Key>> implements Iterable<Integer> { // heap priority queue
  private int n = 0;  // number of elements on PQ
  private int[] pq;   // binary heap using 1-based indexing
  private int[] qp;   // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
  private Key[] keys; // keys[i] = priority of i
  
  public IndexMaxPQ(int maxN) {
    keys = (Key[]) new Comparable[maxN + 1];
    pq = new int[maxN + 1];
    qp = new int[maxN + 1];
    for (int i = 0; i <= maxN; i++) {
      qp[i] = -1;
    }
  }
  
  public boolean isEmpty() {
    return n == 0;
  }
  
  public int size() {
    return n;
  }
  
  public boolean contains(int i) {
    return qp[i] != -1;
  }
  
  public void insert(int i, Key key) {
    n++;
    qp[i] = n;
    pq[n] = i;
    keys[i] = key;
    swim(n);
  }
  
  public void change(int i, Key key) {
    keys[i] = key;
    swim(qp[i]);
    sink(qp[i]);
  }
  
  public void delete(int i) {
    int index = qp[i];
    exchange(index, n--);
    swim(index);
    sink(index);
    keys[i] = null;
    qp[i] = -1;
  }
  
  public int deleteMax() {
    int min = pq[1];
    exchange(1, n--);
    sink(1);
    
    qp[min] = -1;     // delete
    keys[min] = null; // to help with garbage collection
    pq[n + 1] = -1;   // not needed
    
    return min;
  }
  
  public Iterator<Integer> iterator() {
    return new HeapIterator();
  }
  
  private boolean less(int i, int j) {
    return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
  }
  
  private void exchange(int i, int j) {
    int temp = pq[i];
    pq[i] = pq[j];
    pq[j] = temp;
    qp[pq[i]] = i;
    qp[pq[j]] = j;
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
  
  private class HeapIterator implements Iterator<Integer> {
    // Create a new priority queue
    private IndexMaxPQ<Key> copy;
    
    // Add all items to copy of heap
    public HeapIterator() {
      copy = new IndexMaxPQ<Key>(pq.length - 1);
      
      for (int i = 1; i <= n; i++) {
        copy.insert(pq[i], keys[pq[i]]);
      }
    }
    
    public boolean hasNext() {
      return !copy.isEmpty();
    }
    
    public Integer next() {
      return copy.deleteMax();
    }
    
    public void remove() {}
  }
}