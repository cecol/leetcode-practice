package leetcode_old;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FindDuplicatedSubTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(1);
        root.left = left;
        root.right = right;
        List<TreeNode> list = findDuplicateSubtrees(root);
        for (TreeNode i : list) System.out.print(i.val + " ");
    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> list = new LinkedList<TreeNode>();
        if (root == null) return list;
        else {
            Map<String, Integer> map = new HashMap<String, Integer>();
            append(map,root,list);
        }
        return list;
    }

    public static String append(Map<String, Integer> map, TreeNode node, List<TreeNode> list) {
        if (node != null) {
            String left;
            String right;
            if (node.left == null) left = "#";
            else left = append(map, node.left, list);
            if (node.right == null) right = "#";
            else right = append(map, node.right, list);
            String k = node.val + "," + left + "," + right;
            Integer count = map.getOrDefault(k, 0);
            if (count == 1) list.add(node);
            map.put(k, count + 1);
            return k;
        } else return "#";
    }
}
