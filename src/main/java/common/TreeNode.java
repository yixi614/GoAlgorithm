package common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ztang16 on 1/12/2017.
 */
public class TreeNode {

  public int value;
  public TreeNode left;
  public TreeNode right;
  public TreeNode(int x) {value = x;}

  public String printD() {
    StringBuilder str = new StringBuilder();
    depthFirstSearch(this,str);
    return str.toString();
  }

  public String printB() {
    StringBuilder str = new StringBuilder();
    breadthFirstSearch(this,str);
    return str.toString();
  }

  public String midTraverse() {
    StringBuilder str = new StringBuilder();
    midTraverse(this,str);
    return str.toString();
  }

  @Override
  public String toString() {
    return "TreeNode{" +
            "value=" + value +
            ", left=" + left +
            ", right=" + right +
            '}';
  }

  private void breadthFirstSearch(TreeNode node, StringBuilder str) {
    List<TreeNode> queue = new ArrayList<TreeNode>();
    queue.add(node);
    while (!queue.isEmpty()) {
      TreeNode t = queue.remove(0);
      if (t == null) {
        str.append("null,");
        continue;
      }
      str.append(t.value + ",");
      queue.add(t.left);
      queue.add(t.right);
    }
  }

  private void depthFirstSearch(TreeNode node, StringBuilder str) {
    if (node == null) {
      str.append("null,");
      return;
    }
    str.append(node.value + ",");
    depthFirstSearch(node.left,str);
    depthFirstSearch(node.right,str);
  }

  private void midTraverse(TreeNode node, StringBuilder str) {
    if (node == null) {
      return;
    }
    midTraverse(node.left, str);
    str.append(node.value + ",");
    midTraverse(node.right, str);
  }

}
