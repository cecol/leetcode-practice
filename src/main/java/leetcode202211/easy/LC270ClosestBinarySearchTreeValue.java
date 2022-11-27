package leetcode202211.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC270ClosestBinarySearchTreeValue extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC270ClosestBinarySearchTreeValue();
    }

    TreeNode pre = null;
    TreeNode succ = null;

    public int closestValue(TreeNode rt, double t) {
        inorder(rt, t);
        if (succ == null) return pre.val;
        if (pre == null) return succ.val;
        return t - (double) pre.val < (double) succ.val - t ? pre.val : succ.val;
    }

    void inorder(TreeNode rt, double t) {
        if (rt == null) return;
        inorder(rt.left, t);
        if (succ == null && rt.val <= t) pre = rt;
        else if (succ == null && rt.val > t) {
            succ = rt;
            return;
        }
        inorder(rt.right, t);
    }
}
