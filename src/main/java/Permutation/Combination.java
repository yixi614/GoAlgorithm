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

package Permutation;

import java.util.ArrayList;
import java.util.List;

public class Combination {
/**
 * Given a number n and k. Return all possible combination of k numbers
 * out of 1...n
 * For example,n = 4, k = 2, a solution is
 *[
 * [1,2],
 * [1,3],
 * [1,4],
 * [2,3],
 * [2,4]
 * [3,4]
 * ]
 * */
  public static void main(String[] args) {
    List<List<Integer>> res = combinations(4, 3);
    int a = 1;
    return;
  }
  public static List<List<Integer>> combinations(int n, int k) {
    List<List<Integer>> res = new ArrayList<>();
    helper(res, new ArrayList<>(), n, k, 1);
    return res;
  }

  public static void helper(List<List<Integer>> res, List<Integer> list,
      int n, int k, int start) {
    if (k == 0) {
      res.add(new ArrayList<>(list));
      return;
    }
    for (int i = start; i <= n; i++) {
      list.add(i);
      helper(res, list, n, k - 1, i + 1);
      list.remove(list.size() - 1);
    }
  }
}
