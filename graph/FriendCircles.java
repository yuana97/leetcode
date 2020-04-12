package graph;

/*

Question:
N students in a class, we have an NxN adjacency matrix M of students' friendships
(Mij = 1 => i is friends with j). Output the total number of friend circles in the
classroom (a friend circle is a group of friend of friend of friend ...)

Solution:
Union find. This problem reduces to finding connected components in the friendship graph.
We first consider each vertex (student) as its own component (friend group). Then for every pair
of friends we merge the components. At the end we count the remaining unique components.

*/

public class FriendCircles {
  // union-find helper class
  class UF {
    // in union find we have the idea of marking each component by its parent (original vertex)
    // parent[i] = component i belongs to, size[i] = size of component i
    private int[] parent, size;
    // count = # unique components
    private int count;

    // constructor: set the initial state
    public UF(int n) {
      parent = new int[n];
      size = new int[n];
      count = n;
      for (int i = 0; i < n; i++) {
        parent[i] = i;
        size[i] = 1;
      }
    }

    // finds the root of the vertex p and labels all intermediate vertices
    public int find(int p) {
      while (p != parent[p]) {
        parent[p] = parent[parent[p]];
        p = parent[p];
      }
      return p;
    }

    // merge components p and q
    public void union(int p, int q) {
      // get the roots, skip if they're already merged
      int rootP = find(p);
      int rootQ = find(q);
      if (rootP == rootQ) return;
      // union the smaller component into the bigger component
      if (size[rootP] > size[rootQ]) {
        parent[rootQ] = rootP;
        size[rootP] += size[rootQ];
      } else {
        parent[rootP] = rootQ;
        size[rootQ] += size[rootP];
      }
      count--;
    }

    // getter function for count
    public int count() { return count; }
  }

  // count the friend circles
  public int findCircleNum(int[][] M) {
    int n = M.length;
    UF uf = new UF(n);
    // merge each unique friend pair
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++)
        if (M[i][j] == 1)
          uf.union(i, j);
    }
    return uf.count();
  }
}