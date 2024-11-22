package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC98ValidBinarySearchTree extends BasicTemplate {
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

    // 完全忘記這題巧妙在於 - BST 就是給予左右 range, 左半 右半 遞迴即可
    public boolean isValidBST(TreeNode rt) {
        return ch(rt, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    boolean ch(TreeNode rt, long b, long e) {
        if (rt == null) return true;
        if (rt.val <= b || rt.val >= e) return false;
        return ch(rt.left, b, rt.val) && ch(rt.right, rt.val, e);
    }
}
