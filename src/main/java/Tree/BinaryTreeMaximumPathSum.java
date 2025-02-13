package Tree;

import Common.TreeNode;

/**
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in
 * the sequence has an edge connecting them.
 * A node can only appear in the sequence at most once.
 * Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any path.
 *
 *            1
 *         2     3
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 *
 *           -10
 *     9            20
 *              15     7
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 * */
public class BinaryTreeMaximumPathSum {
  private int maxSum = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    helper(root);
    return maxSum;
  }

  private int helper(TreeNode node) {
    if (node == null) {
      return 0;
    }

    // postorder traversal
    int leftMaxPath = Math.max(helper(node.left), 0);
    int rightMaxPath = Math.max(helper(node.right), 0);
    // update global max path sum
    // This value gives the highest path sum if the path is allowed to "turn" at the current node.
    int maxIfNodeIsRoot = node.value + leftMaxPath + rightMaxPath;
    maxSum = Math.max(maxSum, maxIfNodeIsRoot);

    // For the parent node,
    // return the maximum path sum that can continue to be part of a larger path: node.val + max(leftMaxPath, rightMaxPath)
    return node.value + Math.max(leftMaxPath, rightMaxPath);
  }
}

/**
 * First, ask yourself which tree traversal strategy you will use. Preorder, Inorder, or Postorder?
 *
 * In our case, to calculate the max path sum for a given node,
 * we need to know the max path of the left and right subtrees first. Therefore, we will be using a postorder traversal.
 *
 * In the post order traversal, we need to account for two things:
 *
 * Global Maximum Path Sum:
 * This considers the current node as the root of the path and includes contributions from both its left and right subtrees.
 * This value gives the highest path sum if the path is allowed to "turn" at the current node.
 * Current Path Sum: This is the maximum path sum at the current node that can continue upwards to its parent.
 * A parent node can only use one of its child's paths in its calculation.
 * For clarity:
 *
 * If a subtree's contribution is negative, it's better to ignore it entirely, as adding it would reduce the overall sum.
 * At each node, we calculate two values:
 * Maximum path sum considering the node as the root: node.val + leftMaxPath + rightMaxPath.
 * Path sum for continuation: node.val + max(leftMaxPath, rightMaxPath).
 * Approach
 * Here’s how we can implement this efficiently using a recursive helper function:
 *
 * Base Case: If a node is Null, its contribution to the path sum is 0.
 * Recursive Step: At each node:
 * Calculate the maximum path sum for the left and right subtrees.
 * Ignore any subtree that has a negative contribution by taking max(leftSubtreeSum, 0) and max(rightSubtreeSum, 0).
 * Compute the maximum path sum considering the current node as the root: node.val + leftMaxPath + rightMaxPath.
 * Update the global maximum if this value is greater than the previously recorded maximum.
 * Return Value: For the parent node, return the maximum path sum that can continue to be part of a larger path: node.val + max(leftMaxPath, rightMaxPath).
 * Example Walkthrough
 *     -10
 *     /  \
 *    9   20
 *       /  \
 *      15   7
 * Recursive Steps
 * At Node 9:
 *
 * Both left and right children are None, so their contributions are 0.
 * The maximum path sum considering 9 as root is 9 + 0 + 0 = 9.
 * For continuation, the contribution is 9 + max(0, 0) = 9.
 * At Node 15:
 *
 * Both left and right children are None, so their contributions are 0.
 * The maximum path sum considering 15 as root is 15 + 0 + 0 = 15.
 * For continuation, the contribution is 15 + max(0, 0) = 15.
 * At Node 7:
 *
 * Both left and right children are None, so their contributions are 0.
 * The maximum path sum considering 7 as root is 7 + 0 + 0 = 7.
 * For continuation, the contribution is 7 + max(0, 0) = 7.
 * At Node 20:
 *
 * Left subtree contributes 15, right subtree contributes 7.
 * The maximum path sum considering 20 as root is 20 + 15 + 7 = 42.
 * For continuation, the contribution is 20 + max(15, 7) = 35.
 * At Node -10:
 *
 * Left subtree contributes 9, right subtree contributes 35.
 * The maximum path sum considering -10 as root is -10 + 9 + 35 = 34.
 * For continuation, the contribution is -10 + max(9, 35) = 25.
 * Final Result
 * The global maximum path sum is 42, achieved at the subtree rooted at 20.
 *
 * Complexity
 * Time complexity:
 * O(N)
 *
 * Space complexity:
 * O(N)
 *
 * */