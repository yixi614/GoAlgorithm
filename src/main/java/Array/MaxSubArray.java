package Array;

/**
 *
 * 1, -2, 3, 10, -4, 7, 2, -5
 * It's maximum subarray is 3, 10, -4, 7, 2
 * The method should return 18
 */
public class MaxSubArray {

  /*
  // DP solution
  public int maxSubArray(int[] elements) {
    int n = elements.length;
    int[] dp = new int[n]; // dp[i] means the maximum subarray ending with A[i]
    dp[0] = elements[0];
    for (int i = 0; i < n; i++) {
      dp[i] = A[i] + (dp[i-1] > 0 ? dp[i-1]:0); // if dp[i-1] <=0, we just exclude it(because it will make sum even smaller) and start a new accumulation from 0
      max = Math.max(max, dp[i]);
    }
    return max;
  }
  */

  public static int maxSubArray(int[] elements) {
    int sum = 0;
    int max = sum;
    for (int i = 0; i < elements.length; i++) {
      sum += elements[i];
      if (sum < 0) {
        sum = 0;
      } else if (max < sum) {
          max = sum;
      }
    }
    return max;
  }

  public static void main(String[] args) {
    //int[] a = {1, -2, 3, 10, -4, 7, 2, -5};
    int[] b = {12,-1,-3,13,-21,3};
    //System.out.println(Array.MaxSubArray.maxSubArray(a));
    System.out.println(MaxSubArray.maxSubArray(b));
  }
}
