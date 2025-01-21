package Array;

/**
 *
 * Given an integer array nums, find the
 * subarray
 *  with the largest sum, and return its sum and the starting and end indexes.
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6, [3,6]
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1, [0,0]
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23, [0, 4]
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 */
public class MaxSubArrayWithIndex {
    // dp O(1)
    public static int maxSubArray(int[] nums, int[] window) {
        // dp[n] represent maxium sum of subarray 0...n
        // we are looking for max (dp0,...dpN)
        if (nums == null) {
            return 0;
        }
        int count = nums.length;
        int pre = nums[0];
        int max = nums[0];
        for (int i = 1; i < count; i++) {
            if (pre + nums[i] > nums[i]) {
                pre = pre + nums[i];
            } else {
                pre = nums[i];
                window[0] = i;
            }
            if (pre > max) {
                max = pre;
                window[1] = i;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] a = {5,4,-1,7,8};
        int[] b = {-2,1,-3,4,-1,2,1,-5,4};
        int[] c = {1};
        int[] window = {0,0};
        System.out.println(Array.MaxSubArrayWithIndex.maxSubArray(a, window));
        System.out.println("start index:" + window[0] + ", end index:" + window[1]);
        window[0] = 0;
        window[1] = 0;
        System.out.println(Array.MaxSubArrayWithIndex.maxSubArray(b, window));
        System.out.println("start index:" + window[0] + ", end index:" + window[1]);
        window[0] = 0;
        window[1] = 0;
        System.out.println(Array.MaxSubArrayWithIndex.maxSubArray(c, window));
        System.out.println("start index:" + window[0] + ", end index:" + window[1]);
    }
}

