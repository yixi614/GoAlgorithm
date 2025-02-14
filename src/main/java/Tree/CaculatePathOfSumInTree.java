package Tree;

import Common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where the sum of the node values in the path equals targetSum.
 * Each path should be returned as a list of the node values, not node references.
 * <p>
 * A root-to-leaf path is a path starting from the root and ending at any leaf node. A leaf is a node with no children.
 * <p>
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 * Explanation: There are two paths whose sum equals targetSum:
 * 5 + 4 + 11 + 2 = 22
 * 5 + 8 + 4 + 5 = 22
 * <p>
 * <p>
 * Input: root = [1,2,3], targetSum = 5
 * Output: []
 * <p>
 * Input: root = [1,2], targetSum = 0
 * Output: []
 */
public class CaculatePathOfSumInTree {

    public static TreeNode buildATree() {
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node5 = new TreeNode(5);
        TreeNode node12 = new TreeNode(12);
        node5.left = node4;
        node5.right = node7;
        node12.left = null;
        node12.right = null;
        TreeNode root = new TreeNode(10);
        root.left = node5;
        root.right = node12;
        return root;
    }

    static List<List<Integer>> ans = new ArrayList<>();

    // the idea is to use dfs to traverse the tree from all root to leaf paths
    // `path` is used to store the current route
    // `remainingSum` is used to store the remaining sum that we need with the initial value `targetSum`.
    //  we substract it from the node value when we traverse down the tree
    // if we arrive the leaf and the the remaining sum is eqaul to leaf node value
    // then we can add `path` to ans
    // e.g. path = [5,4,11,2] and remainingSum = targetSum = 22
    // traverse node 5, remainingSum = 22 - 5 = 17
    // traverse node 4, remainingSum = 17 - 4 = 13
    // traverse node 11, remainingSum = 13 - 11 = 2
    // traverse node 2, remainingSum = 2 - 2 = 0
    // remainingSum is 0 which means the sum of current path is eqaul to targetSum
    // so how to find another path?
    // we can backtrack here
    // we can pop back a node and try another
    // e.g. path = [5, 4, 11, 7]
    // pop 7 out = [5, 4, 11]
    // push 2 = [5, 4, 11, 2]
    // ... etc
    private static void dfs(TreeNode node, List<Integer> path, int remainingSum) {
        // if it is null, then return
        if (node == null) return;
        // add the current node val to path
        path.add(node.value);
        // !node.left && !node.right : check if it is a leaf node
        // remainingSum == node.val: check if the remaining sum - node.val == 0
        // if both condition is true, then we find one of the paths
        if (node.left == null && node.right == null && remainingSum == node.value) {
          ans.add(new ArrayList<>(path));
        }
        // traverse left sub tree with updated remaining sum
        // we don't need to check if left sub tree is nullptr or not
        // as we handle it in the first line of this function
        dfs(node.left, path, remainingSum - node.value);
        // traverse right sub tree with updated remaining sum
        // we don't need to check if right sub tree is nullptr or not
        // as we handle it in the first line of this function
        dfs(node.right, path, remainingSum - node.value);
        // backtrack
        path.remove(path.size() - 1);
    }

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> path = new ArrayList<>();
        dfs(root, path, targetSum);
        return ans;
    }

    public static void main(String[] args) {
        CaculatePathOfSumInTree.pathSum(CaculatePathOfSumInTree.buildATree(), 22);
        for(List<Integer> p : ans) {
          for (Integer i : p) {
            System.out.print(i +",");
          }
          System.out.println();
        }
    }
}
