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

package Tree;

import Common.CommonBuilder;
import Common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeBFS {
  public static List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList();
    Queue<TreeNode> q = new LinkedList();
    if (root == null) {
      return res;
    }
    q.offer(root);
    while (!q.isEmpty()) {
      List<Integer> level = new ArrayList<>();
      int levelNodesCount = q.size();
      for (int i = 0; i < levelNodesCount; i++) {
        TreeNode e = q.poll();
        level.add(e.value);
        if (e != null) {
          if (e.left != null) {
            q.offer(e.left);
          }
          if (e.right != null) {
            q.offer(e.right);
          }
        }
      }
      if (level.size() != 0) {
        res.add(level);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.left.right = new TreeNode(5);
    List<List<Integer>> res = BinaryTreeBFS.levelOrder(root);
    for (List<Integer> list : res) {
      for(int e : list) {
        System.out.print(e + ",");
      }
      System.out.println("\n");
    }
  }
}
