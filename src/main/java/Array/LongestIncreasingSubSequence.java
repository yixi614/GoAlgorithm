package Array;

import java.util.Arrays;

public class LongestIncreasingSubSequence {
    /*
    * Given an integer array nums, return the length of the longest strictly increasing
    subsequence
    .

    Example 1:

    Input: nums = [10,9,2,5,3,7,101,18]
    Output: 4
    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
    Example 2:

    Input: nums = [0,1,0,3,2,3]
    Output: 4
    Example 3:

    Input: nums = [7,7,7,7,7,7,7]
    Output: 1
    * */


    /**
     *
     * Intuition
     * Alright, so in this question, the one thing that we need to keep track of smaller elements that have appeared before the current element. Let's consider this exmaple.
     *
     * If 2 is smaller than 3 then it is definetly smaller than 5, basically all the elements smaller than 3 will definitely be smaller than 5.
     *
     * So it gives us the idea that it is better to keep track of previous calculations, which gives us the idea of dynamic programming.
     *
     * Approach
     * We will create an array of size n, where n is the number of elements in the given array and initialise all the elements of the array to 1, because the Longest increasing sequence for any single element is 1.
     *
     * Now we will iterate through the array and for each element nums[i], we will check all the previous elements nums[j], j<i.
     *
     * At any given point dp[i] would denote the number of smaller elements that have appeared before nums[i].
     *
     * If nums[j] < nums[i], we will update the dp[i] as
     * dp[i] = max(dp[i], dp[j]+1), since all the elements smaller than nums[j] will definitely be smaller than nums[i].
     *
     * Lastly, we will just return the maximum value from the dp array, that would denote the length of the longest increasing subsequence in the given array.
     *
     * Complexity
     * Time complexity:
     * O(n^2), because of nested loop.
     *
     * Space complexity:
     * O(n), because we are using extra array (dp).
     * */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int maxLength = Arrays.stream(dp).max().orElse(0);
        return maxLength;
    }

}
