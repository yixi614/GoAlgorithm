package Array;

import java.util.HashMap;

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

  public static int[] getSubarray2(int[] arr, int target) {
    int[] res = {-1,-1};
    int sum = 0;
    // key is the prefixSum, value is the right end index
    HashMap<Integer, Integer> prefixSum = new HashMap<>();
    prefixSum.put(0, -1);
    // populate prefixSum
    // while populating, find the prefix[j] - prefix[i] == target
    for (int i = 0; i < arr.length; i++) {
      sum = sum + arr[i];
      if (prefixSum.containsKey(sum - target)) {
        int start = prefixSum.get(sum - target);
        res[0] = start + 1;
        res[1] = i;
        System.out.println(res[0] + "," + res[1]);
        return res;
      }
      prefixSum.put(sum, i);
    }
    return res;
  }

  public static void main(String[] args) {
    int[] a = {1, 4, 20, 3, 10, 5};
    SubarrayWithGivenNum.getSubarray(a,33);
    SubarrayWithGivenNum.getSubarray2(a,33);
    int[] b = {1, 4, 0, 0, 3, 10, 5};
    SubarrayWithGivenNum.getSubarray(b,7);
    SubarrayWithGivenNum.getSubarray2(b,7);
    int[] c = {15, 2, 5, 8, 9, 5, 10, 23};
    SubarrayWithGivenNum.getSubarray(c,23);
    SubarrayWithGivenNum.getSubarray2(c,23);
    int[] d = {15, 23, 2};
    SubarrayWithGivenNum.getSubarray(d,23);
    SubarrayWithGivenNum.getSubarray2(d,23);
  }
}
