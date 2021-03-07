package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC1110DeleteNodesAndReturnForest extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1110DeleteNodesAndReturnForest();
    }

    /**
     * 自己有解過, 速度也是最快的
     * 基本思路就是
     * 先用一個 boolean[1001] 記載哪些要背deleted (題目給的node.val範圍 1 - 1000)
     * 1. 一個遞迴func 回傳當前 node 是否要刪除
     * 2. 後序下去遞迴 先看看左右兒子狀況
     * -> 如果左右兒子各自回傳 true 先把當前 node,左右兒子改成null
     * -> 如果當前node 也要被刪, 且左右兒子存在且保留 -> 把左右兒子加進答案
     * -> 如果當前node 要保留, 但已經沒有parent, 自己要加入 -> 如果直接加, 可能會加到重複, 自己要不要加是讓parent決定
     * 因為後序, 所以左右兒子先遞迴完 子子孫孫都處理過了
     * 1. 如果左右兒子要保留 , 要馬就跟著父親保留, 要馬就一位父親不保留所以父親幫他們保留, 只有父親才知道他們是否獨立
     * 2. 如果父親要保留, 就在給祖父決定, 除非沒有祖父 -> root corner case
     * 回傳 boolean[root.val];
     * 有看一下別人答案, 覺得不是很懂QQ
     * */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        boolean[] dd = new boolean[1001];
        for (int t : to_delete) dd[t] = true;
        del(root, dd, res, null);
        return res;
    }

    private boolean del(TreeNode root, boolean[] dd, List<TreeNode> res, TreeNode p) {
        if (root == null) return false;
        boolean l = del(root.left, dd, res, root);
        boolean r = del(root.right, dd, res, root);
        if (l) root.left = null;
        if (r) root.right = null;
        if (dd[root.val]) {
            if (root.left != null && !l) res.add(root.left);
            if (root.right != null && !r) res.add(root.right);
        } else if (p == null) {
            res.add(root);
        }
        return dd[root.val];
    }
}
