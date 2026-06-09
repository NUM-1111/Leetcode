package com.leetcode.common;

/**
 * 链表节点定义（LeetCode 标准定义）
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    /**
     * 从数组构建链表
     */
    public static ListNode of(int... values) {
        if (values == null || values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode cur = head;
        for (int i = 1; i < values.length; i++) {
            cur.next = new ListNode(values[i]);
            cur = cur.next;
        }
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode cur = this;
        while (cur != null) {
            sb.append(cur.val);
            if (cur.next != null) sb.append(" -> ");
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }
}