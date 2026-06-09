package com.leetcode.solutions;

import com.leetcode.common.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 * <p>
 * 给定一个二叉树，找到该树中两个指定节点的最近公共祖先。
 * <p>
 * ========== 解法一（推荐）：后序递归 ==========
 * 思路：从叶子向上走，看左右子树中能否找到 p 或 q。
 * 1. 如果当前节点是 null 或 p 或 q → 直接返回
 * 2. 递归搜索左子树和右子树
 * 3. 如果左右都找到了 → 当前节点就是 LCA（p 和 q 分居两侧）
 *    如果只在左边找到 → LCA 在左边
 *    如果只在右边找到 → LCA 在右边
 * 时间复杂度：O(n)  空间复杂度：O(n) 递归栈
 * <p>
 * ========== 解法二：路径比较法 ==========
 * 思路：先找到 root → p 的路径，再找到 root → q 的路径，
 *       然后从头到尾比较两条路径，最后一个相同的节点就是 LCA。
 * <p>
 * 伪代码：
 * List<TreeNode> pathP = new ArrayList<>();
 * List<TreeNode> pathQ = new ArrayList<>();
 * findPath(root, p, pathP);
 * findPath(root, q, pathQ);
 * // 从头比较两条路径，最后一个相同的节点即 LCA
 * int i = 0;
 * while (i < pathP.size() && i < pathQ.size() && pathP.get(i) == pathQ.get(i)) i++;
 * return pathP.get(i - 1);
 * <p>
 * 其中 findPath 用 DFS 回溯实现：
 * 1. 先加入当前节点到路径
 * 2. 如果当前节点是 target → 返回 true
 * 3. 递归左右子树
 * 4. 如果左右子树都没找到 → 移除当前节点（回溯），返回 false
 * 时间复杂度：O(n)  空间复杂度：O(n) 两条路径
 */
public class LC236_LowestCommonAncestor {

    /** 解法一：后序递归 */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 空节点 或 找到了 p 或 q → 直接返回
        if (root == null || root == p || root == q) {
            return root;
        }

        // 去左右子树中找 p 和 q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 左右都找到了 → p 和 q 分居 root 两侧 → root 就是 LCA
        if (left != null && right != null) {
            return root;
        }

        // 哪边找到了就返回哪边，都没找到返回 null
        return left != null ? left : right;
    }
}