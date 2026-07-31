package com.leetcode.solutions;

/**
 * LC238 - 除自身以外数组的乘积 (Product of Array Except Self)
 *
 * 给你一个整数数组 nums，返回数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外
 * 其余各元素的乘积。
 *
 * 题目数据保证数组 nums 中任意元素的全部前缀元素和后缀的乘积都在 32 位整数范围内。
 *
 * 请不要使用除法，且在 O(n) 时间复杂度内完成。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：[24,12,8,6]
 *
 * 示例 2：
 * 输入：nums = [-1,1,0,-3,3]
 * 输出：[0,0,9,0,0]
 *
 * 约束：
 * - 2 <= nums.length <= 10^5
 * - -30 <= nums[i] <= 30
 * - 任意元素的前缀积和后缀积都在 32 位整数范围内
 *
 * 核心套路：前缀积 + 后缀积（空间 O(1)，输出数组不算额外空间）
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 */
public class LC238_ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int[] answer = new int[nums.length];
        answer[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        int suffix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] *= suffix;
            suffix *= nums[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        LC238_ProductOfArrayExceptSelf solution = new LC238_ProductOfArrayExceptSelf();

        // 测试用例 1
        int[] result1 = solution.productExceptSelf(new int[]{1,2,3,4});
        System.out.print("[1,2,3,4] => ");
        print(result1);
        // 预期: [24,12,8,6]

        // 测试用例 2
        int[] result2 = solution.productExceptSelf(new int[]{-1,1,0,-3,3});
        System.out.print("[-1,1,0,-3,3] => ");
        print(result2);
        // 预期: [0,0,9,0,0]

        // 边界用例：两个元素
        int[] result3 = solution.productExceptSelf(new int[]{2,3});
        System.out.print("[2,3] => ");
        print(result3);
        // 预期: [3,2]
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