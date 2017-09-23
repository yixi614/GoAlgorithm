package Tree;

import Common.CommonBuilder;
import Common.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ztang16 on 1/12/2017.
 */
public class BSTToLinkedList {

  public static void join(TreeNode a, TreeNode b) {
    a.right = b;
    b.left = a;
  }

  //helper function -- given two circular doubly linked lists,
  // append them and return the new list
  public static TreeNode append(TreeNode a, TreeNode b) {
    if (a == null) {return b;}
    if (b == null) {return a;}
    TreeNode aLast = a.left;
    TreeNode bLast = b.left;
    //join the two together to make it connected and circular
    join(aLast, b);
    join(bLast, a);
    return a;
  }
  public static TreeNode transform(TreeNode root) {
    if (root == null) { return null;}
    //Recursively do the subtree (leap of faith!)
    TreeNode leftList = transform(root.left);
    TreeNode rightList = transform(root.right);
    //make the single root node into a list length-1
    //in preparation for the appending
    root.left = root;
    root.right = root;
    //At this point we have three lists, and it's
    //just a matter of appending them together
    // in the right order (leftList, root, rightList)
    leftList = append(leftList, root);
    leftList = append(leftList,rightList);
    return leftList;
  }

  public static String printAsLinkedList(TreeNode head) {
    StringBuilder sb = new StringBuilder("DoubleLinkedList\n");
    sb.append("From left to right:\n");
    TreeNode p = head;
    while (p != null) {
      sb.append(p.value + ",");
      p = p.right;
      if (p == head) {
        break;
      }
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    List<Integer> nums = Arrays.asList(4,6,8,10,12,14,16,18,19,20);
    TreeNode root = CommonBuilder.buildBSTTree(nums);
    System.out.println(root.midTraverse());
    TreeNode head = BSTToLinkedList.transform(root);
    System.out.println(printAsLinkedList(head));
  }
}
