package Tree;

import Common.TreeNode;

/**
 * 236 lowest common ancestor
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 *
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants
 * (where we allow a node to be a descendant of itself).”
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.
 *
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 * */
public class BSTLowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // if we find p or q, we directly return due to no lower level nodes can be ancestor. ancestor must be in the upper level
        // so no need to dfs again
        if (root == null || root == p || root == q) {
            return root;
        }
        // LCA can be root if root's left and right subtree contains p or q. It's similar to post-order, look at left and right and then root node
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // got p and q from substrees
        if (left != null && right != null) {
            return root;
        }
        // LCA can also be the root itself if root is one of p and q and its subtree contains the other target node.
        // return root so that the upper invocation will decide
        return left != null ? left : right;
    }
}
