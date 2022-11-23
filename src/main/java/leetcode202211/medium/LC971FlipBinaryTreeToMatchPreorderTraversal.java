package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC971FlipBinaryTreeToMatchPreorderTraversal extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }


    /**
     * https://leetcode.com/problems/flip-binary-tree-to-match-preorder-traversal/discuss/214216/JavaC%2B%2BPython-DFS-Solution
     * 我有想到基本概念, 就是從 root 一直往下 preorder 檢查, 要 flip 就把 parent.val 加入
     * 然後走 left, right 看看回傳的 boolean 是否可以達成
     * 但我卡在不知道怎把 剩下的 int[] voyage 傳給下層
     * 結果用一個 global i 就足夠了...
     * 因為 left 走完, i 就是剛好給 right
     * */
    List<Integer> res = new ArrayList<>();
    int i = 0;

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        return flip(root, voyage) ? res : Arrays.asList(-1);
    }

    boolean flip(TreeNode rt, int[] v) {
        if (rt == null) return true;
        if (rt.val != v[i++]) return false;
        if (rt.left != null && rt.left.val != v[i]) {
            res.add(rt.val);
            return flip(rt.right, v) && flip(rt.left, v);
        }
        return flip(rt.left, v) && flip(rt.right, v);
    }
}
