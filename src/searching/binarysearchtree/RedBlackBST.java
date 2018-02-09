package searching.binarysearchtree;

import datastructures.linkedlist.Queue;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
  private static final boolean RED = true;
  private static final boolean BLACK = false;
  private Node root;
  
  public boolean isEmpty() {
    return root == null;
  }
  
  public int size() {
    return size(root);
  }
  
  public boolean contains(Key key) {
    return get(key) != null;
  }
  
  public void put(Key key, Value value) {
    root = put(root, key, value);
    root.color = BLACK;
  }
  
  public Value get(Key key) {
    return get(root, key);
  }
  
  public void delete(Key key) {
    if (!contains(key)) {
      return;
    }
    
    // If both children of root are black, set root to red
    if (!isRed(root.left) && !isRed(root.right)) {
      root.color = RED;
    }
    
    root = delete(root, key);
    
    if (!isEmpty()) {
      root.color = BLACK;
    }
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
    // If both children of root are black, set root to red
    if (!isRed(root.left) && !isRed(root.right)) {
      root.color = RED;
    }
    
    root = deleteMin(root);
    
    if (!isEmpty()) {
      root.color = BLACK;
    }
  }
  
  public void deleteMax() {
    // If both children of root are black, set root to red
    if (!isRed(root.left) && !isRed(root.right)) {
      root.color = RED;
    }
    
    root = deleteMax(root);
    
    if (!isEmpty()) {
      root.color = BLACK;
    }
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
      return new Node(key, value, 1, RED);
    }
    
    int num = key.compareTo(node.key);
    
    if (num < 0) {
      node.left = put(node.left, key, value);
    } else if (num > 0) {
      node.right = put(node.right, key, value);
    } else {
      node.value = value;
    }
    
    if (isRed(node.right) && !isRed(node.left)) {
      node = rotateLeft(node);
    }
    
    if (isRed(node.left) && isRed(node.left.left)) {
      node = rotateRight(node);
    }
    
    if (isRed(node.left) && isRed(node.right)) {
      flipColors(node);
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
    int num = key.compareTo(node.key);
    
    if (num < 0) {
      if (!isRed(node.left) && !isRed(node.left.left)) {
        node = moveRedLeft(node);
      }
      
      node.left = delete(node.left, key);
    } else {
      if (isRed(node.left)) {
        node = rotateRight(node);
      }
      
      if (num == 0 && node.right == null) {
        return null;
      }
      
      if (!isRed(node.right) && !isRed(node.right.left)) {
        node = moveRedRight(node);
      }
      
      if (num == 0) {
        Node min = min(node.right);
        node.key = min.key;
        node.value = min.value;
        node.right = deleteMin(node.right);
      } else {
        node.right = delete(node.right, key);
      }
    }

    return balance(node);
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
      return null;
    }
    
    if (!isRed(node.left) && !isRed(node.left.left)) {
      node = moveRedLeft(node);
    }
    
    node.left = deleteMin(node.left);
    return balance(node);
  }

  private Node deleteMax(Node node) {
    if (isRed(node.left)) {
      node = rotateRight(node);
    }
    
    if (node.right == null) {
      return null;
    }
    
    if (!isRed(node.right) && !isRed(node.right.left)) {
      node = moveRedRight(node);
    }
    
    node.right = deleteMax(node.right);
    return balance(node);
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
  
  private boolean isRed(Node node) {
    if (node == null) {
      return false;
    }
    
    return node.color == RED;
  }
  
  private Node rotateLeft(Node p) {
    Node c = p.right;
    p.right = c.left;
    c.left = p;
    c.color = p.color;
    p.color = RED;
    c.n = p.n;
    p.n = size(p.left) + size(p.right) + 1;
    return c;
  }
  
  private Node rotateRight(Node p) {
    Node c = p.left;
    p.left = c.right;
    c.right = p;
    c.color = p.color;
    p.color = RED;
    c.n = p.n;
    p.n = size(p.left) + size(p.right) + 1;
    return c;
  }
  
  private void flipColors(Node node) {
    node.color = RED;
    node.left.color = BLACK;
    node.right.color = BLACK;
  }
  
  private Node moveRedLeft(Node node) {
    flipColors(node);
    
    if (isRed(node.right.left)) {
      node.right = rotateRight(node.right);
      node = rotateLeft(node);
      flipColors(node);
    }
    
    return node;
  }
  
  private Node moveRedRight(Node node) {
    flipColors(node);
    
    if (isRed(node.left.left)) {
      node = rotateRight(node);
      flipColors(node);
    }
    
    return node;
  }
  
  private Node balance(Node node) {
    if (isRed(node.right)) {
      node = rotateLeft(node);
    }
    
    if (isRed(node.left) && isRed(node.left.left)) {
      node = rotateRight(node);
    }
    
    if (isRed(node.left) && isRed(node.right)) {
      flipColors(node);
    }
    
    node.n = size(node.left) + size(node.right) + 1;
    return node;
  }
  
  private class Node {
    private Key key;
    private Value value;
    private Node left;
    private Node right;
    private int n;
    private boolean color;
    
    public Node(Key key, Value value, int n, boolean color) {
      this.key = key;
      this.value = value;
      this.n = n;
      this.color = color;
    }
  }
}