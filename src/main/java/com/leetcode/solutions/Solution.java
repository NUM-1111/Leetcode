package com.leetcode.solutions;

/**
 * 解题模板 - 复制此类开始新题目
 * <p>
 * 使用方式：
 * 1. 将本文件作为模板创建新类，命名规则：LC{题号}_{题目名}.java
 * 2. 在对应 test 目录创建测试类
 * 3. 运行 mvn test 验证
 */
public class Solution {

    // TODO: 在此处实现解题方法
    /**
     * 二刷：尝试升A
     */
    public int findKthLargest(int[] nums, int k) {
        return fastSort(nums,k,0,nums.length-1);
    }

    private int fastSort(int[] nums, int k, int left, int right){
        if(left == right) return nums[left];
        int curIndex = vaidition(nums,left,right);

        if(k < curIndex) fastSort(nums,k,left,curIndex-1);
        if(k > curIndex) fastSort(nums,k,curIndex+1,right);
        if(k == curIndex) return nums[k];

        return -1;
    }

    private int vaidition(int[] nums, int left,int right){
        int pivot = nums[left];
        int i = left+1;
        int j = right;
        while(i <= j){
            while(nums[i] > pivot) i++;
            while(nums[i] < pivot) j--;
            if(i <= j){
                swap(nums,i,j);
                i++;
                j--;
            }
        }
        swap(nums,left,j);
        return j;
    }

    private void swap(int[] nums, int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}