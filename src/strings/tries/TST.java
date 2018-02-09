package strings.tries;

import datastructures.linkedlist.Queue;

public class TST<Value> {
  private Node<Value> root;
  
  public void put(String key, Value val) {
    root = put(root, key, val, 0);
  }
  
  public Value get(String key) {
    Node x = get(root, key, 0);
    if (x == null) {
      return null;
    }
    return (Value) x.val;
  }
  
  public Iterable<String> keys() {
    Queue<String> q = new Queue<String>();
    collect(root, new StringBuilder(), q);
    return q;
  }
  
  public Iterable<String> keysWithPrefix(String pre) {
    Queue<String> q = new Queue<String>();
    Node<Value> x = get(root, pre, 0);
    if (x == null) {
      return q;
    }
    if (x.val != null) {
      q.enqueue(pre);
    }
    collect(x.mid, new StringBuilder(pre), q);
    return q;
  }
  
  public Iterable<String> keysThatMatch(String pat) {
    Queue<String> q = new Queue<String>();
    collect(root, new StringBuilder(), 0, pat, q);
    return q;
  }
  
  public String longestPrefixOf(String s) {
    if (s.length() == 0) {
      return null;
    }
    int length = 0;
    Node<Value> x = root;
    int i = 0;
    while (x != null && i < s.length()) {
      char c = s.charAt(i);
      if (c < x.c) {
        x = x.left;
      } else if (c > x.c) {
        x = x.right;
      } else {
        i++;
        if (x.val != null) {
          length = i;
        }
        x = x.mid;
      }
    }
    return s.substring(0, length);
  }
  
  private Node put(Node x, String key, Value val, int d) {
    char c = key.charAt(d);
    if (x == null) {
      x = new Node();
      x.c = c;
    }
    if (c < x.c) {
      x.left = put(x.left, key, val, d);
    } else if (c > x.c) {
      x.right = put(x.right, key, val, d);
    } else if (d < key.length() - 1) {
      x.mid = put(x.mid, key, val, d + 1);
    } else {
      x.val = val;
    }
    return x;
  }
  
  private Node get(Node x, String key, int d) {
    if (x == null) {
      return null;
    }
    char c = key.charAt(d);
    if (c < x.c) {
      return get(x.left, key, d);
    } else if (c > x.c) {
      return get(x.right, key, d);
    } else if (d < key.length() - 1) {
      return get(x.mid, key, d + 1);
    } else {
      return x;
    }
  }
  
  private void collect(Node<Value> x, StringBuilder pre, Queue<String> q) {
    if (x == null) {
      return;
    }
    collect(x.left, pre, q);
    if (x.val != null) {
      q.enqueue(pre.toString() + x.c);
    }
    collect(x.mid, pre.append(x.c), q);
    pre.deleteCharAt(pre.length() - 1);
    collect(x.right, pre, q);
  }
  
  private void collect(Node<Value> x, StringBuilder pre, int i, String pat, Queue<String> q) {
    if (x == null) {
      return;
    }
    char c = pat.charAt(i);
    if (c == '.' || c < x.c) {
      collect(x.left, pre, i, pat, q);
    }
    if (c == '.' || c == x.c) {
      if (i == pat.length() - 1 && x.val != null) {
        q.enqueue(pre.toString() + x.c);
      }
      if (i < pat.length() - 1) {
        collect(x.mid, pre.append(x.c), i + 1, pat, q);
        pre.deleteCharAt(pre.length() - 1);
      }
    }
    if (c == '.' || c > x.c) {
      collect(x.right, pre, i, pat, q);
    }
  }
  
  private class Node<Value> {
    char c;
    Node<Value> left, mid, right;
    Value val;
  }
}