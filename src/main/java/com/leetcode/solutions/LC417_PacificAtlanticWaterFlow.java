package com.leetcode.solutions;

import java.util.*;

/**
 * 417-太平洋大西洋水流问题
 * 核心知识点：多源 BFS / 反向搜索（从边界出发）
 * 时间复杂度：O(M × N)，每个格子最多被每个海洋 BFS 访问一次
 * 空间复杂度：O(M × N)，两个 visited 矩阵 + 队列
 *
 * <p>核心思路：正向 DFS 每个格子 → O(MN×MN) 太慢。
 * 改为反向：从太平洋边界（上、左）和大西洋边界（下、右）同时 BFS，
 * 条件为 heights[nx][ny] >= heights[x][y]（反向水流，水往高处灌）。
 * 两个 visited 矩阵的交集即答案。
 */
public class LC417_PacificAtlanticWaterFlow {

    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0) {
            return result;
        }

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        Queue<int[]> queuePac = new LinkedList<>();
        Queue<int[]> queueAtl = new LinkedList<>();

        // 初始化太平洋边界：第一行 + 第一列
        for (int i = 0; i < m; i++) {
            queuePac.offer(new int[]{i, 0});
            pacific[i][0] = true;
        }
        for (int j = 0; j < n; j++) {
            queuePac.offer(new int[]{0, j});
            pacific[0][j] = true;
        }

        // 初始化大西洋边界：最后一行 + 最后一列
        for (int i = 0; i < m; i++) {
            queueAtl.offer(new int[]{i, n - 1});
            atlantic[i][n - 1] = true;
        }
        for (int j = 0; j < n; j++) {
            queueAtl.offer(new int[]{m - 1, j});
            atlantic[m - 1][j] = true;
        }

        // BFS 扩散
        bfs(heights, queuePac, pacific);
        bfs(heights, queueAtl, atlantic);

        // 收集同时可达两个海洋的格子
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void bfs(int[][] heights, Queue<int[]> queue, boolean[][] visited) {
        int m = heights.length;
        int n = heights[0].length;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int[] dir : DIRS) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n
                        && !visited[nx][ny]
                        && heights[nx][ny] >= heights[x][y]) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    // ==================== DFS 备选写法 ====================

    public List<List<Integer>> pacificAtlanticDFS(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0) {
            return result;
        }

        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(heights, i, 0, pacific);
            dfs(heights, i, n - 1, atlantic);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, 0, j, pacific);
            dfs(heights, m - 1, j, atlantic);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private void dfs(int[][] heights, int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        int m = heights.length;
        int n = heights[0].length;

        for (int[] dir : DIRS) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n
                    && !visited[nx][ny]
                    && heights[nx][ny] >= heights[x][y]) {
                dfs(heights, nx, ny, visited);
            }
        }
    }

    // ==================== 测试用例 ====================

    public static void main(String[] args) {
        var solution = new LC417_PacificAtlanticWaterFlow();

        // 测试 1：官方示例
        int[][] heights1 = {
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4}
        };
        var result1 = solution.pacificAtlantic(heights1);
        System.out.println("Test 1: " + result1.size() + " cells → " + result1);

        // 测试 2：单格
        int[][] heights2 = {{1}};
        var result2 = solution.pacificAtlantic(heights2);
        System.out.println(result2.size() == 1 && result2.get(0).equals(Arrays.asList(0, 0))
                ? "Test 2 passed" : "Test 2 failed: got " + result2);

        // 测试 3：两格
        int[][] heights3 = {{1, 2}};
        var result3 = solution.pacificAtlantic(heights3);
        System.out.println(result3.size() == 2
                ? "Test 3 passed" : "Test 3 failed: got " + result3);

        // 测试 4：都流向太平洋（降序矩阵）
        int[][] heights4 = {{3, 2}, {1, 0}};
        var result4 = solution.pacificAtlantic(heights4);
        System.out.println(result4.size() > 0
                ? "Test 4 passed" : "Test 4 failed: unexpected empty");
    }
}