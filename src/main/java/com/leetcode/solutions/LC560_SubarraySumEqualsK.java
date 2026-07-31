package com.leetcode.solutions;

import java.util.*;

/**
 * LeetCode 560 - 和为 K 的子数组 (Subarray Sum Equals K)
 *
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数。
 *
 * 子数组是数组中元素的连续非空序列。
 *
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 * 解释：[1,1] 和 [1,1]（第2到第3个元素）
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 * 解释：[1,2] 和 [3]
 *
 * 示例 3：
 * 输入：nums = [-1,-1,1], k = 0
 * 输出：1
 * 解释：[-1,1]
 *
 * 约束：
 * - 1 <= nums.length <= 2 * 10^4
 * - -1000 <= nums[i] <= 1000
 * - -10^7 <= k <= 10^7
 *
 * 核心套路：前缀和 + HashMap
 * 时间复杂度: O(n)
 * 空间复杂度: O(n)
 */
public class LC560_SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);  // 空数组的前缀和为 0

        int count = 0;
        int prefixSum = 0;

        for (int num : nums) {
            prefixSum += num;

            // 关键：先检查 diff 是否在 map 中
            int diff = prefixSum - k;
            if (map.containsKey(diff)) {
                count += map.get(diff);
            }

            // 后把自己加进 map（避免 k=0 时空子数组问题）
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        LC560_SubarraySumEqualsK solution = new LC560_SubarraySumEqualsK();

        // 测试用例 1
        System.out.println("nums = [1,1,1], k = 2 => " + solution.subarraySum(new int[]{1, 1, 1}, 2));
        // 预期: 2

        // 测试用例 2
        System.out.println("nums = [1,2,3], k = 3 => " + solution.subarraySum(new int[]{1, 2, 3}, 3));
        // 预期: 2

        // 边界用例：包含负数
        System.out.println("nums = [-1,-1,1], k = 0 => " + solution.subarraySum(new int[]{-1, -1, 1}, 0));
        // 预期: 1

        // 边界用例：单个元素
        System.out.println("nums = [1], k = 1 => " + solution.subarraySum(new int[]{1}, 1));
        // 预期: 1
    }
}