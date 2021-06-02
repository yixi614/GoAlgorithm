/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package LinkedList;

import java.util.HashMap;

public class DeepCopyNodeWithRandomPointer {
  // Definition for a Node.
  class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }
  public Node copyRandomList(Node head) {
    HashMap<Node, Node> copyMap = new HashMap<>();
    // copy val
    Node cur = head;
    while (cur != null) {
      Node copy = new Node(cur.val);
      copyMap.put(cur, copy);
      cur = cur.next;
    }
    // copy next and random
    cur = head;
    while (cur != null) {
      Node copy = copyMap.get(cur);
      copy.next = copyMap.get(cur.next);
      copy.random = copyMap.get(cur.random);
      cur = cur.next;
    }
    return copyMap.getOrDefault(head, null);
  }
}
