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

  // Delete this node from a list
  public void delete() {
    if(this != null && this.next != null) {
      this.val = this.next.val;
      this.next = this.next.next;
    }
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

  public static void main(String[] args) {
    int[] a = {1,2,3,4,5,6};
    LinkedNode head = CommonBuilder.buildLindedList(a);
    LinkedNode two = head.next;
    two.delete();
    System.out.println(head.printList(head));
  }

}
