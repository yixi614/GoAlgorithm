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


/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 * Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output: 6
 * Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
 *
 * */
public class TrapWater {

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
    int area = 0;
    int leftMaxHeight = height[0];
    int rightMaxHeight = height[size - 1];
    while (left <= right) {
      leftMaxHeight = max(leftMaxHeight, height[left]);
      rightMaxHeight = max(rightMaxHeight, height[right]);
      if (leftMaxHeight < rightMaxHeight) {
        area += leftMaxHeight - height[left];
        left++;
      } else {
        area += rightMaxHeight - height[right];
        right--;
      }
    }
    return area;
  }
}
