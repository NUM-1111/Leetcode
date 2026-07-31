package com.leetcode.solutions;

/**
 * LC189 - 轮转数组 (Rotate Array)
 *
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4,5,6,7], k = 3
 * 输出：[5,6,7,1,2,3,4]
 * 解释：
 * 向右轮转 1 步：[7,1,2,3,4,5,6]
 * 向右轮转 2 步：[6,7,1,2,3,4,5]
 * 向右轮转 3 步：[5,6,7,1,2,3,4]
 *
 * 示例 2：
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释：
 * 向右轮转 1 步：[99,-1,-100,3]
 * 向右轮转 2 步：[3,99,-1,-100]
 *
 * 约束：
 * - 1 <= nums.length <= 10^5
 * - -2^31 <= nums[i] <= 2^31 - 1
 * - 0 <= k <= 10^5
 *
 * 核心套路：三次翻转（最优解，空间 O(1)）
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 */
public class LC189_RotateArray {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    void reverse(int[] nums, int l, int r) {
        while (l >= 0 && r < nums.length && l < r) {
            int t = nums[l];
            nums[l] = nums[r];
            nums[r] = t;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        LC189_RotateArray solution = new LC189_RotateArray();

        // 测试用例 1
        int[] nums1 = {1,2,3,4,5,6,7};
        solution.rotate(nums1, 3);
        System.out.print("nums = [1,2,3,4,5,6,7], k = 3 => ");
        print(nums1);
        // 预期: [5,6,7,1,2,3,4]

        // 测试用例 2
        int[] nums2 = {-1,-100,3,99};
        solution.rotate(nums2, 2);
        System.out.print("nums = [-1,-100,3,99], k = 2 => ");
        print(nums2);
        // 预期: [3,99,-1,-100]

        // 边界用例：k 超出长度
        int[] nums3 = {1,2};
        solution.rotate(nums3, 3);
        System.out.print("nums = [1,2], k = 3 => ");
        print(nums3);
        // 预期: [2,1]

        // 边界用例：k = 0
        int[] nums4 = {1,2,3};
        solution.rotate(nums4, 0);
        System.out.print("nums = [1,2,3], k = 0 => ");
        print(nums4);
        // 预期: [1,2,3]
    }

    private static void print(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(",");
        }
        System.out.println("]");
    }
}