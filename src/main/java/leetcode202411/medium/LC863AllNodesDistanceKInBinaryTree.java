package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC863AllNodesDistanceKInBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 沒過
    // 1. 先做 root to target 的路上每個點到 target 距離 in Map
    // 1-1. root 往左子樹遞迴 -> 如果回來有找到, root to target 距離 = root.left 的距離 + 1
    // 1-2. 反之就是 root.right 去找
    // dfs 帶當前 len, k 下去
    // dfs left child or right child = len + 1
    // if cur in toT map len 要 rest
    // if len == k 就是答案
    Map<TreeNode, Integer> toT = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        find(root, target);
        dfs(res, k, toT.get(root), root);
        return res;
    }

    void find(TreeNode rt, TreeNode t) {
        if (rt == null) return;
        if (rt == t) {
            toT.put(rt, 0);
        }
        find(rt.left, t);
        if (toT.containsKey(rt.left)) {
            toT.put(rt, toT.get(rt.left) + 1);
            return;
        }
        find(rt.right, t);
        if (toT.containsKey(rt.right)) {
            toT.put(rt, toT.get(rt.right) + 1);
        }
    }

    void dfs(List<Integer> res, int k, int len, TreeNode cur) {
        if (cur == null) return;
        if (toT.containsKey(cur)) len = toT.get(cur);
        if (len == k) res.add(cur.val);
        dfs(res, k, len + 1, cur.left);
        dfs(res, k, len + 1, cur.right);
    }
}
