package Tree;

import Common.TreeNode;

public class MaxDepthOfBinaryTree {

  // if the node doesn't exist, simply return 0
  // Otherwise, return the 1 + the longer distance of its subtree
  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
  }
}
