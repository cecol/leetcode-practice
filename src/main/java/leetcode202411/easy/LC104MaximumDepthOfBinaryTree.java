package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC104MaximumDepthOfBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 遞迴秒解 bj4
    public int maxDepth(TreeNode rt) {
        if (rt == null) return 0;
        int l = maxDepth(rt.left);
        int r = maxDepth(rt.right);
        return Math.max(l, r) + 1;
    }

}
