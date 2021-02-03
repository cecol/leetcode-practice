package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC113PathSumII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC113PathSumII();
        var s = LC.pathSum(null, 0);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        findSum(root, targetSum, res, cur);
        return res;
    }

    private void findSum(TreeNode n, int t, List<List<Integer>> res, List<Integer> cur) {
        if (n == null) return;
        cur.add(n.val);
        if (n.left == null && n.right == null && t == n.val) {
            res.add(new ArrayList<>(cur));
            cur.remove(cur.size() - 1);
            return;
        }
        findSum(n.left, t - n.val, res, cur);
        findSum(n.right, t - n.val, res, cur);
        cur.remove(cur.size() - 1);
    }
}
