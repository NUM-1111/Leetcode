package com.leetcode.solutions;

import java.util.*;

/**
 * 207-课程表
 * 核心知识点：有向图检测环 / 拓扑排序（BFS Kahn 算法）
 * 时间复杂度：O(V + E)，其中 V = numCourses，E = prerequisites.length
 * 空间复杂度：O(V + E)，邻接表 + 入度数组 + 队列
 *
 * <p>问题建模：课程依赖关系 = 有向图，a 依赖 b 表示有向边 b → a
 * <p>能学完所有课 ⇔ 有向图中不存在环 ⇔ 拓扑排序能覆盖所有节点
 */
public class LC207_CourseSchedule {

    /**
     * 判断是否能完成所有课程的学习
     *
     * @param numCourses    课程总数（节点数），课程编号 0 ~ numCourses-1
     * @param prerequisites 依赖关系数组，prerequisites[i] = [a, b] 表示学 a 前必须先学 b（即 b → a）
     * @return 能完成所有课程返回 true，否则（存在环）返回 false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 边界：没有依赖关系，一定能完成
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        // 1. 构建邻接表 + 入度数组
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];

        for (int[] edge : prerequisites) {
            int course = edge[0];   // a
            int prerequisite = edge[1]; // b，b → a
            graph.get(prerequisite).add(course);
            indegree[course]++;
        }

        // 2. 将所有入度为 0 的节点入队（不需要先修课的课）
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 3. BFS 拓扑排序
        int completed = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            completed++;

            for (int nextCourse : graph.get(course)) {
                indegree[nextCourse]--;
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // 4. 如果所有课程都完成了拓扑排序，说明无环
        return completed == numCourses;
    }

    public static void main(String[] args) {
        var solution = new LC207_CourseSchedule();

        // 测试 1：无环，可以完成
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        boolean result1 = solution.canFinish(numCourses1, prerequisites1);
        System.out.println(result1
                ? "Test 1 passed" : "Test 1 failed: expected true but got false");

        // 测试 2：有环，不能完成
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        boolean result2 = solution.canFinish(numCourses2, prerequisites2);
        System.out.println(!result2
                ? "Test 2 passed" : "Test 2 failed: expected false but got true");

        // 测试 3：多条依赖链，无环
        int numCourses3 = 4;
        int[][] prerequisites3 = {{1, 0}, {2, 1}, {3, 2}};
        boolean result3 = solution.canFinish(numCourses3, prerequisites3);
        System.out.println(result3
                ? "Test 3 passed" : "Test 3 failed: expected true but got false");

        // 测试 4：没有先修课限制
        int numCourses4 = 3;
        int[][] prerequisites4 = {};
        boolean result4 = solution.canFinish(numCourses4, prerequisites4);
        System.out.println(result4
                ? "Test 4 passed" : "Test 4 failed: expected true but got false");

        // 测试 5：多个独立链，无环
        int numCourses5 = 5;
        int[][] prerequisites5 = {{1, 0}, {3, 2}, {4, 3}};
        boolean result5 = solution.canFinish(numCourses5, prerequisites5);
        System.out.println(result5
                ? "Test 5 passed" : "Test 5 failed: expected true but got false");
    }
}