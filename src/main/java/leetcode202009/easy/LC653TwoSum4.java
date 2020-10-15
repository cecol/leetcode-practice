package leetcode202009.easy;


import leetcode202009.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC653TwoSum4 extends BasicTemplate {
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

    public static void main(String[] args) {
        var LC = new LC653TwoSum4();
        var s = LC.findTarget(null, 10);
    }

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) return false;
        Map<Integer, Integer> m = new HashMap<>();
        goTree(m, root);
        for (Map.Entry<Integer, Integer> e : m.entrySet()) {
            var rest = k - e.getKey();
            if(e.getKey() == rest && m.get(e.getKey()) > 1) return true;
            else if(e.getKey() == rest) continue;
            else if(m.containsKey(rest)) return true;
            else continue;
        }
        return false;
    }

    public void goTree(Map<Integer, Integer> m, TreeNode n) {
        if (n != null) {
            m.put(n.val, m.getOrDefault(n.val, 0) + 1);
            goTree(m, n.left);
            goTree(m, n.right);
        }
    }

}
