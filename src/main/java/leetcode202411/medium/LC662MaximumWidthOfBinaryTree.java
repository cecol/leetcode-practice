package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC662MaximumWidthOfBinaryTree extends BasicTemplate {
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

    // 沒想起來 dfs
    // 1. 下去左右子樹針對現在的 idx*2, idx*2+1 & level 下去找
    // 2. 遞迴優先走左樹, 最左的一定因為 先撞到 leftMost.size() == level
    // 3. 當前的 idx - 當前 level 最左 leftMost.get(level) 來找最大值
    int max = 0;

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        dfs(root, 0, 1, new ArrayList<>());
        return max;
    }

    void dfs(TreeNode n, int level, int idx, List<Integer> leftMost) {
        if (n == null) return;
        if (leftMost.size() == level) leftMost.add(idx);
        max = Math.max(max, idx + 1 - leftMost.get(level));
        dfs(n.left, level + 1, idx * 2, leftMost);
        dfs(n.right, level + 1, idx * 2 + 1, leftMost);
    }
}
