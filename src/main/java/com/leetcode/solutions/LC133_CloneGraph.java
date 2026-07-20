package com.leetcode.solutions;

import com.leetcode.common.Node;
import java.util.*;

/**
 * 133-克隆图
 * 核心知识点：BFS/DFS 遍历图 + HashMap 记录原节点→克隆节点的映射
 * 时间复杂度：O(N + E)，N 为节点数，E 为边数，每个节点/边只访问一次
 * 空间复杂度：O(N)，HashMap + 队列/递归栈
 *
 * <p>关键点：每次出队一个原节点时，确保它的所有邻居都已经被克隆（至少创建了空壳），
 * 然后统一连边。HashMap 同时承担"去重"和"映射"两个职责。
 */
public class LC133_CloneGraph {

    /**
     * BFS 克隆图
     *
     * @param node 原图中的任意节点引用（连通图保证从该节点可达所有节点）
     * @return 克隆图中的对应节点引用
     */
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // HashMap 同时承担两个角色：
        // 1. 记录"原节点 → 克隆节点"的映射
        // 2. 作为 visited 集合，防止重复克隆
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        // 克隆起点并入队
        map.put(node, new Node(node.val));
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            Node clonedCur = map.get(curNode); // 一定存在，因为入队前已放入 map

            for (Node neighbor : curNode.neighbors) {
                Node clonedNeighbor = map.get(neighbor);
                if (clonedNeighbor == null) {
                    // 邻居尚未被克隆 — 创建并放入 map，入队等待处理
                    clonedNeighbor = new Node(neighbor.val);
                    map.put(neighbor, clonedNeighbor);
                    queue.offer(neighbor);
                }
                // 无论邻居是否刚克隆，都要建立边
                clonedCur.neighbors.add(clonedNeighbor);
            }
        }

        return map.get(node);
    }

    // ==================== DFS 递归写法（备选） ====================

    /**
     * DFS 递归克隆图
     */
    public Node cloneGraphDFS(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }

        Node cloned = new Node(node.val);
        map.put(node, cloned);

        for (Node neighbor : node.neighbors) {
            cloned.neighbors.add(dfs(neighbor, map));
        }

        return cloned;
    }

    // ==================== 测试用例 ====================

    public static void main(String[] args) {
        var solution = new LC133_CloneGraph();

        // 测试 1：两个节点互相连接 [[2],[1]]
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        n1.neighbors.add(n2);
        n2.neighbors.add(n1);
        Node cloned1 = solution.cloneGraph(n1);
        boolean test1Passed = cloned1.val == 1
                && cloned1.neighbors.size() == 1
                && cloned1.neighbors.get(0).val == 2
                && cloned1.neighbors.get(0).neighbors.get(0) == cloned1
                && cloned1 != n1;
        System.out.println(test1Passed
                ? "Test 1 passed" : "Test 1 failed: 双向边或值不对");

        // 测试 2：单节点无邻居
        Node single = new Node(1);
        Node cloned2 = solution.cloneGraph(single);
        boolean test2Passed = cloned2.val == 1
                && cloned2.neighbors.isEmpty()
                && cloned2 != single;
        System.out.println(test2Passed
                ? "Test 2 passed" : "Test 2 failed: 单节点克隆");

        // 测试 3：空图
        Node result3 = solution.cloneGraph(null);
        System.out.println(result3 == null
                ? "Test 3 passed" : "Test 3 failed: null 应返回 null");

        // 测试 4：三角形图 1-2-3
        Node t1 = new Node(1);
        Node t2 = new Node(2);
        Node t3 = new Node(3);
        t1.neighbors.add(t2); t1.neighbors.add(t3);
        t2.neighbors.add(t1); t2.neighbors.add(t3);
        t3.neighbors.add(t1); t3.neighbors.add(t2);
        Node cloned4 = solution.cloneGraph(t1);
        boolean test4Passed = cloned4.val == 1
                && cloned4.neighbors.size() == 2
                && cloned4 != t1
                && cloned4.neighbors.get(0) != cloned4.neighbors.get(1); // 两个不同的邻居
        System.out.println(test4Passed
                ? "Test 4 passed" : "Test 4 failed: 三角图克隆");
    }
}