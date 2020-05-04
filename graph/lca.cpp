#include <cstddef>
#include <unordered_map>
#include <stack>
#include <set>
#include <iostream>

using namespace std;

/*
Question:
Given a binary tree and two nodes in it p and q, return their lowest common ancestor
in the tree.

Solution:
Create a parent-child map as you traverse the tree. Once you find both p and q, travel
up their chain of parents until you find the first intersection.

*/

struct TreeNode
{
  int val;
  TreeNode *left;
  TreeNode *right;
  TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution
{
public:
  TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q)
  {
    if (!root || root == p || root == q)
      return root;
    // initialize parent mapping
    unordered_map<TreeNode *, TreeNode *> parent;
    parent[root] = NULL;
    // initialize stack for dfs
    stack<TreeNode *> stk;
    stk.push(root);
    // search tree until you find p and q
    while (!parent[p] || !parent[q])
    {
      // search left and right, populating parent map as we go
      TreeNode *node = stk.top();
      stk.pop();
      if (node->left)
      {
        parent[node->left] = node;
        stk.push(node->left);
      }
      if (node->right)
      {
        parent[node->right] = node;
        stk.push(node->right);
      }
    }

    set<TreeNode *> ancestor;
    // insert all parents of p into ancestor set
    while (p)
    {
      ancestor.insert(p);
      p = parent[p];
    }
    // find the first ancestor of q which is also an ancestor of p
    while (ancestor.find(q) == ancestor.end())
    {
      q = parent[q];
    }
    return q;
  }
};
// this unit test doesn't quite work
int main()
{
  TreeNode *zero = & TreeNode(0);
  TreeNode *one = &TreeNode(1);
  TreeNode *two = &TreeNode(2);
  TreeNode *three = &TreeNode(3);
  zero->left = one;
  one->left = two;
  one->right = three;

  Solution solution;
  // expected value: 1
  cout << solution.lowestCommonAncestor(zero, two, three)->val;
}