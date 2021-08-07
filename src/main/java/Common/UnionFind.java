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

public class UnionFind {
  // parents[i] is element i's parent's index
  int[] parents;
  // weights[i] is the weights(num of nodes) of tree i which is root
  int[] weights;
  int count;

  public UnionFind(int size) {
    parents = new int[size];
    weights = new int[size];
    for (int i = 0; i < size; i++) {
      parents[i] = i;
      weights[i] = 1;
    }
    count = size;
  }

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

  private int findRoot(int i) {
    while (parents[i] != i) {
      parents[i] = parents[parents[i]];
      i = parents[i];
    }
    return i;
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
