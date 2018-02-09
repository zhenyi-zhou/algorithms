package datastructures.symboltable;

import java.util.Iterator;
import java.util.TreeMap;

public class SymbolTable<Key extends Comparable<Key>, Value> implements Iterable<Key> {
  private TreeMap<Key, Value> st;
  
  public SymbolTable() {
    st = new TreeMap<Key, Value>();
  }
  
  public boolean isEmpty() {
    return size() == 0;
  }
  
  public int size() {
    return st.size();
  }
  
  public boolean contains(Key key) {
    if (key == null) {
      return false;
    }
    
    return st.containsKey(key);
  }
  
  public void put(Key key, Value value) {
    if (key == null) {
      return;
    }
    
    if (value == null) {
      st.remove(key);
    } else {
      st.put(key, value);
    }
  }
  
  public Value get(Key key) {
    if (key == null) {
      return null;
    }
    
    return st.get(key);
  }
  
  public void delete(Key key) {
    if (key == null) {
      return;
    }
    
    st.remove(key);
  }
  
  public Iterable<Key> keys() {
    return st.keySet();
  }
  
  public Iterator<Key> iterator() {
    return st.keySet().iterator();
  }
  
  public Key min() {
    if (isEmpty()) {
      return null;
    }
    
    return st.firstKey();
  }
  
  public Key max() {
    if (isEmpty()) {
      return null;
    }
    
    return st.lastKey();
  }
  
  public Key ceiling(Key key) {
    if (key == null) {
      return null;
    }
    
    Key k = st.ceilingKey(key);
    
    if (k == null) {
      return null;
    }
    
    return k;
  }
  
  public Key floor(Key key) {
    if (key == null) {
      return null;
    }
    
    Key k = st.floorKey(key);
    
    if (k == null) {
      return null;
    }
    
    return k;
  }
} 