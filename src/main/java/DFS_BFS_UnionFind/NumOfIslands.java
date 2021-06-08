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

package DFS_BFS_UnionFind;

import Common.UnionFind;

import java.util.HashMap;
import java.util.Map;

public class NumOfIslands {
  // DFS
  public int numIslands(char[][] grid) {
    int res = 0;
    HashMap<Integer, Boolean> visited = new HashMap();
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == '1') {
          res++;
          dfs(grid, i, j, visited);
        }
      }
    }
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == 'x') {
          grid[i][j] = '1';
        }
      }
    }
    return res;
  }

  public void dfs(char[][] grid, int i, int j, Map<Integer, Boolean> visited) {
    int rows = grid.length;
    int cols = grid[0].length;
    if (i < 0 || i >= rows || j < 0 || j >= cols) {
      return;
    }
    if (visited.get((int) grid[i][j]) != null) {
      return;
    }
    if (grid[i][j] == '0') {
      visited.put((int) grid[i][j], true);
      return;
    }
    if (grid[i][j] == '1') {
      grid[i][j] = 'x';
      visited.put((int) grid[i][j], true);
    }
    dfs(grid, i, j + 1, visited);
    dfs(grid, i, j - 1, visited);
    dfs(grid, i + 1, j, visited);
    dfs(grid, i - 1, j, visited);
  }

  // UnionFind
  public int numIslands2(char[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    UnionFind uf = new UnionFind(rows * cols);
    int totalOnes = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == '1') {
          totalOnes++;
        }
      }
    }
    uf.setCount(totalOnes);
    int[][] d = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    // union all connected 1
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == '1') {
          // search around four directions
          for (int k = 0; k < d.length; k++) {
            int x = i + d[k][0];
            int y = j + d[k][1];
            if (0 <= x && x <= rows - 1 && 0 <= y &&
                y <= cols - 1 && grid[x][y] == '1') {
              uf.union(x * cols + y, i * cols + j);
            }
          }
        }
      }
    }
    return uf.count();
  }
}
