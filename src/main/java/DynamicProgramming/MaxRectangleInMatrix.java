package DynamicProgramming;

public class MaxRectangleInMatrix {

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int maxArea = 0;
        int[] heights = new int[matrix[0].length];

        for (char[] row : matrix) {
            for (int i = 0; i < row.length; i++) {
                heights[i] = row[i] == '1' ? heights[i] + 1 : 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }
    // DP
    public static int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int[] lessFromLeft = new int[height.length]; // idx of the first bar the left that is lower than current
        int[] lessFromRight = new int[height.length]; // idx of the first bar the right that is lower than current
        lessFromRight[height.length - 1] = height.length;
        lessFromLeft[0] = -1;

        /*
        * The main trick is how to effectively calculate lessFromRight and lessFromLeft arrays.
        * The trivial solution is to use O(n^2) solution and for each i element first find his left/right heighbour in the second inner loop just iterating back or forward:
        for (int i = 1; i < height.length; i++) {
            int p = i - 1;
            while (p >= 0 && height[p] >= height[i]) {
                p--;
            }
            lessFromLeft[i] = p;
        }
        *
        * The trick is "p = lessFromLeft[p]" instead of "p--";
        * The only line change shifts this algorithm from O(n^2) to O(n) complexity:
        * we don't need to rescan each item to the left - we can reuse results of previous calculations and
        * "jump" through indices in quick manner
        * */
        for (int i = 1; i < height.length; i++) {
            int p = i - 1;
            while (p >= 0 && height[p] >= height[i]) {
                p = lessFromLeft[p];
            }
            lessFromLeft[i] = p;
        }
        for (int i = height.length - 2; i >= 0; i--) {
            int p = i + 1;

            while (p < height.length && height[p] >= height[i]) {
                p = lessFromRight[p];
            }
            lessFromRight[i] = p;
        }
        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            maxArea = Math.max(maxArea, height[i] * (lessFromRight[i] - lessFromLeft[i] - 1));
        }
        return maxArea;
    }

    public int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols + 1]; // Include an extra element for easier calculation
        int maxArea = 0;

        for (char[] row : matrix) {
            for (int i = 0; i < cols; i++) {
                heights[i] = (row[i] == '1') ? heights[i] + 1 : 0;
            }
            // Calculate max area using histogram method
            int n = heights.length; // Number of bars in the histogram
            // bruce force way
            for (int i = 0; i < n; i++) {
                for (int j = i, minHeight = Integer.MAX_VALUE; j < n; j++) {
                    minHeight = Math.min(minHeight, heights[j]);
                    int area = minHeight * (j - i + 1);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }
}
