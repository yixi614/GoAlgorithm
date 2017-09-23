package LinkedList;


import Common.CommonBuilder;
import Common.LinkedNode;

/**
 * reverse a linked list
 */
public class ReverseLinkedList {

  public static LinkedNode runRecursive(LinkedNode node) {
    if (node == null || node.next == null) {
      return node;
    }
    LinkedNode remaining = runRecursive(node.next);
    node.next.next = node;
    node.next = null;
    return remaining;
  }

  public static LinkedNode run(LinkedNode current) {
    if (current == null) {
      return null;
    }
    LinkedNode pre = null;
    LinkedNode next = null;
    while (current != null) {
      next = current.next;
      current.next = pre;
      pre = current;
      current = next;
    }
    return pre;
  }
  public static void main(String[] args) {
    int[] a = {1,2,3,4,5,6};
    LinkedNode head = CommonBuilder.buildLindedList(a);
    System.out.println(head.printList(head));
    LinkedNode newHead = ReverseLinkedList.run(head);
    //LinkedNode newHead = LinkedList.ReverseLinkedList.runRecursive(head);
    System.out.println(head.printList(newHead));
  }
}
