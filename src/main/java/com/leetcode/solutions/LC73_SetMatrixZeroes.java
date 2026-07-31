package com.leetcode.solutions;

/**
 * LC73 - 矩阵置零 (Set Matrix Zeroes)
 *
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。
 * 请使用原地算法。
 *
 * 示例 1：
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 *
 * 示例 2：
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *
 * 约束条件：
 * - m == matrix.length
 * - n == matrix[i].length
 * - 1 <= m, n <= 200
 * - -2^31 <= matrix[i][j] <= 2^31 - 1
 *
 * 进阶：
 * - 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。
 * - 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * - 你能想出一个仅使用常量空间的解决方案吗？
 *
 * 核心套路：首行首列标记法（原地算法，O(1) 额外空间）
 * 时间复杂度：O(m * n)
 * 空间复杂度：O(1)
 */
public class LC73_SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRow = false;
        boolean firstCol = false;

        // 检查第一行是否有0
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRow = true;
                break;
            }
        }

        // 检查第一列是否有0
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }

        // 用第一行第一列标记需要置零的行和列
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 根据标记置零（除第一行第一列外）
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 处理第一行
        if (firstRow) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        // 处理第一列
        if (firstCol) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        LC73_SetMatrixZeroes solution = new LC73_SetMatrixZeroes();

        // 测试用例 1
        int[][] matrix1 = {{1,1,1},{1,0,1},{1,1,1}};
        System.out.println("输入: [[1,1,1],[1,0,1],[1,1,1]]");
        solution.setZeroes(matrix1);
        System.out.print("输出: ");
        printMatrix(matrix1);
        System.out.println("预期: [[1,0,1],[0,0,0],[1,0,1]]");
        System.out.println();

        // 测试用例 2
        int[][] matrix2 = {{0,1,2,0},{3,4,5,2},{1,3,1,5}};
        System.out.println("输入: [[0,1,2,0],[3,4,5,2],[1,3,1,5]]");
        solution.setZeroes(matrix2);
        System.out.print("输出: ");
        printMatrix(matrix2);
        System.out.println("预期: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]");
        System.out.println();

        // 测试用例 3 - 边界：单元素矩阵
        int[][] matrix3 = {{0}};
        System.out.println("输入: [[0]]");
        solution.setZeroes(matrix3);
        System.out.print("输出: ");
        printMatrix(matrix3);
        System.out.println("预期: [[0]]");
    }

    private static void printMatrix(int[][] matrix) {
        System.out.print("[");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("[");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
                if (j < matrix[i].length - 1) System.out.print(",");
            }
            System.out.print("]");
            if (i < matrix.length - 1) System.out.print(",");
        }
        System.out.println("]");
    }
}