package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;

public class LC508MostFrequentSubtreeSum extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC508MostFrequentSubtreeSum();
        var s = LC.findFrequentTreeSum(null);
    }

    int max = 0;

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[0];
        Map<Integer, Integer> m = new HashMap<>();
        ffts(root, m);
        List<Integer> res = new ArrayList<>();
        for (Integer k : m.keySet()) if (m.get(k) == max) res.add(k);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    private int ffts(TreeNode n, Map<Integer, Integer> m) {
        int l = n.left == null ? 0 : ffts(n.left, m);
        int r = n.right == null ? 0 : ffts(n.right, m);
        int v = l + r + n.val;
        m.put(v, m.getOrDefault(v, 0) + 1);
        max = Math.max(max, m.getOrDefault(v, 0));
        return v;
    }
}
