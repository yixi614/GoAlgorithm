package Common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ztang16 on 1/12/2017.
 */
public class CommonBuilder {

  // SortedArrayToBST
  public static TreeNode buildBSTTree(List<Integer> a) {
    Collections.sort(a);
    TreeNode root = constructBSTTree(0,a.size() - 1,a.toArray(new Integer[a.size()]));
    return root;
  }

  public static LinkedNode buildLindedList(int[] a) {
    LinkedNode head = new LinkedNode(a[0], null);
    LinkedNode p = head;
    for (int i = 1; i < a.length; i++) {
      p.next = new LinkedNode(a[i],null);
      p = p.next;
    }
    return head;
  }

  public static TreeNode constructBSTTree(int start, int end, Integer[] a) {
    if (start > end) {
      return null;
    }
    int midIndex = (end + start) / 2;
    TreeNode root = new TreeNode(a[midIndex]);
    TreeNode left = constructBSTTree(start, midIndex -1, a);
    TreeNode right = constructBSTTree(midIndex + 1, end, a);
    root.left = left;
    root.right = right;
    return root;
  }

  public static void main(String[] args) {
    List<Integer> nums = Arrays.asList(4,6,8,10);
    TreeNode root = buildBSTTree(nums);
    System.out.println(root.printB());
    prettyPrint(root.printB());
  }

  public static void prettyPrint(String breadthTreeStr) {
    String[] nodes = breadthTreeStr.split(",");
    int levelCount = 1;
    int count = 0;
    int level = (int)(Math.log(nodes.length)/Math.log(2.0)) + 1;
    while (count < nodes.length) {
      if(count == levelCount - 1) {
        System.out.print("\n");
        level--;
        levelCount = levelCount << 1;
      }
      int i = level;
      while (i >= 0) {
        System.out.print("\t");
        i--;
      }
      System.out.print(nodes[count++]);
    }
  }

}
