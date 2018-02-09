package searching.binarysearch;

import datastructures.linkedlist.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
  private Key[] keys;
  private Value[] values;
  private int n;
  
  public BinarySearchST() {
    this(1);
  }
  
  public BinarySearchST(int size) {
    keys = (Key[]) new Comparable[size];
    values = (Value[]) new Object[size];
  }
  
  public boolean isEmpty() {
    return size() == 0;
  }
  
  public int size() {
    return n;
  }
  
  public boolean contains(Key key) {
    return get(key) != null;
  }
  
  public void put(Key key, Value value) {
    if (value == null) {
      delete(key);
      return;
    }
    
    // Search for key, update value if found, grow table if new
    int i = rank(key);
    
    if (i < n && keys[i].compareTo(key) == 0) {
      values[i] = value;
      return;
    }
    
    // Array resizing
    if (n == keys.length) {
      resize(keys.length * 2);
    }
    
    for (int j = n; j > i; j--) {
      keys[j] = keys[j - 1];
      values[j] = values[j - 1];
    }
    
    keys[i] = key;
    values[i] = value;
    n++;
  }
  
  public Value get(Key key) {
    if (isEmpty()) {
      return null;
    }
    
    int i = rank(key);
    
    if (i < n && keys[i].compareTo(key) == 0) {
      return values[i];
    } else {
      return null;
    }
  }
  
  public void delete(Key key) {
    if (isEmpty()) {
      return;
    }
    
    int i = rank(key);
    
    // Key not in table
    if (i == n || keys[i].compareTo(key) != 0) {
      return;
    }
    
    for (int j = i; j < n - 1; j++) {
      keys[j] = keys[j + 1];
      values[j] = values[j + 1];
    }
    
    n--;
    keys[n] = null;   // avoid loitering
    values[n] = null; // avoid loitering
    
    // Array resizing
    if (n > 0 && n == keys.length / 4) {
      resize(keys.length / 2);
    }
  }
  
  // Binary search in an ordered array
  public int rank(Key key) {
    int low = 0, high = n - 1;
    
    while (low <= high) {
      int medium = low + (high - low) / 2;
      int num = key.compareTo(keys[medium]);
      
      if (num < 0) {
        high = medium - 1;
      } else if (num > 0) {
        low = medium + 1;
      } else {
        return medium;
      }
    }
    
    return low;
  }
  
  public Iterable<Key> keys() {
    return keys(min(), max());
  }
  
  public Iterable<Key> keys(Key low, Key high) {
    Queue<Key> queue = new Queue<Key>();
    
    if (low.compareTo(high) > 0) {
      return queue;
    }
    
    for (int i = rank(low); i < rank(high); i++) {
      queue.enqueue(keys[i]);
    }
    
    if (contains(high)) {
      queue.enqueue(keys[rank(high)]);
    }
    
    return queue;
  }
  
  public Key min() {
    return keys[0];
  }
  
  public Key max() {
    return keys[n - 1];
  }
  
  public void deleteMin() {
    delete(min());
  }
  
  public void deleteMax() {
    delete(max());
  }
  
  public Key select(int k) {
    return keys[k];
  }
  
  public Key ceiling(Key key) {
    int i = rank(key);
    
    if (i == n) {
      return null;
    } else {
      return keys[i];
    }
  }
  
  public Key floor(Key key) {
    int i = rank(key);
    
    if (i < n && key.compareTo(keys[i]) == 0) {
      return keys[i];
    }
    
    if (i == 0) {
      return null;
    } else {
      return keys[i - 1];
    }
  }
  
  private void resize(int size) {
    Key[] k = (Key[]) new Comparable[size];
    Value[] v = (Value[]) new Object[size];
    
    for (int i = 0; i < n; i++) {
      k[i] = keys[i];
      v[i] = values[i];
    }
    
    values = v;
    keys = k;
  }
}