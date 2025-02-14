package Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * tree node has a pointer to the right sibling
 * use queue to do BFT but use a null when dump the queue to indicate end of level
 */
public class ConnectNodesAtSameLevel {
    // Node class
    static class Node {
        int data;
        Node left, right, nextRight;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
            nextRight = null;
        }
    }
    // Sets nextRight of all nodes of a tree
    static void connect(Node root) {
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        // null marker to represent end of current level
        q.add(null);
        // Do Level order of tree using NULL markers
        while (!q.isEmpty()) {
            Node p = q.poll();
            if (p != null) {
                // next element in queue represents next
                // node at current Level
                p.nextRight = q.peek();
                // push left and right children of current
                // node
                if (p.left != null)
                    q.add(p.left);
                if (p.right != null)
                    q.add(p.right);
            }
            // find the previous level end mark
            if (p == null && !q.isEmpty()) {
                q.add(null);
            }
        }
    }
}
