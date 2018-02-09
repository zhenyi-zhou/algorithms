package graphs.minimumspanningtrees;

import datastructures.linkedlist.Queue;
import datastructures.priorityqueue.IndexMinPQ;
import datastructures.graph.Edge;
import datastructures.graph.EdgeWeightedGraph;

public class PrimMST {
  private Edge[] edgeTo;         // shortest edge from tree vertex
  private double[] distTo;       // distTo[w] = edgeTo[w].weigth()
  private boolean[] marked;      // true if v on tree
  private IndexMinPQ<Double> pq; // eligible crossing edges
  
  public PrimMST(EdgeWeightedGraph g) {
    edgeTo = new Edge[g.v()];
    distTo = new double[g.v()];
    marked = new boolean[g.v()];
    for (int v = 0; v < g.v(); v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
    }
    pq = new IndexMinPQ<Double>(g.v());
    
    distTo[0] = 0.0;
    pq.insert(0, 0.0);          // initialize pq with 0, weight 0
    while (!pq.isEmpty()) {
      visit(g, pq.deleteMin()); // add closest vertex to tree
    }
  }
  
  public Iterable<Edge> edges() {
    Queue<Edge> mst = new Queue<Edge>();
    for (int v = 0; v < edgeTo.length; v++) {
      Edge e = edgeTo[v];
      if (e != null) {
        mst.enqueue(e);
      }
    }
    return mst;
  }
  
  public double weight() {
    double weight = 0.0;
    for (Edge e : edges()) {
      weight += e.weight();
    }
    return weight;
  }
  
  private void visit(EdgeWeightedGraph g, int v) {
    // Add v to tree, update data structures.
    marked[v] = true;
    for (Edge e : g.adj(v)) {
      int w = e.other(v);
      if (marked[w]) {
        continue; // v-w is ineligible
      }
      
      // Edge e is new best connection from tree to w.
      if (e.weight() < distTo[w]) {
        edgeTo[w] = e;
        distTo[w] = e.weight();
        if (pq.contains(w)) {
          pq.change(w, distTo[w]);
        } else {
          pq.insert(w, distTo[w]);
        }
      }
    }
  }
}