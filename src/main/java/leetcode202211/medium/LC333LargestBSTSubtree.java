package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC333LargestBSTSubtree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    int res = 0;

    public int largestBSTSubtree(TreeNode rt) {
        if (rt == null) return res;
        res = 1;
        goBST(rt);
        return res;
    }

    int[] goBST(TreeNode rt) {
        if (rt == null) return null;
        int[] lbst = goBST(rt.left);
        int[] rbst = goBST(rt.right);

        if ((lbst != null && lbst[0] == -1) || (rbst != null && rbst[0] == -1)) return new int[]{-1, 0, 0};
        if ((lbst == null || lbst[2] < rt.val) && (rbst == null || rbst[1] > rt.val)) {
            int count = 1 + (lbst == null ? 0 : lbst[0]) + (rbst == null ? 0 : rbst[0]);
            res = Math.max(res, count);
            return new int[]{count, (lbst == null ? rt.val : lbst[1]), (rbst == null ? rt.val : rbst[2])};
        }
        return new int[]{-1, 0, 0};
    }
}
