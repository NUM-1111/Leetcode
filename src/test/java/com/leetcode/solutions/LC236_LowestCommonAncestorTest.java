package com.leetcode.solutions;

import com.leetcode.common.TreeNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * LC236_LowestCommonAncestor 测试
 */
class LC236_LowestCommonAncestorTest {

    private final LC236_LowestCommonAncestor solution = new LC236_LowestCommonAncestor();

    @Test
    void testExample1() {
        // root = [3,5,1,6,2,0,8,null,null,7,4], p=5, q=1
        TreeNode root = TreeNode.of(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode p = findNode(root, 5);
        TreeNode q = findNode(root, 1);
        assertEquals(3, solution.lowestCommonAncestor(root, p, q).val);
    }

    @Test
    void testExample2() {
        // root = [3,5,1,6,2,0,8,null,null,7,4], p=5, q=4
        TreeNode root = TreeNode.of(3, 5, 1, 6, 2, 0, 8, null, null, 7, 4);
        TreeNode p = findNode(root, 5);
        TreeNode q = findNode(root, 4);
        assertEquals(5, solution.lowestCommonAncestor(root, p, q).val);
    }

    @Test
    void testExample3() {
        // root = [1,2], p=1, q=2
        TreeNode root = TreeNode.of(1, 2);
        TreeNode p = findNode(root, 1);
        TreeNode q = findNode(root, 2);
        assertEquals(1, solution.lowestCommonAncestor(root, p, q).val);
    }

    @Test
    void testSameNode() {
        // p 和 q 是同一个节点
        TreeNode root = TreeNode.of(3, 5, 1, 6, 2, 0, 8);
        TreeNode p = findNode(root, 5);
        assertEquals(5, solution.lowestCommonAncestor(root, p, p).val);
    }

    /**
     * 在树中按值查找节点（DFS）
     */
    private TreeNode findNode(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        TreeNode left = findNode(root.left, val);
        if (left != null) return left;
        return findNode(root.right, val);
    }
}