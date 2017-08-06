/**
 *
 * 1, -2, 3, 10, -4, 7, 2, -5
 * It's maximum subarray is 3, 10, -4, 7, 2
 * The method should return 18
 */
public class MaxSubArray {

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
    //System.out.println(MaxSubArray.maxSubArray(a));
    System.out.println(MaxSubArray.maxSubArray(b));
  }
}
