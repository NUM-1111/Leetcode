package com.leetcode.solutions;

/**
 * LC76 - 最小覆盖子串 (Minimum Window Substring)
 *
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
 * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：
 * - 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * - 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：涵盖 "A"、"B"、"C" 的最小子串是 "BANC"
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 *
 * 示例 3：
 * 输入：s = "a", t = "aa"
 * 输出：""
 * 解释：t 中两个字符 'a' 均应包含在 s 的子串中，因此没有符合条件的子串，返回空字符串。
 *
 * 约束：
 * - 1 <= s.length, t.length <= 10^5
 * - s 和 t 由英文字母组成
 *
 * 核心套路：可变滑动窗口 + int[128] 计数数组 + need 欠账计数
 * 时间复杂度: O(n)
 * 空间复杂度: O(1)
 */
public class LC76_MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        // 代码健壮性
        if (s == null || t == null || s.length() <= 0 || t.length() <= 0 || s.length() < t.length())
            return "";

        // 核心代码部分
        String res = "";
        int[] needCount = new int[128];
        int[] count = new int[128];
        int need = t.length();

        for(char c : t.toCharArray()){
            needCount[c]++;
        }

        int left = 0;
        int lengthMin = Integer.MAX_VALUE;
        for (int right = 0; right < s.length(); right++) {
            count[s.charAt(right)]++;
            if (count[s.charAt(right)] <= needCount[s.charAt(right)]) {
                need--;
            }
            if (need == 0) {
                while (count[s.charAt(left)] != needCount[s.charAt(left)]) {
                    count[s.charAt(left)]--;
                    left++;
                }
                //先将当前最小子串进行处理
                int length = right - left + 1;
                if (length < lengthMin) {
                    lengthMin = length;
                    res = s.substring(left, right + 1);
                }
                // 处理当前位置的char，保证破坏掉当前的最小子串
                count[s.charAt(left)]--;
                left++;
                need++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        LC76_MinimumWindowSubstring solution = new LC76_MinimumWindowSubstring();

        // 测试用例 1
        System.out.println("s = \"ADOBECODEBANC\", t = \"ABC\" => \"" +
            solution.minWindow("ADOBECODEBANC", "ABC") + "\"");
        // 预期: "BANC"

        // 测试用例 2
        System.out.println("s = \"a\", t = \"a\" => \"" +
            solution.minWindow("a", "a") + "\"");
        // 预期: "a"

        // 边界用例：t 比 s 长
        System.out.println("s = \"a\", t = \"aa\" => \"" +
            solution.minWindow("a", "aa") + "\"");
        // 预期: ""

        // 边界用例：无匹配
        System.out.println("s = \"a\", t = \"b\" => \"" +
            solution.minWindow("a", "b") + "\"");
        // 预期: ""
    }
}