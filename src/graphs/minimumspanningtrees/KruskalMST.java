package graphs.minimumspanningtrees;

import datastructures.linkedlist.Queue;
import datastructures.priorityqueue.MinPQ;
import datastructures.graph.Edge;
import datastructures.graph.EdgeWeightedGraph;
import datastructures.unionfind.UnionFind;

public class KruskalMST {
  private Queue<Edge> mst;
  
  public KruskalMST(EdgeWeightedGraph g) {
    mst = new Queue<Edge>();
    MinPQ<Edge> pq = new MinPQ<Edge>();
    for (Edge e : g.edges()) {
      pq.insert(e);
    }
    
    UnionFind uf = new UnionFind(g.v());
    
    while (!pq.isEmpty() && mst.size() < g.v() - 1) {
      Edge e = pq.deleteMin();  // get min weight edge on pq
      int v = e.either();       // get its vertices
      int w = e.other(v);       
      if (uf.connected(v, w)) { // ignore ineligible edges
        continue;
      }
      uf.union(v, w);           // merge components
      mst.enqueue(e);           // add edge to mst
    }
  }
  
  public Iterable<Edge> edges() {
    return mst;
  }
  
  public double weight() {
    double weight = 0.0;
    for (Edge e : edges()) {
      weight += e.weight();
    }
    return weight;
  }
}