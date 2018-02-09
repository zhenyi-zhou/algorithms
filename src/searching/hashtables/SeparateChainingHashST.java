package searching.hashtables;

import searching.sequentialsearch.SequentialSearchST;
import datastructures.linkedlist.Queue;

public class SeparateChainingHashST<Key, Value> {
  private int n;
  private int m;
  private SequentialSearchST<Key, Value>[] st;
  
  public SeparateChainingHashST() {
    this(997);
  }
  
  public SeparateChainingHashST(int m) {
    this.m = m;
    st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[m];
    for (int i = 0; i < m; i++) {
      st[i] = new SequentialSearchST();
    }
  }
  
  public int size() {
    return n;
  }
  
  public boolean isEmpty() {
    return size() == 0;
  }
  
  public boolean contains(Key key) {
    return get(key) != null;
  }
  
  public Value get(Key key) {
    return (Value) st[hash(key)].get(key);
  }
  
  public void put(Key key, Value val) {
    st[hash(key)].put(key, val);
  }
  
  public void delete(Key key) {
    st[hash(key)].delete(key);
  }
  
  public Iterable<Key> keys() {
    Queue<Key> queue = new Queue<Key>();
    for (int i = 0; i < m; i++) {
      for (Key key : st[i].keys()) {
        queue.enqueue(key);
      }
    }
    return queue;
  }
  
  private int hash(Key key) {
    return (key.hashCode() & 0x7fffffff) % m;
  }
}