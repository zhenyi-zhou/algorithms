package graphs.shortestpaths;

import datastructures.linkedlist.Stack;
import datastructures.priorityqueue.IndexMinPQ;
import datastructures.graph.DirectedEdge;
import datastructures.graph.EdgeWeightedDigraph;

public class DijkstraSP {
  private DirectedEdge[] edgeTo;
  private double[] distTo;
  private IndexMinPQ<Double> pq;
  
  public DijkstraSP(EdgeWeightedDigraph g, int s) {
    edgeTo = new DirectedEdge[g.v()];
    distTo = new double[g.v()];
    for (int v = 0; v < g.v(); v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
    }
    pq = new IndexMinPQ<Double>(g.v());
    
    distTo[s] = 0.0;
    pq.insert(s, 0.0);
    while (!pq.isEmpty()) {
      relax(g, pq.deleteMin());
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
  
  private void relax(EdgeWeightedDigraph g, int v) {
    for (DirectedEdge e : g.adj(v)) {
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
        if (pq.contains(w)) {
          pq.change(w, distTo[w]);
        } else {
          pq.insert(w, distTo[w]);
        }
      }
    }
  }
}