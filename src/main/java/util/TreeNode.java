package util;

import java.util.ArrayList;
import java.util.List;

/**
 * for LC 337 House Robber III - https://leetcode.com/problems/house-robber-iii/
 * */
public class TreeNode {
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
        if(left == null) o.add(null);
        else o.addAll(left.preOrder());
        if(left == null) o.add(null);
        else o.addAll(left.preOrder());
        return o;
    }
}

