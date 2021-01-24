package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

public class LC101SymmetricTree extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC101SymmetricTree();
        var s = LC.isSymmetric(null);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        return l.val == r.val && isMirror(l.left, r.right) && isMirror(l.right, r.left);
    }
}
