package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

public class LC100SameTree extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC100SameTree();
        var s = LC.isSameTree(null, null);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q != null) return false;
        else if (p != null && q == null) return false;
        else if (p == null && q == null) return true;
        else return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
