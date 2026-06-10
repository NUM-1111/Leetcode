package com.leetcode.solutions;

import com.leetcode.common.TreeNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * LC102_LevelOrder 测试
 */
class LC102_LevelOrderTest {

    private final LC102_LevelOrder solution = new LC102_LevelOrder();

    @Test
    void testExample1() {
        // root = [3,9,20,null,null,15,7]
        TreeNode root = TreeNode.of(3, 9, 20, null, null, 15, 7);
        List<List<Integer>> result = solution.levelOrder(root);

        assertEquals(3, result.size());
        assertArrayEquals(new Integer[]{3}, result.get(0).toArray());
        assertArrayEquals(new Integer[]{9, 20}, result.get(1).toArray());
        assertArrayEquals(new Integer[]{15, 7}, result.get(2).toArray());
    }

    @Test
    void testSingleNode() {
        TreeNode root = TreeNode.of(1);
        List<List<Integer>> result = solution.levelOrder(root);

        assertEquals(1, result.size());
        assertArrayEquals(new Integer[]{1}, result.get(0).toArray());
    }

    @Test
    void testEmptyTree() {
        List<List<Integer>> result = solution.levelOrder(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testLeftSkewed() {
        TreeNode root = TreeNode.of(1, 2, null, 3);
        List<List<Integer>> result = solution.levelOrder(root);

        assertEquals(3, result.size());
        assertArrayEquals(new Integer[]{1}, result.get(0).toArray());
        assertArrayEquals(new Integer[]{2}, result.get(1).toArray());
        assertArrayEquals(new Integer[]{3}, result.get(2).toArray());
    }
}