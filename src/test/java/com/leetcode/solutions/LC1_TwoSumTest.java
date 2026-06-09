package com.leetcode.solutions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * LC1_TwoSum 测试
 */
class LC1_TwoSumTest {

    private final LC1_TwoSum solution = new LC1_TwoSum();

    @Test
    void testExample1() {
        int[] result = solution.twoSum(new int[]{2, 7, 11, 15}, 9);
        assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    void testExample2() {
        int[] result = solution.twoSum(new int[]{3, 2, 4}, 6);
        assertArrayEquals(new int[]{1, 2}, result);
    }

    @Test
    void testExample3() {
        int[] result = solution.twoSum(new int[]{3, 3}, 6);
        assertArrayEquals(new int[]{0, 1}, result);
    }
}