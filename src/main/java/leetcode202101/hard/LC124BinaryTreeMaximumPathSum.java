package leetcode202101.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC124BinaryTreeMaximumPathSum extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC124BinaryTreeMaximumPathSum();
        var s = LC.maxPathSum(null);
    }

    /**
     * https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/39775/Accepted-short-solution-in-Java
     * 其實這題跟687邏輯一樣
     * 我原本以為我姐的出來, 但我卡死在因為有負值, 一直算錯負值情境
     * 後來還是參考答案才恍然大悟
     * left如果回傳的單邊子子樹也小於0 不如不要
     * right也同理
     * 然後其他邏輯就跟687很像了
     * maxP 只回傳以自己這個點配上某一邊子樹最大值
     * */
    int s = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxP(root);
        return s;
    }

    private int maxP(TreeNode root) {
        if (root == null) return 0;
        int l = Math.max(0, maxP(root.left));
        int r = Math.max(0, maxP(root.right));
        s = Math.max(s, l + r + root.val);
        return Math.max(l, r) + root.val;
    }
}
