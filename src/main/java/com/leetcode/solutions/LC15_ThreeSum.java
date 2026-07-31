package com.leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC15 - 三数之和
 * 
 * 核心知识点：排序 + 双指针 + 去重
 * 
 * 思路：先排序，固定 nums[i]，双指针 left/right 在 [i+1, n-1] 区间夹逼找和为 -nums[i]。
 * 三重去重：i 跳过重复 nums[i]；找到一组解后 left 和 right 也跳过重复。
 * 
 * 时间复杂度：O(n²)
 * 空间复杂度：O(log n) ~ O(n)（排序栈空间）
 */
public class LC15_ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i];
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    list.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
            }
        }
        return list;
    }
}