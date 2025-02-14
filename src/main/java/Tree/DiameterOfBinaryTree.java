package Tree;

import Common.TreeNode;

/**
 * Given a binary tree, you need to compute the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * <p>
 * Example:
 * Given a binary tree
 * 1
 * /   \
 * 2    3
 * /  \
 * 4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * <p>
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBinaryTree {
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (null == root) {
            return 0;
        }
        maxDepth(root);
        return diameter;
    }
    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int l = maxDepth(root.left);
        int r = maxDepth(root.right);
        if (l + r > diameter) {
            diameter = l + r;
        }
        return l >= r ? l + 1 : r + 1;
    }
}