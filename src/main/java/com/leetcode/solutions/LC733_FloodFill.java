package com.leetcode.solutions;

/**
 * LeetCode 733 — 图像渲染 (Flood Fill)
 * <p>
 * 核心知识点：DFS 深度优先搜索 / BFS 广度优先搜索 / 方向数组
 * 时间复杂度：O(m × n)，最坏遍历全图
 * 空间复杂度：O(m × n)，最坏递归栈深度为全图大小（DFS）/ 队列存储全图（BFS）
 * <p>
 * 思路：
 * 从 (sr, sc) 出发，将四方向连通且颜色等于原始颜色的像素全部染成新颜色。
 * 关键边界条件：若原颜色 == 新颜色，直接返回原图，否则 DFS 会因为颜色不变导致无限递归。
 */
public class LC733_FloodFill {

    // DFS 解法：方向数组写法
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        // 关键边界：原色等于新色时，DFS 染色无效，直接返回避免无限递归
        if (image[sr][sc] == color) {
            return image;
        }

        int originalColor = image[sr][sc];
        dfs(image, sr, sc, originalColor, color);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int originalColor, int newColor) {
        // 越界检查 + 非原色（已访问或不同色）直接返回
        if (r < 0 || r >= image.length || c < 0 || c >= image[0].length
                || image[r][c] != originalColor) {
            return;
        }

        // 染色
        image[r][c] = newColor;

        // 方向数组：上、下、左、右
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : dirs) {
            dfs(image, r + d[0], c + d[1], originalColor, newColor);
        }
    }
}