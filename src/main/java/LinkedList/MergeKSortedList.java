package LinkedList;

import Common.LinkedNode;

import java.util.PriorityQueue;

public class MergeKSortedList {
    public LinkedNode mergeKLists(LinkedNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<LinkedNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (LinkedNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        LinkedNode dummy = new LinkedNode(0, null);
        LinkedNode tail = dummy;
        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;
            if (tail.next != null) {
                queue.add(tail.next);
            }
        }
        return dummy.next;
    }
}
