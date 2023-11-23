package Array;

/*
* Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b


Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]


Constraints:

1 <= k <= arr.length
1 <= arr.length <= 104
arr is sorted in ascending order.
-104 <= arr[i], x <= 104
*
* */

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {

    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> re = new ArrayList<>();
        int left = 0;
        int right = arr.length - k;
        while (left < right) {
            int mid = left + (right - left)/2;
            int midDis = x - arr[mid];
            int midKDis = arr[mid + k] - x;
            if (midDis > midKDis) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        for (int i = left; i < left + k; i++) {
            re.add(arr[i]);
        }
        return re;
    }

    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5};
//        int k = 4, x = 3;
        int[] arr = {1,1,2,2,2,2,2,3,3};
        int k = 3, x = 3;
        List<Integer> re = findClosestElements(arr, k, x);
        for (Integer i : re) {
            System.out.print(i + ",");
        }
    }
}
