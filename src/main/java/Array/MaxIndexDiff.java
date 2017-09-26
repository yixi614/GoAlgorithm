package Array;

/**
 * Given an array arr[], find the maximum j – i such that arr[j] > arr[i]
 *
 * To solve this problem, we need to get two optimum indexes of arr[]:
 * left index i and right index j. For an element arr[i], we do not need to
 * consider arr[i] for left index if there is an element smaller than arr[i] on left side of arr[i].
 * Similarly, if there is a greater element on right side of arr[j] then we do not need to consider this j
 * for right index. So we construct two auxiliary arrays LMin[] and RMax[] such that LMin[i] holds the
 * smallest element on left side of arr[i] including arr[i],
 * and RMax[j] holds the greatest element on right side of arr[j] including arr[j].
 * After constructing these two auxiliary arrays, we traverse both of these arrays from left to right.
 * While traversing LMin[] and RMax[] if we see that LMin[i] is greater than RMax[j],
 * then we must move ahead in LMin[] (or do i++) because all elements on left of LMin[i]
 * are greater than or equal to LMin[i].
 * Otherwise we must move ahead in RMax[j] to look for a greater j – i value.
 *
 * Input: {34, 8, 10, 3, 2, 80, 30, 33, 1}
 * Output: 6  (j = 7, i = 1)
 *
 * Input: {9, 2, 3, 4, 5, 6, 7, 8, 18, 0}
 * Output: 8 ( j = 8, i = 0)
 *
 * Input:  {1, 2, 3, 4, 5, 6}
 * Output: 5  (j = 5, i = 0)
 *
 * Input:  {6, 5, 4, 3, 2, 1}
 * Output: -1
 * */
public class MaxIndexDiff {

  public static int getMaxIndexDiff(int[] a) {
    int maxIndexDiff = -1;
    int[] LMin = new int[a.length];
    int[] RMax = new int[a.length];
    //LMin[i] holds the smallest value from a[0] to a[i]
    LMin[0] = a[0];
    int i,j;
    for (i = 1; i < a.length; i++) {
      LMin[i] = Math.min(a[i], LMin[i-1]);
    }
    //RMax[i] holds the maximum value from a[j] to a[n-1]
    RMax[a.length - 1] = a[a.length - 1];
    for (j = a.length - 2; j>=0; j--) {
      RMax[j] = Math.max(a[j], RMax[j+1]);
    }
    //Traverse both arrays from left to right to find optimum j - i
    //This process is similar to merge() of MergeSort
    i = 0;
    j = 0;
    while (j < a.length && i < a.length) {
      if (LMin[i] >= RMax[j]) {
        i++;
      } else if (LMin[i] < RMax[j]) {
        maxIndexDiff = Math.max(maxIndexDiff, j - i);
        j++;
      }
    }
    System.out.println(maxIndexDiff);
    return maxIndexDiff;
  }

  public static void main(String[] args) {
    int[] a = {34, 8, 10, 3, 2, 80, 30, 33, 1};
    int[] b = {9, 2, 3, 4, 5, 6, 7, 8, 18, 0};
    int[] c = {1, 2, 3, 4, 5, 6};
    int[] d = {6, 5, 4, 3, 2, 1};
    MaxIndexDiff.getMaxIndexDiff(a);
    MaxIndexDiff.getMaxIndexDiff(b);
    MaxIndexDiff.getMaxIndexDiff(c);
    MaxIndexDiff.getMaxIndexDiff(d);
  }
}
