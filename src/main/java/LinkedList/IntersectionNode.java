package LinkedList;

public class IntersectionNode {
  /*
  // If the distance of head ~ intersection point is the same, then a and b will reach same point in the first iteration
  // if the distance of head ~ intersection point is different, then one of a or b will first reach tail, and
  // at this time we rest it to the other "longer" list's head. Then when the slower pointer(let's say b) reach
  // B's tail. Then a has moved the distance diff value in B. So at this time we set b to A's head. Then the
  // two pointer will meet at the intersection point
  public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    if (headA == null || headB == null) {
      return null;
    }
    ListNode a = headA;
    ListNode b = headB;
    while (a != b) {
      a = a == null ? headB : a.next;
      b = b == null ? headA : b.next;
    }
    return a;
  }
  */
}
