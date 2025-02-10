package DynamicProgramming;

/**
 * leetcode 85 Maximal Rectangle
 * Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * Output: 6
 * Explanation: The maximal rectangle is shown in the above picture.
 * <p>
 * <p>
 * <p>
 * We can apply the maximum in histogram in each row of the 2D matrix. What we need is to maintain an int array for each row, which represent for the height of the histogram.
 * <p>
 * Please refer to https://leetcode.com/problems/largest-rectangle-in-histogram/ first.
 * <p>
 * Suppose there is a 2D matrix like
 * <p>
 * 1 1 0 1 0 1
 * <p>
 * 0 1 0 0 1 1
 * <p>
 * 1 1 1 1 0 1
 * <p>
 * 1 1 1 1 0 1
 * <p>
 * First initiate the height array as 1 1 0 1 0 1, which is just a copy of the first row. Then we can easily calculate the max area is 2.
 * <p>
 * Then update the array. We scan the second row, when the matrix[1][i] is 0, set the height[i] to 0; else height[i] += 1, which means the height has increased by 1. So the height array again becomes 0 2 0 0 1 2. The max area now is also 2.
 * <p>
 * Apply the same method until we scan the whole matrix. the last height arrays is 2 4 2 2 0 4, so the max area has been found as 2 * 4 = 8.
 * <p>
 * Then reason we scan the whole matrix is that the maximum value may appear in any row of the height.
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[] height = new int[matrix[0].length];
        // set up histogram for row 0
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1') height[i] = 1;
        }
        // get max rectangle area
        int result = LargestRectangeInHistogram.largestRectangleArea(height);
        // scan each row, accumulate the heights array and get max area
        for (int i = 1; i < matrix.length; i++) {
            updateHeight(matrix, height, i);
            result = Math.max(result, LargestRectangeInHistogram.largestRectangleArea(height));
        }
        return result;
    }
    // if encounter 0, set heights to 0. If encounter 1, accumulate the heights
    private void updateHeight(char[][] matrix, int[] height, int idx) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[idx][i] == '1') height[i] += 1;
            else height[i] = 0;
        }
    }
}
