package Tree;

import Common.TreeNode;

/**
 * given a binary tree, check if it's symmetrical
 *
 *          1
 *        /   \
 *       2     2
 *        \   /
 *        3  3
 * */
public class BTCheckIfIsSymmetrical {
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }

        if (n1 == null || n2 == null) {
            return false;
        }

        return n1.value == n2.value && isMirror(n1.left, n2.right) && isMirror(n1.right, n2.left);
    }
}
