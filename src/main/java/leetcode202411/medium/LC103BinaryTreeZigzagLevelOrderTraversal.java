package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC103BinaryTreeZigzagLevelOrderTraversal extends BasicTemplate {
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

    // 沒過
    // 真的就是 LEVEL ORDER TRAVERSAL + 判斷哪一層來 reverse
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        List<TreeNode> cur = new ArrayList<>();
        cur.add(root);
        zigzag(res, cur, false);
        return res;
    }

    void zigzag(List<List<Integer>> res, List<TreeNode> cur, boolean r) {
        if(cur.isEmpty()) return;
        List<TreeNode> next = new ArrayList<>();
        List<Integer> cl = new ArrayList<>();
        for(TreeNode n :cur) {
            if(n.left != null) next.add(n.left);
            if(n.right != null) next.add(n.right);
            cl.add(n.val);
        }
        if(r) Collections.reverse(cl);
        res.add(cl);
        zigzag(res, next, !r);
    }
}
