package datastructures.graph;

import datastructures.linkedlist.Bag;

public class EdgeWeightedGraph {
  private final int v;
  private int e;
  private Bag<Edge>[] adj;
  
  public EdgeWeightedGraph(int v) {
    this.v = v;
    this.e = 0;
    adj = (Bag<Edge>[]) new Bag[v];
    for (int i = 0; i < v; i++) {
      adj[i] = new Bag<Edge>();
    }
  }
  
  public EdgeWeightedGraph(double[][] g) {
    this((int) g[0][0]);
    int e = (int) g[0][1];
    for (int i = 1; i <= e; i++) {
      Edge edge = new Edge((int) g[i][0], (int) g[i][1], g[i][2]);
      addEdge(edge);
    } 
  }
  
  public int v() {
    return v;
  }
  
  public int e() {
    return e;
  }
  
  public void addEdge(Edge e) {
    int v = e.either();
    int w = e.other(v);
    adj[v].add(e);
    adj[w].add(e);
    this.e++;
  }
  
  public Iterable<Edge> adj(int v) {
    return adj[v];
  }
  
  public Iterable<Edge> edges() {
    Bag<Edge> bag = new Bag<Edge>();
    for (int i = 0; i < v; i++) {
      for (Edge e : adj[i]) {
        if (e.other(i) > i) {
          bag.add(e);
        }
      }
    }
    return bag;
  }
}