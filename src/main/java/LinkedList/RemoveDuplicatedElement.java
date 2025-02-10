package LinkedList;

import Common.LinkedNode;

/**
 * Given the head of a sorted linked list, delete all duplicates such that each element appears only once. Return the linked list sorted as well
 *
 * */
public class RemoveDuplicatedElement {
    public LinkedNode deleteDuplicates(LinkedNode head) {
        LinkedNode current = head;
        while (current != null && current.next != null) {
            if (current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}
