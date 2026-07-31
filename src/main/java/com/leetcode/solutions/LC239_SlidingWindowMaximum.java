package com.leetcode.solutions;

import java.util.*;

/**
 * LeetCode 239 - 滑动窗口最大值 (Sliding Window Maximum)
 *
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 *
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *
 * 约束：
 * - 1 <= nums.length <= 10^5
 * - -10^4 <= nums[i] <= 10^4
 * - 1 <= k <= nums.length
 *
 * 核心套路：单调队列（Deque）
 * 时间复杂度: O(n)
 * 空间复杂度: O(k)
 */
public class LC239_SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return new int[0];

        int[] result = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // ① 清理队尾：比当前小的永远没机会了
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                deque.removeLast();
            }

            // ② 当前下标入队
            deque.offerLast(i);

            // ③ 队首过期则弹出
            if (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // ④ 窗口满 → 取队首为最大值
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LC239_SlidingWindowMaximum solution = new LC239_SlidingWindowMaximum();

        System.out.println("nums = [1,3,-1,-3,5,3,6,7], k = 3 => " +
            Arrays.toString(solution.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3)));
        // 预期: [3,3,5,5,6,7]

        System.out.println("nums = [1], k = 1 => " +
            Arrays.toString(solution.maxSlidingWindow(new int[]{1}, 1)));
        // 预期: [1]

        System.out.println("nums = [5,4,3,2,1], k = 3 => " +
            Arrays.toString(solution.maxSlidingWindow(new int[]{5,4,3,2,1}, 3)));
        // 预期: [5,4,3]

        System.out.println("nums = [1,2,3,4,5], k = 3 => " +
            Arrays.toString(solution.maxSlidingWindow(new int[]{1,2,3,4,5}, 3)));
        // 预期: [3,4,5]
    }
}