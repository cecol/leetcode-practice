package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC297SerializeAndDeserializeBinaryTree extends BasicTemplate {
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

    // 秒解
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "X";
        return String.valueOf(root.val) + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>();
        String[] ds = data.split(",");
        for (String s : ds) q.offer(s);
        return getT(q);
    }

    TreeNode getT(Queue<String> q) {
        String c = q.poll();
        if (c.equals("X")) return null;
        TreeNode va = new TreeNode(Integer.valueOf(c));
        va.left = getT(q);
        va.right = getT(q);
        return va;
    }
}
