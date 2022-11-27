package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC156BinaryTreeUpsideDown extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    public TreeNode upsideDownBinaryTree(TreeNode rt) {
        if (rt == null || rt.left == null) return rt;
        return udb(rt.left, rt);
    }

    TreeNode udb(TreeNode rt, TreeNode p) {
        if (rt == null) return rt;
        TreeNode rrt = udb(rt.left, rt);
        rt.right = p;
        rt.left = p.right;
        p.right = null;
        p.left = null;
        return rrt == null ? rt : rrt;
    }
}
