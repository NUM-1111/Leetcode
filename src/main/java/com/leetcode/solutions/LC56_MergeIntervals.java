package com.leetcode.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC56 - 合并区间 (Merge Intervals)
 *
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠，将它们合并为 [1,6]
 *
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间
 *
 * 约束：
 * - 1 <= intervals.length <= 10^4
 * - intervals[i].length == 2
 * - 0 <= starti <= endi <= 10^4
 *
 * 核心套路：排序 + 区间合并（贪心）
 * 时间复杂度: O(n log n)
 * 空间复杂度: O(log n) 排序栈空间
 */
public class LC56_MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        
        // 按 start 升序排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        List<int[]> result = new ArrayList<>();
        
        int currentStart = intervals[0][0];
        int currentEnd = intervals[0][1];
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= currentEnd) {
                // 重叠：扩展 currentEnd
                currentEnd = Math.max(currentEnd, intervals[i][1]);
            } else {
                // 不重叠：把当前区间加入结果，换到新区间
                result.add(new int[]{currentStart, currentEnd});
                currentStart = intervals[i][0];
                currentEnd = intervals[i][1];
            }
        }
        
        // 把最后一个区间加入结果
        result.add(new int[]{currentStart, currentEnd});
        
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        LC56_MergeIntervals solution = new LC56_MergeIntervals();

        // 测试用例 1
        int[][] result1 = solution.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}});
        System.out.print("[[1,3],[2,6],[8,10],[15,18]] => ");
        print(result1);
        // 预期: [[1,6],[8,10],[15,18]]

        // 测试用例 2
        int[][] result2 = solution.merge(new int[][]{{1,4},{4,5}});
        System.out.print("[[1,4],[4,5]] => ");
        print(result2);
        // 预期: [[1,5]]

        // 边界用例：只有一个区间
        int[][] result3 = solution.merge(new int[][]{{1,3}});
        System.out.print("[[1,3]] => ");
        print(result3);
        // 预期: [[1,3]]

        // 边界用例：完全覆盖
        int[][] result4 = solution.merge(new int[][]{{1,10},{2,5},{3,6}});
        System.out.print("[[1,10],[2,5],[3,6]] => ");
        print(result4);
        // 预期: [[1,10]]
    }

    private static void print(int[][] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print("[" + arr[i][0] + "," + arr[i][1] + "]");
            if (i < arr.length - 1) System.out.print(",");
        }
        System.out.println("]");
    }
}