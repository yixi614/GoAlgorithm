package common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ztang16 on 1/12/2017.
 */
public class CommonBuilder {

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

  private static TreeNode constructBSTTree(int start, int end, Integer[] a) {
    if (start == end) {
      return new TreeNode(a[start]);
    }
    if (start > end) {
      return null;
    }
    int midIndex = start + (end-start)/2;
    TreeNode root = new TreeNode(a[midIndex]);
    TreeNode left = constructBSTTree(start, midIndex -1, a);
    TreeNode right = constructBSTTree(midIndex + 1, end, a);
    root.left = left;
    root.right = right;
    return root;
  }

  public static void main(String[] args) {
    List<Integer> nums = Arrays.asList(4,6,8,10,12,14,16,18,19,20);
    TreeNode root = buildBSTTree(nums);
    System.out.println(root.printD());
    System.out.println(root.printB());
  }


}
