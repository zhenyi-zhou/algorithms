package graphs.shortestpaths;

import datastructures.linkedlist.Stack;
import datastructures.graph.DirectedEdge;
import datastructures.graph.EdgeWeightedDigraph;
import graphs.directedgraphs.Topological;

public class AcyclicSP {
  private DirectedEdge[] edgeTo;
  private double[] distTo;
  
  public AcyclicSP(EdgeWeightedDigraph g, int s) {
    edgeTo = new DirectedEdge[g.v()];
    distTo = new double[g.v()];
    for (int v = 0; v < g.v(); v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
    }
    
    distTo[s] = 0.0;
    Topological top = new Topological(g);
    for (int v : top.order()) {
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
  
  private void relax(EdgeWeightedDigraph g, int v) {
    for (DirectedEdge e : g.adj(v)) {
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
      }
    }
  }
}