package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC124BinaryTreeMaximumPathSum extends BasicTemplate {
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

        // 秒解
        // 關鍵是 左右子樹下去遞迴, 回傳自己 + 左右最大擇一
        // 左右要跟0比 複數可以不要選, 左右都捨棄
        int res = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            max(root);
            return res;
        }

        int max(TreeNode rt) {
            if (rt == null) return 0;
            int rtL = Math.max(max(rt.left), 0);
            int rtR = Math.max(max(rt.right), 0);
            res = Math.max(res, rt.val + rtL + rtR);
            return Math.max(rtL, rtR) + rt.val;
        }
    }
}
