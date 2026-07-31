package com.leetcode.solutions;

import java.util.*;

/**
 * LC15 - 三数之和 (3Sum)
 * 
 * 核心知识点：排序 + 双指针夹逼 + 去重
 * 
 * 时间复杂度: O(n²)
 * 空间复杂度: O(1)（不计结果集）
 */
public class LC15_三数之和 {

    /**
     * 排序后固定第一个数 nums[i]，在 [i+1, n-1] 区间用双指针找两数之和为 -nums[i]。
     * 去重分两层：i 层跳过相同值；left/right 找到解后 while 跳过所有重复。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}