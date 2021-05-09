package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC1026MaximumDifferenceBetweenNodeAndAncestor extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1026MaximumDifferenceBetweenNodeAndAncestor();
    }

    /**
     * 很直觀的遞迴解掉了
     * Runtime: 0 ms, faster than 100.00%
     * 這類題目應該之後都不成問題, 有更精簡解法, 概念也差不多
     * */
    int res = 0;
    public int maxAncestorDiff(TreeNode root) {
        if(root == null) return 0;
        int[] l = minCh(root.left);
        int[] r = minCh(root.right);
        if(l != null) {
            res = Math.max(res, Math.abs(root.val - l[0]));
            res = Math.max(res, Math.abs(root.val - l[1]));
        }
        if(r != null) {
            res = Math.max(res, Math.abs(root.val - r[0]));
            res = Math.max(res, Math.abs(root.val - r[1]));
        }
        return res;
    }

    private int[] minCh(TreeNode root) {
        if(root == null) return null;
        int[] l = minCh(root.left);
        int[] r = minCh(root.right);
        int mx = root.val;
        int mn = root.val;
        if(l != null) {
            mx = Math.max(mx,l[1]);
            mn = Math.min(mn,l[0]);
            res = Math.max(res, Math.abs(root.val - l[0]));
            res = Math.max(res, Math.abs(root.val - l[1]));
        }
        if(r != null) {
            mx = Math.max(mx,r[1]);
            mn = Math.min(mn,r[0]);
            res = Math.max(res, Math.abs(root.val - r[0]));
            res = Math.max(res, Math.abs(root.val - r[1]));
        }
        return new int[]{mn,mx};
    }
}
