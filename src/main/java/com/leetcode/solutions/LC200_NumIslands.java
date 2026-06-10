package com.leetcode.solutions;

/**
 * 200. 岛屿数量
 * <p>
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * DFS 沉岛法解法
 * 时间复杂度：O(m × n)
 * 空间复杂度：O(m × n) 递归栈（最坏情况整个网格都是陆地）
 */
public class LC200_NumIslands {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 注意：grid 是 char[][]，'1' 是字符，不是数字 1
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, m, n);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * DFS 沉岛：将连通的 '1' 全部标记为 '0'
     */
    private void dfs(char[][] grid, int i, int j, int m, int n) {
        // 越界 或 已经是水 → 返回
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';  // 沉岛：将当前陆地标记为水

        // 四个方向继续 DFS
        dfs(grid, i + 1, j, m, n);
        dfs(grid, i - 1, j, m, n);
        dfs(grid, i, j + 1, m, n);
        dfs(grid, i, j - 1, m, n);
    }
}