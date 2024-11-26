package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC113PathSum2 extends BasicTemplate {
    public static void main(String[] args) {
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 沒過, 有想到 DFS, 但是終止線用錯, 應該是 LEAVE 而非 null, null 因為左右子樹會造成 重複答案
    public List<List<Integer>> pathSum(TreeNode rt, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), rt, targetSum);
        return res;
    }

    void dfs(List<List<Integer>> res, List<Integer> cur, TreeNode rt, int t) {
        if (rt == null) return;
        cur.add(rt.val);
        if (rt.left == null && rt.right == null && t == rt.val) {
            res.add(new ArrayList<>(cur));
            cur.remove(cur.size() - 1);
            return;
        }
        dfs(res, cur, rt.left, t - rt.val);
        dfs(res, cur, rt.right, t - rt.val);
        cur.remove(cur.size() - 1);
    }
}
