package Array;

/**
 *
 * 1, -2, 3, 10, -4, 7, 2, -5
 * It's maximum subarray is 3, 10, -4, 7, 2
 * The method should return 18
 */
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

  public class Status {
    public int lSum, rSum, mSum, iSum;

    public Status(int lSum, int rSum, int mSum, int iSum) {
      this.lSum = lSum;
      this.rSum = rSum;
      this.mSum = mSum;
      this.iSum = iSum;
    }
  }

  // Segment Tree
  public int maxSubArray1(int[] nums) {
    return getInfo(nums, 0, nums.length - 1).mSum;
  }

  public Status getInfo(int[] a, int l, int r) {
    if (l == r) {
      return new Status(a[l], a[l], a[l], a[l]);
    }
    int m = (l + r) >> 1;
    Status lSub = getInfo(a, l, m);
    Status rSub = getInfo(a, m + 1, r);
    return pushUp(lSub, rSub);
  }

  public Status pushUp(Status l, Status r) {
    int iSum = l.iSum + r.iSum;
    int lSum = Math.max(l.lSum, l.iSum + r.lSum);
    int rSum = Math.max(r.rSum, r.iSum + l.rSum);
    int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
    return new Status(lSum, rSum, mSum, iSum);
  }

  public static void main(String[] args) {
    //int[] a = {1, -2, 3, 10, -4, 7, 2, -5};
    int[] b = {12,-1,-3,13,-21,3};
    //System.out.println(Array.MaxSubArray.maxSubArray(a));
    System.out.println(MaxSubArray.maxSubArray(b));
  }
}
