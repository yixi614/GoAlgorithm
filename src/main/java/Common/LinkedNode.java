package Common;

/**
 * Created by ztang16 on 3/19/2017.
 */
public class LinkedNode {
  public int val;
  public LinkedNode next;
  public LinkedNode(int val, LinkedNode next) {
    this.val = val;
    this.next = next;
  }

  public static String printList(LinkedNode head) {
    StringBuilder s = new StringBuilder();
    LinkedNode p = head;
    while (p != null) {
      s.append(p.val + ",");
      p = p.next;
    }
    return s.toString();
  }

}
