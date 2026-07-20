package com.leetcode.solutions;

import java.util.HashSet;
import java.util.Set;

/**
 * LC128 - 最长连续序列
 * 
 * 核心知识点：HashSet 边界探测 O(n)
 * 
 * 思路：把所有数放入 HashSet，遍历每个数。
 * 关键优化：只在 num-1 不存在时（即当前数是某连续序列的起点）才开始向后扫描，
 * 避免每个序列被重复计算。
 * 内层 while 不断找 cur+1 直到断裂，更新 maxLen。
 * 
 * 时间复杂度：O(n) — 每个数最多被访问 2 次（外层一次 + 内层 while 一次）
 * 空间复杂度：O(n)
 */
public class LC128_LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int maxLen = 0;
        for (int num : set) {
            if (set.contains(num - 1))
                continue;
            int cur = num;
            int len = 1;

            while (set.contains(cur + 1)) {
                cur++;
                len++;
            }

            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}