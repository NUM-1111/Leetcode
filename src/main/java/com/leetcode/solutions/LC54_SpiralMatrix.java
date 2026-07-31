package com.leetcode.solutions;

import java.util.*;

/**
 * LC54 - 螺旋矩阵 (Spiral Matrix)
 *
 * 给你一个 m 行 n 列的矩阵 matrix，请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 约束条件：
 * - m == matrix.length
 * - n == matrix[i].length
 * - 1 <= m, n <= 10
 * - -100 <= matrix[i][j] <= 100
 *
 * 核心套路：四边界收缩（top, bottom, left, right）
 * 时间复杂度：O(m * n)
 * 空间复杂度：O(1)（不含输出数组）
 */
public class LC54_SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {
            // 1. 左→右（上边）
            for (int j = left; j <= right; j++)
                res.add(matrix[top][j]);
            top++;

            // 2. 上→下（右边）
            for (int i = top; i <= bottom; i++)
                res.add(matrix[i][right]);
            right--;

            // 3. 右→左（下边）
            if (top <= bottom) {
                for (int j = right; j >= left; j--)
                    res.add(matrix[bottom][j]);
                bottom--;
            }

            // 4. 下→上（左边）
            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    res.add(matrix[i][left]);
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LC54_SpiralMatrix solution = new LC54_SpiralMatrix();

        // 测试用例 1
        int[][] matrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("输入: [[1,2,3],[4,5,6],[7,8,9]]");
        System.out.println("输出: " + solution.spiralOrder(matrix1));
        System.out.println("预期: [1,2,3,6,9,8,7,4,5]");
        System.out.println();

        // 测试用例 2
        int[][] matrix2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println("输入: [[1,2,3,4],[5,6,7,8],[9,10,11,12]]");
        System.out.println("输出: " + solution.spiralOrder(matrix2));
        System.out.println("预期: [1,2,3,4,8,12,11,10,9,5,6,7]");
        System.out.println();

        // 测试用例 3 - 边界：单行
        int[][] matrix3 = {{1,2,3,4}};
        System.out.println("输入: [[1,2,3,4]]");
        System.out.println("输出: " + solution.spiralOrder(matrix3));
        System.out.println("预期: [1,2,3,4]");
        System.out.println();

        // 测试用例 4 - 边界：单列
        int[][] matrix4 = {{1},{2},{3}};
        System.out.println("输入: [[1],[2],[3]]");
        System.out.println("输出: " + solution.spiralOrder(matrix4));
        System.out.println("预期: [1,2,3]");
    }
}