package Tree;

import Common.CommonBuilder;
import Common.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST
 * is changed to the original key plus the sum of all keys greater than the original key in BST.
 *
 * As a reminder, a binary search tree is a tree that satisfies these constraints:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *                   4
 *         /                \
 *        1                  6
 *    /     \              /   \
 *  0         2           5     7
 *              \                 \
 *                3                8
 * Input: root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * Output: [30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]

 * Input: root = [0,null,1]
 * Output: [1,null,1]
 * */
public class ConvertBSTToGreaterTree {
  static int sum = 0;
  public static TreeNode convertBST(TreeNode root) {
    sum = 0;
    reInOrderBST(root);
    return root;
  }
  private static void reInOrderBST(TreeNode root) {
    if (null == root) {
      return;
    }
    reInOrderBST(root.right);
    root.value += sum;
    sum = root.value;
    reInOrderBST(root.left);
  }
  public static void main(String[] args) {
    List<Integer> nums = Arrays.asList(2,5,13);
    TreeNode root = CommonBuilder.buildBSTTree(nums);
    ConvertBSTToGreaterTree.convertBST(root);
    System.out.println(root.printB());
  }
}
