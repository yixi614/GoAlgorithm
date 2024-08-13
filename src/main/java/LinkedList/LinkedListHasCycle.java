package LinkedList;

public class LinkedListHasCycle {
  /*
  public boolean hasCycle(ListNode head) {
    if (head == null) {
      return false;
    }
    ListNode slow = head;
    ListNode fast = head;
    while(slow.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true
      }
    }
    return false;
  }
  * */

/*
    // Function that returns true if there is a loop in
    // linked list else returns false.
    static boolean detectLoop(Node head) {
        Set<Node> set = new HashSet<>();

        // loop that runs till the head is null
        while (head != null) {

            // If this node is already present
            // in hashmap it means there is a cycle
            // (Because you will be encountering the
            // node for the second time).
            if (set.contains(head))
                return true;

            // If we are seeing the node for
            // the first time, insert it in hash
            set.add(head);

            head = head.next;
        }
        return false;
    }
 */

}
