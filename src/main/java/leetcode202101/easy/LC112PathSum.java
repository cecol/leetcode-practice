package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

public class LC112PathSum extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC112PathSum();
        var s = LC.hasPathSum(null, 0);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.val != targetSum && root.left == null && root.right == null) return false;
        if (root.val == targetSum && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }
}
