package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

public class LC530MinimumAbsoluteDifferenceInBST extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC530MinimumAbsoluteDifferenceInBST();
        var s = LC.getMinimumDifference(null);
    }

    public int getMinimumDifference(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        TreeNode lr = root;
        int lm = Integer.MAX_VALUE;
        if (root.left != null) {
            lr = root.left;
            while (lr.right != null) lr = lr.right;
            lm = Math.abs(root.val - lr.val);
        }
        TreeNode rl = root;
        int rm = Integer.MAX_VALUE;
        if (root.right != null) {
            rl = root.right;
            while (rl.left != null) rl = rl.left;
            rm = Math.abs(root.val - rl.val);
        }
        return Math.min(
                Math.min(lm,rm),
                Math.min(getMinimumDifference(root.right), getMinimumDifference(root.left)));
    }
}
