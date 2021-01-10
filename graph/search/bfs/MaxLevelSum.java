package graph.search.bfs;

import java.util.*;

/*
Q: Given a binary tree, return the maximum sum of one of its levels.

S: BFS to grab the level sums and return the max.

Script:
- Are we guaranteed that the input is not null?
- Alright, let's handle it by throwing an exception.

- Let me give a quick overview of the problem.
- Essentially, we want to compare each level sum and find the max.
- We can calculate each level sum using a BFS, and then get the max.
- This should give an O(N) solution to the problem, where N is the size of the tree.
- If you agree with my analysis, I can start coding it up.
*/

public class MaxLevelSum {
  // node class
  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  public int maxLevelSum(TreeNode root) throws IllegalArgumentException {
    if (root == null) {
      throw new IllegalArgumentException("Root must not be null");
    }
    // init result
    int max = Integer.MIN_VALUE;
    // q = next level of bfs
    Queue<TreeNode> q = new LinkedList<>();
    // put the root as the first level of bfs
    q.offer(root);
    // process each level
    while (!q.isEmpty()) {
      // record size so we know when we're done processing a level
      int levelSize = q.size();
      int levelSum = 0;
      for (int i = 0; i < levelSize; i++) {
        TreeNode nextNode = q.poll();
        levelSum += nextNode.val;
        // add nodes of the next level to the queue
        if (nextNode.left != null) {
          q.offer(nextNode.left);
        }
        if (nextNode.right != null) {
          q.offer(nextNode.right);
        }
      }

      if (levelSum > max) {
        max = levelSum;
      }
    }
    return max;
  }
}
