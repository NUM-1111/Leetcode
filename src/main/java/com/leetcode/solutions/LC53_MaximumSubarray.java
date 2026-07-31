package com.leetcode.solutions;

/**
 * LC53 - 最大子数组和 (Maximum Subarray)
 *
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），
 * 返回其最大和。
 *
 * 子数组是数组中的一个连续部分。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 * 约束：
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 *
 * 核心套路：Kadane 算法（动态规划/贪心）
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 */
public class LC53_MaximumSubarray {
    public int maxSubArray(int[] nums) {
        //Robustness Constrction
        if (nums == null || nums.length == 0)
            return 0;
        
        int currentMax = nums[0];
        int globalMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // Greedy: either extend the existing subarray or start a new one
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            globalMax = Math.max(globalMax, currentMax);
        }
        return globalMax;
    }

    public static void main(String[] args) {
        LC53_MaximumSubarray solution = new LC53_MaximumSubarray();

        // 测试用例 1
        System.out.println("[-2,1,-3,4,-1,2,1,-5,4] => " +
            solution.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        // 预期: 6

        // 测试用例 2
        System.out.println("[1] => " +
            solution.maxSubArray(new int[]{1}));
        // 预期: 1

        // 测试用例 3
        System.out.println("[5,4,-1,7,8] => " +
            solution.maxSubArray(new int[]{5,4,-1,7,8}));
        // 预期: 23

        // 边界用例：全负数
        System.out.println("[-1,-2,-3] => " +
            solution.maxSubArray(new int[]{-1,-2,-3}));
        // 预期: -1
    }
}