package com.leetcode.solutions;

import com.leetcode.common.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 * <p>
 * 给你二叉树的根节点 root，返回其节点值的层序遍历。
 * （即逐层地，从左到右访问所有节点）。
 * <p>
 * BFS 队列解法
 * 时间复杂度：O(n)
 * 空间复杂度：O(n)
 */
public class LC102_LevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        // Queue 是接口，需要用 LinkedList 实例化
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 当前层的节点数 —— 关键技巧：锁定这一层的边界
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);

                // 左右子树入队，下一层会处理
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            // 将这一层的结果加入最终结果
            res.add(level);
        }

        return res;
    }
}