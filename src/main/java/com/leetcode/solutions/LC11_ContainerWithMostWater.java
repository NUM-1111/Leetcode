package com.leetcode.solutions;

/**
 * LC11 - 盛最多水的容器
 * 
 * 核心知识点：双指针夹逼
 * 
 * 思路：左右指针从两端向中间移动，每次计算当前容量 min(height[l], height[r]) * (r-l)，
 * 更新最大值。每次移动较矮的那一侧指针（矮侧决定容量上限，移动高侧不可能更大）。
 * 
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class LC11_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int maxV = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int minH = Math.min(height[left], height[right]);
            int lenB = right - left;
            int v = minH * lenB;
            maxV = Math.max(maxV, v);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxV;
    }
}