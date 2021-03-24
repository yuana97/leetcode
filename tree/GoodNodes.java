package tree;

import java.util.*;

/*
Q: Given a binary tree 'root', a node X in the tree is called 'good' if
in the path from root to X, there are no nodes with a value greater than X.

Return the number of good nodes in the tree.

S:
- DFS returning the number of 'good' nodes in the subtree.
- To achieve this, pass a 'max value in path' variable in the DFS.
  - at each node, add '1' to the overall answer if the node value is greater than the max value.
*/

public class GoodNodes {
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
  }

  public int goodNodes(TreeNode root) {
    return dfs(root, root.val);
  }

  private int dfs(TreeNode n, int prevMax) {
    if (n == null) {
      return 0;
    }
    // update max for children
    int max = Math.max(n.val, prevMax);
    return (n.val >= prevMax ? 1 : 0) + dfs(n.left, max) + dfs(n.right, max);
  }
}
