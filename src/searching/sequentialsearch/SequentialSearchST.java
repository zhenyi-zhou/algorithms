package searching.sequentialsearch;

import datastructures.linkedlist.Queue;

public class SequentialSearchST<Key, Value> {
  private Node first; // first node in the linked list
  private int n;
  
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
    // Search for key, update value if found, grow table if new
    for (Node x = first; x != null; x = x.next) {
      if (key.equals(x.key)) {
        x.value = value;
        return;
      }
    }
    
    first = new Node(key, value, first);
    n++;
  }
  
  public Value get(Key key) {
    // Search for key, return associated value
    for (Node x = first; x != null; x = x.next) {
      if (key.equals(x.key)) {
        return x.value;
      }
    }
    
    return null;
  }
  
  public void delete(Key key) {
    first = delete(first, key);
  }
  
  public Iterable<Key> keys() {
    Queue<Key> queue = new Queue<Key>();
    
    for (Node x = first; x != null; x = x.next) {
      queue.enqueue(x.key);
    }
    
    return queue;
  }
  
  private Node delete(Node x, Key key) {
    // Delete key in linked list beginning at Node x
    if (x == null) {
      return null;
    }
    
    if (key.equals(x.key)) {
      n--;
      return x.next;
    }
    
    x.next = delete(x.next, key);
    return x;
  }
  
  private class Node {
    Key key;
    Value value;
    Node next;
    
    public Node(Key key, Value value, Node next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }
}