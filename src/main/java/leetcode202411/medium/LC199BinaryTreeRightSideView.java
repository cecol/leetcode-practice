package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC199BinaryTreeRightSideView extends BasicTemplate {
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

    // 秒解, 直觀想到是 level order traversal 就是對了
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);
        while (!bfs.isEmpty()) {
            res.add(bfs.getLast().val);
            LinkedList<TreeNode> nbfs = new LinkedList<>();
            int size = bfs.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = bfs.poll();
                if(n.left != null) nbfs.add(n.left);
                if(n.right != null) nbfs.add(n.right);
            }
            bfs = nbfs;
        }
        return res;
    }
}
