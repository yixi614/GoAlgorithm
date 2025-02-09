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

package Common;

// union find is to represent the graph's interconnection
// it uses array "parents" to represent all node' parent position so that we know their relationship.
// each node can be part of a tree (multi-way tree) or an independent tree. So we can regard unionfind as "forest".
// it is suitable for check if two nodes are connected.
// "weights“ array is to represent how many nodes of a tree. Used to ensure small treeA becomes child of big treeB so that the new tree(A combine B) is more balanced
public class UnionFind {
  // parents[i] is the tree node i's parent's index
  // parents represent a forest which contains single node trees and multi-way trees
  int[] parents;
  // weights[i] is the weights(num of nodes) of tree i which is root
  int[] weights;
  // count is the number of trees in a forest
  int count;

  // input is the number of trees
  public UnionFind(int size) {
    parents = new int[size];
    weights = new int[size];
    for (int i = 0; i < size; i++) {
      // tree node point to itself, means every tree is a single root node
      parents[i] = i;
      // every tree size is 1
      weights[i] = 1;
    }
    count = size;
  }

  // connect two tree
  public void union(int i, int j) {
    int parentP = findRoot(i);
    int parentQ = findRoot(j);
    if (parentP == parentQ) {
      return;
    }
    if (weights[parentP] > weights[parentQ]) {
      parents[parentQ] = parentP;
      weights[parentP] += weights[parentQ];
    } else {
      parents[parentP] = parentQ;
      weights[parentQ] += weights[parentP];
    }
    count--;
  }

  // find node x's root
  private int findRoot(int x) {
    // if node x is not his root, look up the "parents" array to find x's root node
    while (parents[x] != x) {
      // operation to modify tree structure to make it more balance
      parents[x] = parents[parents[x]];
      // next round we'll find x's parent's parent
      x = parents[x];
    }
    return x;
  }

  public int count() {
    return count;
  }
  public void setCount(int c) {
    this.count = c;
  }

  public boolean connected(int i, int j) {
    return findRoot(i) == findRoot(j);
  }
}
