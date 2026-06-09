package com.leetcode.solutions;

/**
 * 215. 数组中的第K个最大元素
 * <p>
 * 快速选择（QuickSelect）算法
 * 时间复杂度：平均 O(n)，最坏 O(n²)
 * 空间复杂度：O(log n) 递归栈
 */
public class LC215_FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        // 第 k 个最大元素在排序数组中的索引是 k-1（0-based）
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }

    /**
     * 快速选择
     *
     * @param nums      数组
     * @param left      左边界（含）
     * @param right     右边界（含）
     * @param targetIdx 目标在排序数组中的索引（0-based）
     * @return 第 k 大的元素值
     */
    private int quickSelect(int[] nums, int left, int right, int targetIdx) {
        if (left == right) return nums[left];

        int pivotIdx = partition(nums, left, right);

        if (pivotIdx == targetIdx) {
            return nums[pivotIdx];
        } else if (pivotIdx > targetIdx) {
            // pivot 位置 > 目标索引 → 目标在左区间
            return quickSelect(nums, left, pivotIdx - 1, targetIdx);
        } else {
            // pivot 位置 < 目标索引 → 目标在右区间
            return quickSelect(nums, pivotIdx + 1, right, targetIdx);
        }
    }

    /**
     * 分区：选 nums[left] 为 pivot
     * 比 pivot 大的放左边，比 pivot 小的放右边
     * 返回 pivot 最终位置
     */
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int i = left + 1;  // 从左向右
        int j = right;     // 从右向左

        while (i <= j) {
            // 跳过左边大于 pivot 的元素（它们已在正确位置）
            while (i <= j && nums[i] > pivot) i++;
            // 跳过右边小于 pivot 的元素（它们已在正确位置）
            while (i <= j && nums[j] < pivot) j--;
            if (i <= j) {
                swap(nums, i, j);
                i++;  // i 向右移动
                j--;  // j 向左移动（你之前写成了 j++，这是关键 bug）
            }
        }
        // 将 pivot 放到正确位置
        swap(nums, left, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}