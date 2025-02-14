package Tree;

import Common.CommonBuilder;
import Common.TreeNode;

import java.util.Arrays;
import java.util.List;

/**
 * 426. Convert Binary Search Tree to Sorted Doubly Linked List
 * Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
 *
 * Let's take the following BST as an example, it may help you understand the problem better:
 *
 *                 4
 *         2              5
 *   1          3
 *
 *
 * We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
 *
 * The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest element of the linked list.
 *
 *   node
 *      |
 *      1 <=>  2 <=> 3 <=> 4 <=> 5
 *
 *
 * Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.
 *
 * The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.
 *
 * Created by ztang16 on 1/12/2017.
 */
public class BSTToLinkedList {

  static TreeNode pre;
  static TreeNode head;
  public static void transform2(TreeNode root) {
    dfs(root);
    head.left = pre;
    pre.right = head;
  }
  // in-order traverse
  public static void dfs(TreeNode root) {
    if (root == null) {
      return;
    }
    dfs(root.left);
    // this only happens once, BST left most is the smallest node which is the head of the list
    if (head == null) {
      head = root;
    }
    // pre always points to the last smaller value of current root node
    // so link pre and current root
    if (pre != null) {
      pre.right = root;
      root.left = pre;
    }
    // then pre move to the next which is the root node then in the next recursive call (dfs(root.right)), it will continue to do the linking
    pre = root;
    dfs(root.right);
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
    // List<Integer> nums = Arrays.asList(1,2,3,4,5);
    TreeNode root = CommonBuilder.buildBSTTree(nums);
    System.out.println(root.inOrderTraverse());
    BSTToLinkedList.transform2(root);
    System.out.println(printAsLinkedList(head));
  }
}
