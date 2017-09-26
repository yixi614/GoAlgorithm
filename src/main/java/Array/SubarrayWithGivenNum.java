package Array;

/**
 * Given an unsorted array of non-negative integers,
 * find a continuous sub-array which adds to a given number.
 * Expected: O(n)
 * */
public class SubarrayWithGivenNum {
  public static int[] getSubarray(int[] a, int sum) {
    int[] r = new int[2];
    if (a.length == 0) {
      return null;
    }
    if (a.length == 1 && a[0] != sum) {
      return null;
    }
    int start = 0, i, current_sum = a[0];
    for (i = 1; i < a.length + 1; i++) {
      if (current_sum > sum) {
        while (current_sum > sum && start < i) {
          current_sum -= a[start];
          start++;
        }
      }
      if (current_sum == sum) {
        r[0] = start;
        r[1] = i-1;
        System.out.println(r[0] + "," + r[1]);
        return r;
      }
      if (i < a.length) {
        current_sum += a[i];
      }
    }
    return null;
  }

  public static void main(String[] args) {
    int[] a = {1, 4, 20, 3, 10, 5};
    SubarrayWithGivenNum.getSubarray(a,33);
    int[] b = {1, 4, 0, 0, 3, 10, 5};
    SubarrayWithGivenNum.getSubarray(b,7);
    int[] c = {15, 2, 5, 8, 9, 5, 10, 23};
    SubarrayWithGivenNum.getSubarray(c,23);
    int[] d = {15, 23, 2};
    SubarrayWithGivenNum.getSubarray(d,23);
  }
}
