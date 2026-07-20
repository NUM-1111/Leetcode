package com.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * LC1 - 两数之和
 * 
 * 核心知识点：HashMap 查补数
 * 
 * 思路：一遍遍历，用 HashMap 记录「值 → 下标」。
 * 对每个数，看 target - num 是否已在 map 中，在则返回两个下标。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class LC1_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] { i, map.get(target - nums[i]) };
            }
            map.put(nums[i], i);
        }

        return null;
    }
}