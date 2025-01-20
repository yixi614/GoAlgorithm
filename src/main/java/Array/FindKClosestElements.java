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
import java.util.PriorityQueue;

public class FindKClosestElements {

    // two pointers
    public static List<Integer> findClosestElements2(int[] arr, int k, int x) {

        int start = 0;
        int end = arr.length - 1;
        // Between the 'start' and 'end' pointers, inclusive, contains all the k integers that is closest to x.
        while (end - start >= k) {
            // Move 'start' to the right if 'end' is closer to x, or move 'end' to the left if 'start' is closer to x.
            if (Math.abs(arr[start] - x) > Math.abs(arr[end] - x)) {
                start++;
            } else {
                end--;
            }
        }

        // Input all the k closest integers into the result.
        List<Integer> result = new ArrayList<>(k);
        for (int i = start; i <= end; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    // Approach:
    // Using a min heap priority queue, add all the smallest integers up to k integers.
    // Then, traverse the 'arr' array will replacing the priority queue with integer closer to x.

    public static List<Integer> findClosestElements3(int[] arr, int k, int x) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // Traverse the array with,
        // First, add all the smallest integers up to k number.
        // Second, if found a closer integer to x, remove the smallest integer from the priority queue, and add the new integer.
        // This is because the smallest integer is always the further to x, if a larger number is closer to x.
        for (int integer : arr) {
            if (k > 0) {
                minHeap.offer(integer);
                k--;
            } else if (Math.abs(minHeap.peek() - x) > Math.abs(integer - x)) {
                minHeap.poll();
                minHeap.offer(integer);
            }
        }

        // Add the integers from the priority queue to the result.
        // This will automatically add in ascending order, from smallest to largest k integers closest to x.
        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll());
        }
        return result;
    }

    // Approach:
    // Using binary search and a sliding window, find the midpoint where,
    // the integers between midpoint and midpoint + k is the k closest integers to x.
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        // The sliding window is between 'mid' and 'mid' + k.
        int left = 0, right = arr.length - k;
        while (left < right) {
            int midpoint = left + (right - left) / 2; // same as (left + right) / 2

            // With midpoint on the left, we use x - arr[midpoint], while arr[midpoint + k] - x because it is on the right.
            // This is important!
            // Rather than using Math.abs(), we need the direction keep the x within the sliding window.
            // If the window is too far left, we shift the window to the right.
            if (x - arr[midpoint] > arr[midpoint + k] - x) {
                left = midpoint + 1;
            }
            // If the window is too far right, we shift the window to the left.
            else {
                right = midpoint;
            }
        }

        // Input all the k closest integers into the result.
        List<Integer> result = new ArrayList<>(k);
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5};
//        int k = 4, x = 3;
        int[] arr = {1,1,2,2,2,2,2,3,3};
        int k = 3, x = 3;
        List<Integer> re = findClosestElements2(arr, k, x);
        for (Integer i : re) {
            System.out.print(i + ",");
        }
    }
}
