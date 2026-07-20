package com.leetcode.solutions;

/**
 * LC283 - 移动零
 * 
 * 核心知识点：双指针 / 非零前置
 * 
 * 思路：第一遍遍历，将所有非零元素按顺序前置（用慢指针 j 记录插入位置）。
 * 第二遍将剩余位置全部补 0。
 * 不改变非零元素的相对顺序。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class LC283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                j++;
            }
        }

        for (; j < nums.length; j++) {
            nums[j] = 0;
        }
    }
}