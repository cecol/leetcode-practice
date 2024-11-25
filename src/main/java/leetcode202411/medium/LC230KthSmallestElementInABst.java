package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC230KthSmallestElementInABst extends BasicTemplate {
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

    int res = 0;
    int i = 0;

    // 沒過, 有想起是 Inorder, 但沒想起 inorder 途中 counter++ 就找到了
    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return res;
    }

    void inorder(TreeNode rt, int k) {
        if (rt == null) return;
        inorder(rt.left, k);
        i++;
        if (i == k) res = rt.val;
        inorder(rt.right, k);
    }

}
