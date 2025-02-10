/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package DynamicProgramming;

/*
* leetcode 62 unique paths
* There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
* The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.

The test cases are generated so that the answer will be less than or equal to 2 * 109.
* Example 2:

Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down
* */
public class UniquePath {

    /**
     * 2D dp
     * Intuition and Logic Behind the Solution
     * The idea behind this approach is to use a 2D Dynamic Programming (DP) array to store the number of unique paths to each cell.
     * A cell (i,j) can be reached either from (i−1,j) or (i,j−1), and thus
     * the number of unique paths to (i,j) is the sum of the number of unique paths to these two cells.
     * <p>
     * Step-by-step Explanation
     * Initialization:
     * <p>
     * Create a m×n DP array, initializing the first row and first column to 1 because there's only one way to reach those cells from the starting point.
     * Main Algorithm:
     * <p>
     * Iterate over the DP array starting from cell (1,1).
     * For each cell (i,j), set dp[i][j]=dp[i−1][j]+dp[i][j−1].
     * Complexity Analysis
     * Time Complexity: O(m×n) — We iterate through each cell once.
     * Space Complexity: O(m×n) — For the DP array.
     * <p>
     * <p>
     * <p>
     * we can optimize the memory from O(M*N) to O(N)
     * Intuition and Logic Behind the Solution
     * The original DP solution used a m×n array to store the number of unique paths to each cell.
     * However, since we only need information from the previous row and the current row to compute the number of unique paths for a given cell,
     * we can optimize the solution to use only two rows at a time. This reduces the space complexity from O(m×n) to O(n).
     * <p>
     * Transitioning from O(m×n) to O(n)
     * In the original O(m×n) approach, we used a 2D array dp where dp[i][j] represented the number of unique paths to reach cell (i,j).
     * To optimize this to O(n), we can maintain only two 1D arrays: prev_row and curr_row, each of length n.
     * <p>
     * prev_row[j] will represent dp[i−1][j], the number of unique paths to reach the cell in the previous row and j-th column.
     * curr_row[j] will represent dp[i][j], the number of unique paths to reach the cell in the current row and j-th column.
     */
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public int uniquePath(int m, int n) {

        int[] preRow = new int[n];
        int[] curRow = new int[n];

        for (int i = 0; i < n; i++) {
            preRow[i] = 1;
            curRow[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                curRow[j] = curRow[j - 1] + preRow[j];
            }
            // swap pre and cur so that next iteration's preRow has correct values(curRow)
            int[] tmp = curRow;
            curRow = preRow;
            preRow = tmp;
        }
        return preRow[n - 1];
    }
}
