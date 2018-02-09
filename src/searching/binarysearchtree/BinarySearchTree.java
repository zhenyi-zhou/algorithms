package searching.binarysearchtree;

import datastructures.linkedlist.Queue;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
  private Node root;
  
  public boolean isEmpty() {
    return size() == 0;
  }
  
  public int size() {
    return size(root);
  }
  
  public boolean contains(Key key) {
    return get(key) != null;
  }
  
  public void put(Key key, Value value) {
    root = put(root, key, value);
  }
  
  public Value get(Key key) {
    return get(root, key);
  }
  
  public void delete(Key key) {
    root = delete(root, key);
  }
  
  public int rank(Key key) {
    return rank(root, key);
  }
  
  public Iterable<Key> keys() {
    return keys(min(), max());
  }
  
  public Iterable<Key> keys(Key low, Key high) {
    Queue<Key> queue = new Queue<Key>();
    keys(root, queue, low, high);
    return queue;
  }
  
  public Key min() {
    return min(root).key;
  }
  
  public Key max() {
    return max(root).key;
  }
  
  public void deleteMin() {
    root = deleteMin(root);
  }
  
  public void deleteMax() {
    root = deleteMax(root);
  }
  
  public Key select(int k) {
    return select(root, k).key;
  }
  
  public Key ceiling(Key key) {
    Node node = ceiling(root, key);
    
    if (node == null) {
      return null;
    }
    
    return node.key;
  }
  
  public Key floor(Key key) {
    Node node = floor(root, key);
    
    if (node == null) {
      return null;
    }
    
    return node.key;
  }
  
  private int size(Node node) {
    if (node == null) {
      return 0;
    }
    
    return node.n;
  }
  
  private Node put(Node node, Key key, Value value) {
    if (node == null) {
      return new Node(key, value, 1);
    }
    
    int num = key.compareTo(node.key);
    
    if (num < 0) {
      node.left = put(node.left, key, value);
    } else if (num > 0) {
      node.right = put(node.right, key, value);
    } else {
      node.value = value;
    }
    
    node.n = size(node.left) + size(node.right) + 1;
    return node;
  }
  
  private Value get(Node node, Key key) {
    if (node == null) {
      return null;
    }
    
    int num = key.compareTo(node.key);
    
    if (num < 0) {
      return get(node.left, key);
    } else if (num > 0) {
      return get(node.right, key);
    } else {
      return node.value;
    }
  }
  
  private Node delete(Node node, Key key) {
    if (node == null) {
      return null;
    }
    
    int num = key.compareTo(node.key);
    
    if (num < 0) {
      node.left = delete(node.left, key);
    } else if (num > 0) {
      node.right = delete(node.right, key);
    } else {
      if (node.right == null) {
        return node.left;
      }
      
      if (node.left == null) {
        return node.right;
      }
      
      Node t = node;
      node = min(t.right);
      node.right = deleteMin(t.right);
      node.left = t.left;
    }
    
    node.n = size(node.left) + size(node.right) + 1;
    return node;
  }
  
  private int rank(Node node, Key key) {
    if (node == null) {
      return 0;
    }
    
    int num = key.compareTo(node.key);
    
    if (num < 0) {
      return rank(node.left, key);
    } else if (num > 0) {
      return rank(node.right, key) + size(node.left) + 1;
    } else {
      return size(node.left);
    }
  }
  
  private void keys(Node node, Queue<Key> queue, Key low, Key high) {
    if (node == null) {
      return;
    }
    
    int numl = low.compareTo(node.key);
    int numh = high.compareTo(node.key);
    
    if (numl < 0) {
      keys(node.left, queue, low, high);
    }
    
    if (numl <= 0 && numh >= 0) {
      queue.enqueue(node.key);
    }
    
    if (numh > 0) {
      keys(node.right, queue, low, high);
    }
  }
  
  private Node min(Node node) {
    if (node.left == null) {
      return node;
    }
    
    return min(node.left);
  }
  
  private Node max(Node node) {
    if (node.right == null) {
      return node;
    }
    
    return max(node.right);
  }
  
  private Node deleteMin(Node node) {
    if (node.left == null) {
      return node.right;
    }
    
    node.left = deleteMin(node.left);
    node.n = size(node.left) + size(node.right) + 1;
    return node;
  }

  private Node deleteMax(Node node) {
    if (node.right == null) {
      return node.left;
    }
    
    node.right = deleteMax(node.right);
    node.n = size(node.left) + size(node.right) + 1;
    return node;
  }
  
  private Node select(Node node, int k) {
    if (node == null) {
      return null;
    }
    
    int t = size(node.left);
    
    if (t > k) {
      return select(node.left, k);
    } else if (t < k) {
      return select(node.right, k - t - 1);
    } else {
      return node;
    }
  }
  
  private Node ceiling(Node node, Key key) {
    if (node == null) {
      return null;
    }
    
    int num = key.compareTo(node.key);
    
    if (num == 0) {
      return node;
    }
    
    if (num > 0) {
      return ceiling(node.right, key);
    }
    
    Node t = ceiling(node.left, key);
    
    if (t != null) {
      return t;
    } else {
      return node;
    }
  }
  
  private Node floor(Node node, Key key) {
    if (node == null) {
      return null;
    }
    
    int num = key.compareTo(node.key);
    
    if (num == 0) {
      return node;
    }
    
    if (num < 0) {
      return floor(node.left, key);
    }
    
    Node t = floor(node.right, key);
    
    if (t != null) {
      return t;
    } else {
      return node;
    }
  }
  
  private class Node {
    private Key key;
    private Value value;
    private Node left;
    private Node right;
    private int n;
    
    public Node(Key key, Value value, int n) {
      this.key = key;
      this.value = value;
      this.n = n;
    }
  }
}