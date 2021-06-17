package Array;

public class MedianOfTwoSortedArrays {

  public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
    int len1 = nums1.length;
    int len2 = nums2.length;
    int size = len1 + len2;
    int[] res = new int[size];
    int i = 0;
    int j = 0;
    int count = 0;
    while (i < len1 && j < len2) {
      if (nums1[i] <= nums2[j]) {
        res[count] = nums1[i];
        i++;
      } else {
        res[count] = nums2[j];
        j++;
      }
      count++;
    }
    while (i < len1) {
      res[count] = nums1[i];
      count++;
      i++;
    }
    while (j < len2) {
      res[count] = nums2[j];
      count++;
      j++;
    }
    if (size % 2 == 0) {
      return (double) (res[size / 2] + res[size / 2 - 1]) / 2.0;
    } else {
      return (double) res[(size - 1) / 2];
    }
  }

  public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    int left = (n + m + 1) / 2;
    int right = (n + m + 2) / 2;
    // if odd, calculate k twice
    return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) +
        getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
  }

  private static int getKth(int[] nums1, int start1, int end1,
                            int[] nums2, int start2, int end2, int k) {
    int len1 = end1 - start1 + 1;
    int len2 = end2 - start2 + 1;
    // let len1 < len2 to ensure if array is empty, it must be len1
    if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
    if (len1 == 0) return nums2[start2 + k - 1];

    if (k == 1) return Math.min(nums1[start1], nums2[start2]);

    int i = start1 + Math.min(len1, k / 2) - 1;
    int j = start2 + Math.min(len2, k / 2) - 1;

    if (nums1[i] > nums2[j]) {
      return getKth(nums1, start1, end1,
          nums2, j + 1, end2,
          k - (j - start2 + 1));
    } else {
      return getKth(nums1, i + 1, end1,
          nums2, start2, end2,
          k - (i - start1 + 1));
    }
  }

  public static void main(String[] args) {
    int[] num1 = {1, 2};
    int[] num2 = {3, 4};
    System.out.println(MedianOfTwoSortedArrays.findMedianSortedArrays(num1, num2));
  }

}
