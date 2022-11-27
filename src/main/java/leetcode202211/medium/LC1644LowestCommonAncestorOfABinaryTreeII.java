package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC1644LowestCommonAncestorOfABinaryTreeII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/discuss/933835/Java.-Difference-from-236-is-you-need-to-search-the-entire-tree.
     * 這題跟 LC236LowestCommonAncestorOfABinaryTree 幾乎一樣  只是有個前提不一樣
     * It is NOT guaranteed that both p and q are in the tree.
     * 236 可以直接遞迴 left/right, 一找到就回傳, 然後根據回傳決定 LCA, 前提是一定找得到
     * 這題是不一定找得到
     * 1. 要有兩個 boolean 紀錄目前是否真的找到
     * 2. 樹要走完, 走完後才決定是否回傳, 如果當前找到 rt == p || rt == q 就是標記找到且回傳
     * 不然就是回傳子樹狀況
     * 3. 最後根據標記決定目前找到的 lca 是否正確
     * */
    boolean qf = false, pf = false;

    public TreeNode lowestCommonAncestor(TreeNode rt, TreeNode p, TreeNode q) {
        TreeNode lca = LCA(rt, p, q);
        return qf && pf ? lca : null;
    }

    TreeNode LCA(TreeNode rt, TreeNode p, TreeNode q) {
        if (rt == null) return rt;
        TreeNode lf = LCA(rt.left, p, q);
        TreeNode rf = LCA(rt.right, p, q);
        if (rt == p) {
            pf = true;
            return rt;
        }
        if (rt == q) {
            qf = true;
            return rt;
        }
        return lf == null ? rf : rf == null ? lf : rt;
    }
}
