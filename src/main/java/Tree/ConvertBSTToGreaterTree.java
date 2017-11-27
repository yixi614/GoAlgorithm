package Tree;

import Common.CommonBuilder;
import Common.TreeNode;

import java.util.Arrays;
import java.util.List;

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
