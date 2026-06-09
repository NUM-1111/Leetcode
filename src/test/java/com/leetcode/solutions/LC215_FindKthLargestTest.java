package com.leetcode.solutions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * LC215_FindKthLargest 测试
 */
class LC215_FindKthLargestTest {

    private final LC215_FindKthLargest solution = new LC215_FindKthLargest();

    @Test
    void testExample1() {
        assertEquals(5, solution.findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
    }

    @Test
    void testExample2() {
        assertEquals(4, solution.findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, solution.findKthLargest(new int[]{1}, 1));
    }

    @Test
    void testDuplicateValues() {
        assertEquals(5, solution.findKthLargest(new int[]{5, 5, 5, 5}, 2));
    }

    @Test
    void testDescendingOrder() {
        assertEquals(3, solution.findKthLargest(new int[]{6, 5, 4, 3, 2, 1}, 4));
    }
}