package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC236LowestCommonAncestorOfABinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC236LowestCommonAncestorOfABinaryTree();
        var s = LC.lowestCommonAncestor(null, null, null);
    }

    /**
     * 自己有解出來, 但想得太複雜, 原本以為跟863類似, 但後來發現思路完全錯誤
     * 把原本想的笨方法刪了
     * 改參考正確路徑
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/discuss/65225/4-lines-C%2B%2BJavaPythonRuby
     *
     * 基本上就是直接左右子樹遞迴下去
     * 如果踩到 null, p, q -> 就回傳當前node
     * 遞迴回來的如果是 null 代表p,q各在一邊, LCA就是當前node
     * 如果有一邊是null -> 另一邊不是 ->代表p,q在當前node的某一邊子樹中 -> 而該邊回傳的也已經遞迴下去達到LCA
     *
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }


}
