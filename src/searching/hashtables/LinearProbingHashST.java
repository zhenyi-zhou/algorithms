package searching.hashtables;

import datastructures.linkedlist.Queue;

public class LinearProbingHashST<Key, Value> {
  private int n;        // number of key-value pairs
  private int m = 16;   // size of linear-probing table
  private Key[] keys;   // the keys
  private Value[] vals; // the values
  
  public LinearProbingHashST() {
    keys = (Key[]) new Object[m];
    vals = (Value[]) new Object[m];
  }
  
  public LinearProbingHashST(int m) {
    this.m = m;
    n = 0;
    keys = (Key[]) new Object[m];
    vals = (Value[]) new Object[m];
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
  
  public void put(Key key, Value val) {
    if (n >= m / 2) {
      resize(2 * m);
    } 
    
    int i;
    for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
      if (keys[i].equals(key)) {
        vals[i] = val;
        return;
      }
    }
    keys[i] = key;
    vals[i] = val;
    n++;
  }
  
  public Value get(Key key) {
    for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
      if (keys[i].equals(key)) {
        return vals[i];
      }
    }
    return null;
  }
  
  public void delete(Key key) {
    if (!contains(key)) {
      return;
    }
    
    int i = hash(key);
    while (!key.equals(keys[i])) {
      i = (i + 1) % m;
    }
    keys[i] = null;
    vals[i] = null;
    
    i = (i + 1) % m;
    while (keys[i] != null) {
      Key keyToRedo = keys[i];
      Value valToRedo = vals[i];
      keys[i] = null;
      vals[i] = null;
      n--;
      put(keyToRedo, valToRedo);
      i = (i + 1) % m;
    }
    
    n--;
    
    if (n > 0 && n == m / 8) {
      resize(m / 2);
    }
  }
  
  public Iterable<Key> keys() {
    Queue<Key> queue = new Queue<Key>();
    for (int i = 0; i < m; i++) {
      if (keys[i] != null) {
        queue.enqueue(keys[i]);
      }
    }
    return queue;
  }
  
  private int hash(Key key) {
    return (key.hashCode() & 0x7fffffff) % m;
  }
  
  private void resize(int size) {
    LinearProbingHashST<Key, Value> st;
    st = new LinearProbingHashST<Key, Value>(size);
    for (int i = 0; i < m; i++) {
      if (keys[i] != null) {
        st.put(keys[i], vals[i]);
      }
    }
    keys = st.keys;
    vals = st.vals;
    m = st.m;
  }
}