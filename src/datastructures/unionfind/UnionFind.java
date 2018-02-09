package datastructures.unionfind;

public class UnionFind { // weighted quick-union
  private int[] id;  // access to component ID
  private int[] sz;  // size of component for roots
  private int count; // number of components
  
  public UnionFind(int n) {
    count = n;
    id = new int[n];
    
    for (int i = 0; i < n; i++) {
      id[i] = i;
    }
    
    sz = new int[n];
    
    for (int i = 0; i < n; i++) {
      sz[i] = 1;
    }
  }
  
  public int count() {
    return count;
  }
  
  public boolean connected(int x, int y) {
    return find(x) == find(y);
  }
  
  public int find(int x) {
    // Follow links to find a root
    while (x != id[x]) {
      x = id[x];
    }
    
    return x;
  }
  
  public void union(int x, int y) {
    int i = find(x);
    int j = find(y);
    
    if (i == j) {
      return;
    }
    
    // Make smaller root point to larger one
    if (sz[i] < sz[j]) {
      id[i] = j;
      sz[j] += sz[i];
    } else {
      id[j] = i;
      sz[i] += sz[j];
    }
    
    count--;
  }

}