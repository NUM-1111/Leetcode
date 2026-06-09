package com.leetcode.common;

/**
 * 二叉树节点定义（LeetCode 标准定义）
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 层序遍历构建二叉树（LeetCode 风格，null 表示空节点）
     */
    public static TreeNode of(Integer... values) {
        if (values == null || values.length == 0) return null;
        TreeNode root = new TreeNode(values[0]);
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        int i = 1;
        while (i < values.length) {
            TreeNode cur = queue.poll();
            if (values[i] != null) {
                cur.left = new TreeNode(values[i]);
                queue.offer(cur.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                cur.right = new TreeNode(values[i]);
                queue.offer(cur.right);
            }
            i++;
        }
        return root;
    }
}