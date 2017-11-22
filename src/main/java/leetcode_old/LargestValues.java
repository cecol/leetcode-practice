package leetcode_old;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class LargestValues {
    public static void main(String[] args) {
    }

    public static List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new LinkedList<Integer>();
        if(root == null) return list;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        fillByMaxValue(0, root, map);
        int[] result = new int[map.size()];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result[entry.getKey()] = entry.getValue();
        }
        for (int i : result) {
            list.add(i);
        }
        return list;
    }

    public static void fillByMaxValue(Integer level, TreeNode node, HashMap<Integer, Integer> map) {
        Integer origin = map.get(level);
        if (origin == null || node.val > origin) map.put(level, node.val);
        if (node.left != null) fillByMaxValue(level + 1, node.left, map);
        if (node.right != null) fillByMaxValue(level + 1, node.right, map);
    }
}
