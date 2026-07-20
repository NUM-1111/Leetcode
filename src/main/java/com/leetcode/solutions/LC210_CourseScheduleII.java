package com.leetcode.solutions;

import java.util.*;

/**
 * 210-课程表 II
 * 核心知识点：拓扑排序（BFS Kahn 算法），输出拓扑序列
 * 时间复杂度：O(V + E)，其中 V = numCourses，E = prerequisites.length
 * 空间复杂度：O(V + E)，邻接表 + 入度数组 + 队列 + 结果数组
 *
 * <p>与 LC207 的区别：207 返回 boolean 判环，210 返回 int[] 拓扑序列
 * <p>核心改动：节点出队时记录到结果数组，最后 completed==numCourses 返回结果，否则返回空数组
 */
public class LC210_CourseScheduleII {

    /**
     * 返回一个可行的课程学习顺序（拓扑序列）
     *
     * @param numCourses    课程总数（节点数），课程编号 0 ~ numCourses-1
     * @param prerequisites 依赖关系数组，prerequisites[i] = [a, b] 表示学 a 前必须先学 b（即 b → a）
     * @return 可行的学习顺序数组，如果存在环则返回空数组
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] order = new int[numCourses];

        // 边界：没有依赖关系，返回任意顺序即可
        if (prerequisites == null || prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                order[i] = i;
            }
            return order;
        }

        // 1. 构建邻接表 + 入度数组
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];

        for (int[] edge : prerequisites) {
            int course = edge[0];
            int prerequisite = edge[1];
            graph.get(prerequisite).add(course);
            indegree[course]++;
        }

        // 2. 将所有入度为 0 的节点入队
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 3. BFS 拓扑排序，出队时记录顺序
        int completed = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            order[completed] = course;
            completed++;

            for (int nextCourse : graph.get(course)) {
                indegree[nextCourse]--;
                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        // 4. 如果所有课程都完成了拓扑排序，返回顺序；否则有环，返回空数组
        return completed == numCourses ? order : new int[0];
    }

    public static void main(String[] args) {
        var solution = new LC210_CourseScheduleII();

        // 测试 1：简单链，无环
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        int[] result1 = solution.findOrder(numCourses1, prerequisites1);
        System.out.println(result1.length == 2 && result1[0] == 0 && result1[1] == 1
                ? "Test 1 passed" : "Test 1 failed: got " + Arrays.toString(result1));

        // 测试 2：有环，返回空数组
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        int[] result2 = solution.findOrder(numCourses2, prerequisites2);
        System.out.println(result2.length == 0
                ? "Test 2 passed" : "Test 2 failed: expect empty, got " + Arrays.toString(result2));

        // 测试 3：多条依赖链，无环
        int numCourses3 = 4;
        int[][] prerequisites3 = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] result3 = solution.findOrder(numCourses3, prerequisites3);
        // 可能的有效顺序：[0,1,2,3] 或 [0,2,1,3]
        System.out.println(result3.length == 4
                ? "Test 3 passed" : "Test 3 failed: expect length 4, got " + Arrays.toString(result3));

        // 测试 4：没有先修课限制
        int numCourses4 = 3;
        int[][] prerequisites4 = {};
        int[] result4 = solution.findOrder(numCourses4, prerequisites4);
        boolean test4Passed = result4.length == 3;
        for (int i = 0; i < result4.length && test4Passed; i++) {
            if (result4[i] != i) test4Passed = false;
        }
        System.out.println(test4Passed
                ? "Test 4 passed" : "Test 4 failed: got " + Arrays.toString(result4));

        // 测试 5：单节点
        int numCourses5 = 1;
        int[][] prerequisites5 = {};
        int[] result5 = solution.findOrder(numCourses5, prerequisites5);
        System.out.println(result5.length == 1 && result5[0] == 0
                ? "Test 5 passed" : "Test 5 failed: got " + Arrays.toString(result5));
    }
}