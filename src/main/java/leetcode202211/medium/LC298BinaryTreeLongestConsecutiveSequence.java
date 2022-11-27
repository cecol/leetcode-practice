package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC298BinaryTreeLongestConsecutiveSequence extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    int res = 0;

    public int longestConsecutive(TreeNode root) {
        if (root == null) return res;
        res = 1;
        lc(root.left, root, 1);
        lc(root.right, root, 1);
        return res;
    }

    void lc(TreeNode rt, TreeNode p, int pPath) {
        if (rt == null) return;
        int myPath = p.val + 1 == rt.val ? pPath + 1 : 1;
        res = Math.max(myPath, res);
        lc(rt.left, rt, myPath);
        lc(rt.right, rt, myPath);
    }
}
