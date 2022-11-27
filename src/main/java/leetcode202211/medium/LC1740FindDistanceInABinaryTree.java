package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC1740FindDistanceInABinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    public int findDistance(TreeNode root, int p, int q) {
        TreeNode lca = LCA(root, p, q);
        return d(lca, 0, p) + d(lca, 0, q);
    }

    int d(TreeNode rt, int d, int p) {
        if (rt == null) return -1;
        if (rt.val == p) return d;
        int lf = d(rt.left, d + 1, p);
        if (lf != -1) return lf;
        return d(rt.right, d + 1, p);
    }

    TreeNode LCA(TreeNode rt, int p, int q) {
        if (rt == null || rt.val == p || rt.val == q) return rt;
        TreeNode lf = LCA(rt.left, p, q);
        TreeNode rf = LCA(rt.right, p, q);
        return lf == null ? rf : rf == null ? lf : rt;
    }
}
