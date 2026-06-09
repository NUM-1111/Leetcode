package com.leetcode.solutions;

/**
 * 1. Two Sum
 * <p>
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出和为目标值的那两个整数，并返回它们的数组下标。
 * 假设每种输入只会对应一个答案，且同一个元素不能使用两遍。
 * <p>
 * 时间复杂度 O(n) 空间复杂度 O(n)
 */
public class LC1_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}