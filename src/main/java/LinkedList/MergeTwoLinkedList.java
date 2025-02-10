package LinkedList;

import Common.LinkedNode;

/**
 * TODO:
 * Created by ztang16 on 3/21/2017.
 */
public class MergeTwoLinkedList {
    LinkedNode mergeTwoLinkedList(LinkedNode l1, LinkedNode l2) {
        LinkedNode dummy = new LinkedNode(0, null);
        LinkedNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        if (l1 != null) {
            cur.next = l1;
        } else if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }
}
