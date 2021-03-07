package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC979DistributeCoinsInBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC979DistributeCoinsInBinaryTree();
    }

    int res = 0;

    /**
     * https://leetcode.com/problems/distribute-coins-in-binary-tree/discuss/256136/Java-Recursion-REALLY-easy-to-understand!!
     * 這題我有想到得多做
     * 1. global res 去加總總共的move
     * 2. 另一個遞迴的func去回傳自己還有包含左右子樹的加總值
     * 3. 得後序處理, 就是說先看完左右子樹 在看當前root
     * 直到看到這個答案才發現其實這類題目都可以拆的這麼完整
     * 每個人都回傳
     * 1. 包含自己總共幾個node
     * 2. 包含自己總共多少 coin
     * 我覺得我一開始沒解出來是因為, 我以為一次移動可以移n個硬幣
     * 但事實上就是一個移動, 是移一個硬幣 + 一步 -> 關鍵, 不能一次移多格or多個硬幣 -> 所以是先從leave層一直往上distribute
     * 因為是後序, 子樹都平衡了, 多的再上貢獻 or 少的得往parent拿
     * 當該node 看左右子樹個別的共幾個node + 總共多少 coin 的差值就是要在移動的距離
     * 然後再回傳下去
     */
    public int distributeCoins(TreeNode root) {
        distribute(root);
        return res;
    }

    public int[] distribute(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] l = distribute(root.left);
        int[] r = distribute(root.right);
        res += Math.abs(l[1] - l[0]) + Math.abs(r[1] - r[0]);
        return new int[]{l[0] + r[0] + 1, l[1] + r[1] + root.val};
    }
}
