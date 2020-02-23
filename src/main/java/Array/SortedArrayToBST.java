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

import Common.CommonBuilder;
import Common.TreeNode;

import java.util.Arrays;

public class SortedArrayToBST {

    public static TreeNode sortedArrayToBST(int[] nums) {
      return _toBST(0, nums.length - 1, nums);
    }

    public static TreeNode _toBST(int start, int end, int[] nums) {
      if (start > end) {
        return null;
      }
      int mid = (end + start) / 2;
      TreeNode root = new TreeNode(nums[mid]);
      TreeNode left = _toBST(start, mid - 1, nums);
      TreeNode right = _toBST(mid + 1, end, nums);
      root.left = left;
      root.right = right;
      return root;
    }
    public static void main(String[] args) {
      int[] list = {6,4,8,10};
      Arrays.sort(list);
      TreeNode root = SortedArrayToBST.sortedArrayToBST(list);
      CommonBuilder.prettyPrint(root.printB());
    }
}
