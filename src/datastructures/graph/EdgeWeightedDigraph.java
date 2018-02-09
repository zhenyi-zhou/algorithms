package datastructures.graph;

import datastructures.linkedlist.Bag;

public class EdgeWeightedDigraph {
  private final int v;
  private int e;
  private Bag<DirectedEdge>[] adj;
  
  public EdgeWeightedDigraph(int v) {
    this.v = v;
    this.e = 0;
    adj = (Bag<DirectedEdge>[]) new Bag[v];
    for (int i = 0; i < v; i++) {
      adj[i] = new Bag<DirectedEdge>();
    }
  }
  
  public EdgeWeightedDigraph(double[][] g) {
    this((int) g[0][0]);
    int e = (int) g[0][1];
    for (int i = 1; i <= e; i++) {
      DirectedEdge edge = new DirectedEdge((int) g[i][0], (int) g[i][1], g[i][2]);
      addEdge(edge);
    } 
  }
  
  public int v() {
    return v;
  }
  
  public int e() {
    return e;
  }
  
  public void addEdge(DirectedEdge e) {
    adj[e.from()].add(e);
    this.e++;
  }
  
  public Iterable<DirectedEdge> adj(int v) {
    return adj[v];
  }
  
  public Iterable<DirectedEdge> edges() {
    Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
    for (int i = 0; i < v; i++) {
      for (DirectedEdge e : adj[i]) {
        bag.add(e);
      }
    }
    return bag;
  }
}