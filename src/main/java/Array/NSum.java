package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NSum {

  // n represent the NSum's N
  public static List<List<Integer>> NSum(int[] nums, int n,
                                         int start, int end,
                                         int target) {
    List<List<Integer>> res = new ArrayList<>();
    // At least two sum. The num of elements should exceed n
    if (n < 2 || nums.length < n) {
      return res;
    }
    if (n == 2) {
      res = twoSum(nums, start, end, target);
    } else {
      for (int i = start; i <= end; i++) {
        if (nums[i] > target) return res;
        int e = nums[i];
        List<List<Integer>> sub = NSum(nums, n - 1, start + 1, end, target - e);
        for (List<Integer> c : sub) {
          // (N - 1)Sum add the num[i] element is NSum
          c.add(e);
          res.add(c);
        }
        while (i < end && nums[i] == nums[i + 1]) {
          i++;
        }
      }
    }
    return res;
  }

  public static List<List<Integer>> twoSum(int[] nums, int start, int end, int target) {
    List<List<Integer>> res = new ArrayList<>();
    int low = start;
    int high = end;
    while (low < high) {
      int sum = nums[low] + nums[high];
      int left = nums[low];
      int right = nums[high];
      if (sum == target) {
        List<Integer> newCollection = new ArrayList<>();
        newCollection.add(nums[low]);
        newCollection.add(nums[high]);
        res.add(newCollection);
        while (low < high && nums[low] == left) {
          low++;
        }
        while (low < high && nums[high] == right) {
          high--;
        }
      } else if (sum < target) {
        low++;
      } else {
        high--;
      }
    }
    return res;
  }

  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> lists = new ArrayList<>();
    //排序
    Arrays.sort(nums);
    //双指针
    int len = nums.length;
    for (int i = 0; i < len; ++i) {
      if (nums[i] > 0) return lists;
      while (i < len && nums[i] == nums[i + 1]) {i++;}
      int curr = nums[i];
      int L = i, R = len - 1;
      while (L < R) {
        int tmp = curr + nums[L] + nums[R];
        if (tmp == 0) {
          List<Integer> list = new ArrayList<>();
          list.add(curr);
          list.add(nums[L]);
          list.add(nums[R]);
          lists.add(list);
          while (L < R && nums[L + 1] == nums[L]) ++L;
          while (L < R && nums[R - 1] == nums[R]) --R;
          ++L;
          --R;
        } else if (tmp < 0) {
          ++L;
        } else {
          --R;
        }
      }
    }
    return lists;
  }

  public static void main(String[] args) {
    int[] nums = {1,1,2,2,3,4,-3,-2,-2,-3,-1,-1};
    //int[] nums = {-1, 0, 1, 2, -1, -4};
    Arrays.sort(nums);
    List<List<Integer>> res = NSum.NSum(nums, 3, 0, nums.length - 1, 0);
    //List<List<Integer>> res = threeSum(nums);
    for (List<Integer> c : res) {
      System.out.print("[");
      for (Integer e : c) {
        System.out.print(e + ",");
      }
      System.out.print("]");
      System.out.println();
    }
  }
}
