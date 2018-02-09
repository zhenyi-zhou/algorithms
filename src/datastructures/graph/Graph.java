package datastructures.graph;

import datastructures.linkedlist.Bag;

public class Graph {
  private final int v;        // number of vertices
  private int e;              // number of edges
  private Bag<Integer>[] adj; // adjacency lists
  
  public Graph(int v) {
    this.v = v;
    this.e = 0;
    adj = (Bag<Integer>[]) new Bag[v];
    for (int i = 0; i < v; i++) {
      adj[i] = new Bag<Integer>();
    } 
  }
  
  public Graph(int[][] g) {
    this(g[0][0]);
    int e = g[0][1];
    for (int i = 1; i <= e; i++) {
      int v = g[i][0];
      int w = g[i][1];
      addEdge(v, w);
    } 
  }
  
  public int v() {
    return v;
  }
  
  public int e() {
    return e;
  }
  
  public void addEdge(int v, int w) {
    adj[v].add(w);
    adj[w].add(v);
    e++;
  }
  
  public Iterable<Integer> adj(int v) {
    return adj[v];
  }
}