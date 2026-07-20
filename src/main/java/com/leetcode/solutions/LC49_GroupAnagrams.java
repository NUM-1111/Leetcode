package com.leetcode.solutions;

import java.util.*;

/**
 * LC49 - 字母异位词分组
 * 
 * 核心知识点：排序后哈希分组
 * 
 * 思路：字母异位词排序后完全相同。
 * 用 HashMap<String, List<String>>，key 为排序后的字符串，value 为该组的词列表。
 * computeIfAbsent 一行完成"key 不存在则创建新 list 并添加"。
 * 
 * 时间复杂度：O(n·k·logk)，n 为字符串数量，k 为最长字符串长度
 * 空间复杂度：O(n·k)
 */
public class LC49_GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String key = new String(c);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }

        return new ArrayList<>(map.values());
    }
}