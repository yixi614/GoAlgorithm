import common.TreeNode;

import java.util.Vector;

/**
 * Input 22 with below tree
 *   10
 *  /  \
 *  5  12
 * / \
 * 4 7
 *
 * output 10,5,7 and 10,12
 */
public class CaculatePathOfSumInTree {
  public static class Indicator {
    private int current;
    public Indicator(int val) {
      this.current = val;
    }
    public void increaseVal(int val) {
      this.current += val;
    }
    public void decreaseVal(int val) {
      this.current -= val;
    }
    public int getVal() {
      return this.current;
    }
  }
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

  public static void run(int sum, TreeNode root) {
    Vector<TreeNode> stack = new Vector<TreeNode>();
    Indicator current = new Indicator(0);
    depthFirstTraver(root, stack, sum, current);
  }
  public static void depthFirstTraver(TreeNode root, Vector<TreeNode> stack, int sum, Indicator current) {
    if (root == null) {
      return;
    }
    stack.add(root);
    current.increaseVal(root.value);
    if (current.getVal() == sum) {
      String s = "";
      for (int i = 0; i < stack.size(); i++) {
        s += stack.get(i).value + ",";
      }
      System.out.println(s);
    }
    if (root.left != null) {
      depthFirstTraver(root.left, stack, sum, current);
      stack.remove(stack.size()-1);
      current.decreaseVal(root.left.value);
    }
    if (root.right != null) {
      depthFirstTraver(root.right, stack, sum, current);
      stack.remove(stack.size()-1);
      current.decreaseVal(root.right.value);
    }
  }

  public static void main(String[] args) {
    CaculatePathOfSumInTree.run(22, CaculatePathOfSumInTree.buildATree());
  }
}
