package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC102BinaryTreeLevelOrderTraversal extends BasicTemplate {
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

    // 直觀兩層 bfs queue 切換就解完了
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);
        while (bfs.size() > 0) {
            LinkedList<TreeNode> bfs2 = new LinkedList<>();
            List<Integer> r = new ArrayList<>();
            while (bfs.size() > 0) {
                TreeNode t = bfs.poll();
                r.add(t.val);
                if(t.left != null) bfs2.add(t.left);
                if(t.right != null) bfs2.add(t.right);
            }
            res.add(r);
            bfs = bfs2;
        }

        return res;
    }
}
