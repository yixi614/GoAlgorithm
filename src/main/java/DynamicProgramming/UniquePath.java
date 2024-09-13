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
*
* There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.

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

  private int[][] dp;
  private int m;
  private int n;

  public int uniquePaths(int m, int n) {
    this.dp = new int[m][n];
    this.m = m;
    this.n = n;
    return dfs(0, 0);
  }

  private int dfs(int x, int y) {
    if (x > m - 1 || y > n - 1)
      return 0;
    if (x == m - 1 && y == n - 1)
      return 1;
    if (dp[x][y] == 0)
      dp[x][y] = dfs(x + 1, y) + dfs(x, y + 1);
    return dp[x][y];
  }
}
