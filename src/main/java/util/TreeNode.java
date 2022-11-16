package util;

import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * for LC 337 House Robber III - https://leetcode.com/problems/house-robber-iii/
 */
public class TreeNode extends BasicTemplate {
    public int val;
    public TreeNode left = null;
    public TreeNode right = null;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public List<Integer> preOrder() {
        List<Integer> o = new ArrayList<>();
        o.add(val);
        if (left == null) o.add(null);
        else o.addAll(left.preOrder());
        if (left == null) o.add(null);
        else o.addAll(left.preOrder());
        return o;
    }

    public TreeNode buildTreeByLevel(Integer[] vals, int n) {
        if (n > vals.length || vals[n - 1] == null) return null;
        return new TreeNode(vals[n - 1], buildTreeByLevel(vals, 2 * n), buildTreeByLevel(vals, 2 * n + 1));
    }
}

