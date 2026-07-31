package com.leetcode.solutions;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 438 - 找到字符串中所有字母异位词 (Find All Anagrams in a String)
 *
 * 核心知识点：定长滑动窗口 + int[26] 计数数组
 *
 * 思路：
 * 1. 用 int[26] 统计 p 中每个字符的出现次数
 * 2. 滑动窗口遍历 s，维护窗口内字符计数
 * 3. 每当窗口长度 == p.length() 时，比较两个计数数组是否相等
 * 4. 若相等则记录左指针索引，然后左指针右移（移出窗口），继续滑动
 *
 * 时间复杂度：O(n)，n = s.length()
 * 空间复杂度：O(1)
 */
public class LC438_FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        int[] pCount = new int[26];
        int[] window = new int[26];

        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            window[s.charAt(right) - 'a']++;

            if (right - left + 1 == p.length()) {
                if (matches(pCount, window)) {
                    result.add(left);
                }
                window[s.charAt(left) - 'a']--;
                left++;
            }
        }

        return result;
    }

    private boolean matches(int[] pCount, int[] window) {
        for (int i = 0; i < 26; i++) {
            if (pCount[i] != window[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LC438_FindAllAnagramsInAString solution = new LC438_FindAllAnagramsInAString();

        // 测试用例 1
        System.out.println("s = \"cbaebabacd\", p = \"abc\" => " +
            solution.findAnagrams("cbaebabacd", "abc"));
        // 预期: [0, 6]

        // 测试用例 2
        System.out.println("s = \"abab\", p = \"ab\" => " +
            solution.findAnagrams("abab", "ab"));
        // 预期: [0, 1, 2]

        // 边界用例
        System.out.println("s = \"a\", p = \"ab\" => " +
            solution.findAnagrams("a", "ab"));
        // 预期: []

        System.out.println("s = \"abc\", p = \"abc\" => " +
            solution.findAnagrams("abc", "abc"));
        // 预期: [0]
    }
}