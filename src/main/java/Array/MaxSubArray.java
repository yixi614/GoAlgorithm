package Array;

/**
 *
 * Given an integer array nums, find the
 * subarray
 *  with the largest sum, and return its sum.
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: The subarray [4,-1,2,1] has the largest sum 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Explanation: The subarray [1] has the largest sum 1.
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 * Explanation: The subarray [5,4,-1,7,8] has the largest sum 23.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 * 1, -2, 3, 10, -4, 7, 2, -5
 * It's maximum subarray is 3, 10, -4, 7, 2
 * The method should return 18
 */

/**
 * see explanation here
 * https://medium.com/@rsinghal757/kadanes-algorithm-dynamic-programming-how-and-why-does-it-work-3fd8849ed73d
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 *
 * From the figure above, we see that the local_maximum[4] is equal to 3 which is the sum of the subarray [4, -1].
 * Now have a look at the subarrays ending with A[5]. You’ll notice that these subarrays can be divided into two parts,
 * the subarrays ending with A[4] (highlighted with yellow) and the single element subarray A[5] (in green).
 *
 * Let’s say somehow I know the local_maximum[4]. Then we see that to calculate the local_maximum[5],
 * we don’t need to compute the sum of all subarrays ending with A[5] since we already know the result from arrays ending with A[4].
 * Note that if array [4, -1] had the maximum sum, then we only need to check the arrays highlighted with the red arrows to calculate local_maximum[5].
 * And this leads us to the principle on which Kadane’s Algorithm works.
 * */
public class MaxSubArray {

  // DP solution,O(n)
  public static int maxSubArray2(int[] nums) {
     //dp[n] represent maxium sum of subarray 0...n
     //we are looking for max (dp0,...dpN)
     if (nums == null) {
         return 0;
     }
     int count = nums.length;
     int[] dp = new int[count];
     dp[0] = nums[0];
     int max = nums[0];
     for (int i = 1; i < count; i++) {
         dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
         max = Math.max(dp[i], max);
     }
     return max;
  }

  // dp O(1)
  public static int maxSubArray(int[] nums) {
    // dp[n] represent maxium sum of subarray 0...n
    // we are looking for max (dp0,...dpN)
    if (nums == null) {
      return 0;
    }
    int count = nums.length;
    int pre = nums[0];
    int max = nums[0];
    for (int i = 1; i < count; i++) {
      pre = Math.max(pre + nums[i], nums[i]);
      max = Math.max(pre, max);
    }
    return max;
  }

  public static void main(String[] args) {
    //int[] a = {1, -2, 3, 10, -4, 7, 2, -5};
    //int[] b = {12,-1,-3,13,-21,3};
    int[] b = {-4,-2,-3,-1};
    //System.out.println(Array.MaxSubArray.maxSubArray(a));
    System.out.println(MaxSubArray.maxSubArray(b));
  }
}


/**
 *
 *   public class Status {
 *     public int lSum, rSum, mSum, iSum;
 *
 *     public Status(int lSum, int rSum, int mSum, int iSum) {
 *       this.lSum = lSum;
 *       this.rSum = rSum;
 *       this.mSum = mSum;
 *       this.iSum = iSum;
 *     }
 *   }
 *
 *   // Segment Tree
 *   public int maxSubArray1(int[] nums) {
 *     return getInfo(nums, 0, nums.length - 1).mSum;
 *   }
 *
 *   public Status getInfo(int[] a, int l, int r) {
 *     if (l == r) {
 *       return new Status(a[l], a[l], a[l], a[l]);
 *     }
 *     int m = (l + r) >> 1;
 *     Status lSub = getInfo(a, l, m);
 *     Status rSub = getInfo(a, m + 1, r);
 *     return pushUp(lSub, rSub);
 *   }
 *
 *   public Status pushUp(Status l, Status r) {
 *     int iSum = l.iSum + r.iSum;
 *     int lSum = Math.max(l.lSum, l.iSum + r.lSum);
 *     int rSum = Math.max(r.rSum, r.iSum + l.rSum);
 *     int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
 *     return new Status(lSum, rSum, mSum, iSum);
 *   }
 * */
