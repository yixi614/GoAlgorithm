package DynamicProgramming;

/**
 * leetcode 84 Largest Rectangle in Histogram
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * <p>
 * <p>
 * basic explanation see https://leetcode.com/problems/largest-rectangle-in-histogram/solutions/1727776/java-c-explanation-going-from-brute-to-optimal-approach/
 * but we don't use the stack which is hard not intuitive.
 *
 *
 * for 2,1,5,6,4,3,6,2,1
 * closestLowerBarToTheLeft[0] = -1;
 * i = 1, p = 0, then closestLowerBarToTheLeft[1] = closestLowerBarToTheLeft[0] = -1
 * i = 2, p = 1, then closestLowerBarToTheLeft[2] = 1
 * i = 3, p = 2, then closestLowerBarToTheLeft[3] = 2
 * i = 4, p = 3, bar 6 is higher, p = closestLowerBarToTheLeft[3] = 2, bar 5 is higher than 4, p = closestLowerBarToTheLeft[2] = 1
 *              bar 1 is lower, closestLowerBarToTheLeft[4] = 1
 * i = 5, p = 4, bar 4 is higher, p = closestLowerBarToTheLeft[4] = 1. index 1 bar height is 1 is lower than 3, set closestLowerBarToTheLeft[5] = 1. skipped 3 p--
 * i = 6, p = 5, closestLowerBarToTheLeft[6] = 5
 * i = 7, p = 6, bar 6 is higher than 2, p = closestLowerBarToTheLeft[6] = 5, index 5 bar 3 is higher than 2, p = closestLowerBarToTheLeft[5] = 1.
 *              set closestLowerBarToTheLeft[7] = 1. skipped 4 p-- operations
 * i = 8, p = 7, bar 2 is higher, p = closestLowerBarToTheLeft[7] = 1, bar 1 is smaller or equal to 1, set closestLowerBarToTheLeft[8] = 1. skipped 5 p-- operations
 */
public class LargestRectangeInHistogram {
    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] closestLowerBarToTheLeft = new int[height.length]; // idx of the first bar to the left that is lower than current
        int[] closestLowerBarToTheRight = new int[height.length]; // idx of the first bar to the right that is lower than current
        closestLowerBarToTheRight[height.length - 1] = height.length;
        closestLowerBarToTheLeft[0] = -1;

        // populate the closestLowerBarToTheLeft array
        for (int i = 1; i < height.length; i++) {
            int p = i - 1;

            // skip the histogram bar which is higher
            while (p >= 0 && height[p] >= height[i]) {
                // p--;
                // instead of p--, The only line change shifts this algorithm from O(n^2) to O(n) complexity:
                // we don't need to rescan each item to the left - we can reuse results of previous calculations and "jump" through indices in quick manner:
                p = closestLowerBarToTheLeft[p];
            }
            closestLowerBarToTheLeft[i] = p;
        }

        // populate the closestLowerBarToTheRight array
        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < height.length && height[p] >= height[i]) {
                p = closestLowerBarToTheRight[p];
            }
            closestLowerBarToTheRight[i] = p;
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (closestLowerBarToTheRight[i] - closestLowerBarToTheLeft[i] - 1));
        }

        return maxArea;
    }
}
