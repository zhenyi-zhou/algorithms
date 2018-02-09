package graphs.shortestpaths;

import datastructures.linkedlist.Queue;
import datastructures.linkedlist.Stack;
import datastructures.graph.DirectedEdge;
import datastructures.graph.EdgeWeightedDigraph;
import graphs.directedgraphs.EdgeWeightedDirectedCycle;

public class BellmanFordSP {
  private DirectedEdge[] edgeTo;        // length of path to v
  private double[] distTo;              // last edge on path to v
  private boolean[] onQ;                // is this vertex on the queue?
  private Queue<Integer> queue;         // vertices being relaxed
  private int cost;                     // number of calls to relax()
  private Iterable<DirectedEdge> cycle; // negative cycle in edgeTo[]?
  
  public BellmanFordSP(EdgeWeightedDigraph g, int s) {
    edgeTo = new DirectedEdge[g.v()];
    distTo = new double[g.v()];
    for (int v = 0; v < g.v(); v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
    }
    onQ = new boolean[g.v()];
    queue = new Queue<Integer>();
    
    distTo[s] = 0.0;
    queue.enqueue(s);
    onQ[s] = true;
    while (!queue.isEmpty() && !this.hasNegativeCycle()) {
      int v = queue.dequeue();
      onQ[v] = false;
      relax(g, v);
    }
  }
  
  public double distTo(int v) {
    return distTo[v];
  }
  
  public boolean hasPathTo(int v) {
    return distTo[v] < Double.POSITIVE_INFINITY;
  }
  
  public Iterable<DirectedEdge> pathTo(int v) {
    if (!hasPathTo(v)) {
      return null;
    }
    Stack<DirectedEdge> path = new Stack<DirectedEdge>();
    for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
      path.push(e);
    }
    return path;
  }
  
  public boolean hasNegativeCycle() {
    return cycle != null;
  }
  
  public Iterable<DirectedEdge> negativeCycle() {
    return cycle;
  }
  
  private void relax(EdgeWeightedDigraph g, int v) {
    for (DirectedEdge e : g.adj(v)) {
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
        if (!onQ[w]) {
          queue.enqueue(w);
          onQ[w] = true;
        }
      }
      if (cost++ % g.v() == 0) {
        findNegativeCycle();
        if (hasNegativeCycle()) {
          return;
        }
      }
    }
  }
  
  private void findNegativeCycle() {
    int v = edgeTo.length;
    EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(v);
    for (int i = 0; i < v; i++) {
      if (edgeTo[i] != null) {
        ewd.addEdge(edgeTo[i]);
      }
    }
    
    EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(ewd);
    cycle = finder.cycle();
  }
}