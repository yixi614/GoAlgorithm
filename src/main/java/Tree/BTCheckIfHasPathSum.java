package Tree;

import Common.TreeNode;

/**
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 *
 * A leaf is a node with no children.
 *
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Explanation: The root-to-leaf path with the target sum is shown.
 *
 * Input: root = [1,2,3], targetSum = 5
 * Output: false
 * Explanation: There are two root-to-leaf paths in the tree:
 * (1 --> 2): The sum is 3.
 * (1 --> 3): The sum is 4.
 * There is no root-to-leaf path with sum = 5.
 *
 * Input: root = [], targetSum = 0
 * Output: false
 * Explanation: Since the tree is empty, there are no root-to-leaf paths.
 * */

public class BTCheckIfHasPathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // return false if the root is null
        if(root == null) return false;
        // if it reaches to the end and the val is equal to the sum, return true
        if(root.left == null && root.right == null && root.value == targetSum) {
            return true;
        }
        // otherwise, we traverse left node and right node with the new targetSum `targetSum - root.val`
        return hasPathSum(root.left, targetSum - root.value) ||
                hasPathSum(root.right, targetSum - root.value);
    }
}
