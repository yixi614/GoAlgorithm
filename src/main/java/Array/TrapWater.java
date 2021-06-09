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

package Array;

public class TrapWater {
  // for a single elevation i, the water it can trap is
  // min(leftMaxHeight (0..i), rightMaxHeight(i..n)) - height(i)
  // public int trap(int[] height) {
  //     if (height == null || height.length < 3) {
  //         return 0;
  //     }
  //     int res = 0;
  //     int n = height.length;
  //     for (int i = 1; i < n - 1; i++) {
  //         int leftMaxHeight = 0;
  //         int rightMaxHeight = 0;
  //         for (int j = i; j >= 0; j--) {
  //             leftMaxHeight = max(leftMaxHeight, height[j]);
  //         }
  //         for (int j = i; j < n; j++) {
  //             rightMaxHeight = max(rightMaxHeight, height[j]);
  //         }
  //         res += (min(leftMaxHeight, rightMaxHeight) - height[i]);
  //     }
  //     return res;
  // }

  public int min(int h1, int h2) {
    return h1 >= h2 ? h2 : h1;
  }

  public int max(int h1, int h2) {
    return h1 >= h2 ? h1 : h2;
  }

  // double pointer
  public int trap(int[] height) {
    if (height.length == 0) {
      return 0;
    }
    int size = height.length;
    int left = 0;
    int right = size - 1;
    int res = 0;
    int leftMaxHeight = height[0];
    int rightMaxHeight = height[size - 1];
    while (left <= right) {
      leftMaxHeight = max(leftMaxHeight, height[left]);
      rightMaxHeight = max(rightMaxHeight, height[right]);
      if (leftMaxHeight < rightMaxHeight) {
        res += leftMaxHeight - height[left];
        left++;
      } else {
        res += rightMaxHeight - height[right];
        right--;
      }
    }
    return res;
  }
}
