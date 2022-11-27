package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC549BinaryTreeLongestConsecutiveSequenceII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/solution/
     * 我覺得我原本的思路沒錯啊
     * 一直遞迴下去看子樹回傳的 int[]{ max Consecutive 遞增, max Consecutive 遞減}
     * 然後父點根據子樹回傳的更新他的 int[]{ max Consecutive 遞增, max Consecutive 遞減} 回傳
     * 回傳先看看 max Consecutive 遞增, max Consecutive 遞減 總常有無超過目前最大值
     *
     * 可能我原本解法太法雜有打錯字或者邏輯錯誤沒發現吧
     * */
    int res = 0;

    public int longestConsecutive(TreeNode rt) {
        if (rt == null) return res;
        res = 1;
        lc(rt);
        return res;
    }

    int[] lc(TreeNode rt) {
        if (rt == null) return new int[]{0, 0};
        int inr = 1, dcr = 1;

        if (rt.left != null) {
            int[] left = lc(rt.left);
            if (rt.val == rt.left.val + 1) dcr = left[1] + 1;
            if (rt.val == rt.left.val - 1) inr = left[0] + 1;
        }

        if (rt.right != null) {
            int[] right = lc(rt.right);
            if (rt.val == rt.right.val + 1) dcr = Math.max(right[1] + 1, dcr);
            if (rt.val == rt.right.val - 1) inr = Math.max(right[0] + 1, inr);
        }

        res = Math.max(res, inr + dcr - 1);
        return new int[]{inr, dcr};
    }
}
