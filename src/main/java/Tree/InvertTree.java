package Tree;

public class InvertTree {
  /*
  // DFS
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    TreeNode temp = root.left;
    root.left = invertTree(root.right);
    root.right = invertTree(temp);
    return root;
  }
  // BFS
  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queque.offer(root);
    while (!queue.isEmpty()) {
      TreeNode node = queue.poll();
      TreeNode left = node.left;
      node.left = node.right;
      node.right = left;
      if (node.left != null) {
        queue.offer(node.left);
      }
      if (node.right != null) {
        queue.offer(node.right);
      }
    }
    return root;
  }
  * */
}
