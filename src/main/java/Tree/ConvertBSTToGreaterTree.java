package Tree;

import Common.CommonBuilder;
import Common.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the
 * original BST is changed to the original key plus sum of all keys greater than the original key in BST.
 * Example:
 * Input: The root of a Binary Search Tree like this:
   5
 /   \
 2     13

 Output: The root of a Greater Tree like this:
   18
 /   \
 20     13
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
