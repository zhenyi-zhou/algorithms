package graphs.directedgraphs;

import datastructures.linkedlist.Queue;
import datastructures.linkedlist.Stack;
import datastructures.graph.Digraph;
import datastructures.graph.DirectedEdge;
import datastructures.graph.EdgeWeightedDigraph;

public class DepthFirstOrder {
  private boolean[] marked;           // has dfs() been called for this vertex?
  private Queue<Integer> pre;         // vertices in preorder
  private Queue<Integer> post;        // vertices in postorder
  private Stack<Integer> reversePost; // vertices in reverse postorder
  
  public DepthFirstOrder(Digraph g) {
    pre = new Queue<Integer>();
    post = new Queue<Integer>();
    reversePost = new Stack<Integer>();
    marked = new boolean[g.v()];
    
    for (int v = 0; v < g.v(); v++) {
      if (!marked[v]) {
        dfs(g, v);
      }
    } 
  }
  
  public DepthFirstOrder(EdgeWeightedDigraph g) {
    pre = new Queue<Integer>();
    post = new Queue<Integer>();
    reversePost = new Stack<Integer>();
    marked = new boolean[g.v()];
    
    for (int v = 0; v < g.v(); v++) {
      if (!marked[v]) {
        dfs(g, v);
      }
    } 
  }
  
  public Iterable<Integer> pre() {
    return pre;
  }
  
  public Iterable<Integer> post() {
    return post;
  }
  
  public Iterable<Integer> reversePost() {
    return reversePost;
  }
  
  private void dfs(Digraph g, int v) {
    pre.enqueue(v);
    marked[v] = true;
    for (int w : g.adj(v)) {
      if (!marked[w]) {
        dfs(g, w);
      }
    }
    post.enqueue(v);
    reversePost.push(v);
  }
  
  private void dfs(EdgeWeightedDigraph g, int v) {
    pre.enqueue(v);
    marked[v] = true;
    for (DirectedEdge e : g.adj(v)) {
      int w = e.to();
      if (!marked[w]) {
        dfs(g, w);
      }
    }
    post.enqueue(v);
    reversePost.push(v);
  }
}