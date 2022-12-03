package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Stack;

public class LC2313MinimumFlipsInBinaryTreeToGetResult extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2313MinimumFlipsInBinaryTreeToGetResult();
    }

    public int minimumFlips(TreeNode root, boolean result) {
        int[] r = dfs(root);
        return result ? r[1] : r[0];
    }

    int[] dfs(TreeNode rt) {
        if (rt == null) return new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        if (rt.left == null && rt.right == null) return new int[]{rt.val == 0 ? 0 : 1, rt.val == 1 ? 0 : 1};
        int[] l = dfs(rt.left);
        int[] r = dfs(rt.right);
        if (rt.val == 2) {
            return new int[]{l[0] + r[0], Math.min(l[1] + r[1], Math.min(l[0] + r[1], l[1] + r[0]))};
        } else if (rt.val == 3) {
            return new int[]{Math.min(l[0] + r[0], Math.min(l[0] + r[1], l[1] + r[0])), l[1] + r[1]};
        } else if (rt.val == 4) {
            return new int[]{Math.min(l[0] + r[0], l[1] + r[1]), Math.min(l[1] + r[0], l[0] + r[1])};
        }
        return new int[]{Math.min(l[1], r[1]), Math.min(r[0], l[0])};
    }
}
