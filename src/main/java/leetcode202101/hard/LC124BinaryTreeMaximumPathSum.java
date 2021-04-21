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
     * 我原本以為我解的出來, 但我卡死在因為有負值, 一直算錯負值情境
     * 後來還是參考答案才恍然大悟
     * left如果回傳的單邊子子樹也小於0 不如不要
     * right也同理
     * 然後其他邏輯就跟687很像了
     * maxP 只回傳以自己這個點配上某一邊子樹最大值
     *
     * 特別的是
     * 1. if (root == null) return 0;  -> 在空子樹下面, 基本上就直接跳過不多做計算
     * 2. 用 global res 來計算max value, 而非是回傳 int[]{include root, no to include root}
     * -> 後續比較的地方Math.max 可以用 0, 的語意是捨棄那個branch, 只取 root
     * -> 所以回傳的子樹都會先跟0 比較: int l = Math.max(0, maxP(root.left));
     * -> 然後後續的計算 Math.max(s, l + r + root.val); Math.max(l, r) + root.val; 都一定包到root.val, 所以就算是負值也可以好好處理
     * -> 原本我的想法是 回傳 int[] 會更難處理, 因為要處理 left, right 要不要選 + null case
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
