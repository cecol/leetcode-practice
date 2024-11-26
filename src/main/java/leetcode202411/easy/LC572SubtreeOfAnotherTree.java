package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC572SubtreeOfAnotherTree extends BasicTemplate {
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

    // 初見殺 bj4
    public boolean isSubtree(TreeNode rt, TreeNode srt) {
        if (rt == null && srt == null) return true;
        if (rt == null || srt == null) return false;

        if (iden(rt, srt)) return true;
        return isSubtree(rt.left, srt) || isSubtree(rt.right, srt);
    }

    boolean iden(TreeNode rt, TreeNode srt) {
        if (rt == null && srt == null) return true;
        if (rt == null || srt == null) return false;
        if (rt.val != srt.val) return false;
        return iden(rt.left, srt.left) && iden(rt.right, srt.right);
    }
}
