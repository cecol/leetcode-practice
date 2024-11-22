package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC543DiameterOfBinaryTree extends BasicTemplate {
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

    // 很直觀就想起來並解完
    // 關鍵 dfs 遞迴, 看左右兒子 + global result 記載最大結果就好
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max - 1;
    }

    int dfs(TreeNode rt) {
        if (rt == null) return 0;
        int ll = dfs(rt.left);
        int rr = dfs(rt.right);
        max = Math.max(max, ll + rr + 1);
        return Math.max(ll, rr) + 1;
    }

}
