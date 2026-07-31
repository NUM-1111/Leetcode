package com.leetcode.solutions;

/**
 * LC42 - 接雨水
 *
 * 核心知识点：双指针夹逼
 *
 * 思路：左右指针向中间移动，维护 leftMax 和 rightMax 两个边界高度。
 * 哪边矮就移动哪边——因为较矮的一侧已经能确定当前格能接多少水。
 * 如果当前高度 >= 边界，更新边界；否则接水 = 边界 - 当前高度。
 *
 * 时间复杂度：O(n)
 * 空间复杂度：O(1)
 */
public class LC42_TrappingRainWater {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }
}