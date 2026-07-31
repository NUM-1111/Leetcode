package com.leetcode.solutions;

import java.util.Arrays;

/**
 * LC48 - 旋转图像 (Rotate Image)
 *
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * 约束条件：
 * - n == matrix.length
 * - n == matrix[i].length
 * - 1 <= n <= 20
 * - -1000 <= matrix[i][j] <= 1000
 *
 * 核心套路：转置 + 水平翻转
 * 时间复杂度：O(n²)
 * 空间复杂度：O(1)
 */
public class LC48_RotateImage {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 1. 转置（沿主对角线，只遍历上三角）
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 2. 每行水平反转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        LC48_RotateImage solution = new LC48_RotateImage();

        int[][] matrix1 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("输入: [[1,2,3],[4,5,6],[7,8,9]]");
        solution.rotate(matrix1);
        System.out.println("输出: " + deepToString(matrix1));
        System.out.println("预期: [[7,4,1],[8,5,2],[9,6,3]]");
        System.out.println();

        int[][] matrix2 = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        System.out.println("输入: [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]");
        solution.rotate(matrix2);
        System.out.println("输出: " + deepToString(matrix2));
        System.out.println("预期: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]");
        System.out.println();

        int[][] matrix3 = {{1}};
        System.out.println("输入: [[1]]");
        solution.rotate(matrix3);
        System.out.println("输出: " + deepToString(matrix3));
        System.out.println("预期: [[1]]");
    }

    private static String deepToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < matrix.length; i++) {
            sb.append(Arrays.toString(matrix[i]));
            if (i < matrix.length - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}