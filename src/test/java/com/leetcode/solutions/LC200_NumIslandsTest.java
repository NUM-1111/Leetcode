package com.leetcode.solutions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * LC200_NumIslands 测试
 */
class LC200_NumIslandsTest {

    private final LC200_NumIslands solution = new LC200_NumIslands();

    @Test
    void testExample1() {
        char[][] grid = {
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}
        };
        assertEquals(1, solution.numIslands(grid));
    }

    @Test
    void testExample2() {
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        assertEquals(3, solution.numIslands(grid));
    }

    @Test
    void testEmptyGrid() {
        char[][] grid = {};
        assertEquals(0, solution.numIslands(grid));
    }

    @Test
    void testAllWater() {
        char[][] grid = {
            {'0', '0'},
            {'0', '0'}
        };
        assertEquals(0, solution.numIslands(grid));
    }

    @Test
    void testSingleIsland() {
        char[][] grid = {
            {'1'}
        };
        assertEquals(1, solution.numIslands(grid));
    }
}