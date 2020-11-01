package leetcode20200921to20201031.easy;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 * pass
 */
class Solution {
    static class TreeNode {
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

    public int findSecondMinimumValue(TreeNode root) {
        if (root.left != null) {
            int l = root.left.val == root.val? findSecondMinimumValue(root.left) : root.left.val;
            int r = root.right.val == root.val? findSecondMinimumValue(root.right) : root.right.val;
            if(l != -1 && (l <= r || r == -1)) return l;
            if(r != -1) return r;
        }
        return -1;
    }
}
