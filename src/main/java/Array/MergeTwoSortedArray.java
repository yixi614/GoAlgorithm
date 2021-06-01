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

public class MergeTwoSortedArray {
  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    int p1 = m - 1;
    int p2 = n - 1;
    int index = m + n - 1;
    int chosenValue = 0;
    while (p1 != -1 || p2 != -1) {
      if (p1 == -1) {
        chosenValue = nums2[p2--];
      } else if (p2 == -1) {
        chosenValue = nums1[p1--];
      } else {
        if (nums1[p1] > nums2[p2]) {
          chosenValue = nums1[p1--];
        } else {
          chosenValue = nums2[p2--];
        }
      }
      if (index == -1) {
        return;
      }
      nums1[index--] = chosenValue;
    }
  }
  public static void main(String[] args) {
    int[] a = {1,2,3,4,5,0,0,0};
    int[] b = {4,5,6};
    //System.out.println(Array.MaxSubArray.maxSubArray(a));
    MergeTwoSortedArray.merge(a, 5, b, 3);
    for (int v : a) {
      System.out.println(v);
    }
  }
}
