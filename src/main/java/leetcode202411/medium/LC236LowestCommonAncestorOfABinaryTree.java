package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC236LowestCommonAncestorOfABinaryTree extends BasicTemplate {
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

    // 奇怪? 這題不就跟 LC235LowestCommonAncestorOfABinarySearchTree 一模一樣?
    public TreeNode lowestCommonAncestor(TreeNode rt, TreeNode p, TreeNode q) {
        if(rt == null) return null;
        if(rt == p || rt == q) return rt;
        TreeNode lf = lowestCommonAncestor(rt.left, p ,q);
        TreeNode rf = lowestCommonAncestor(rt.right, p ,q);
        return lf == null ? rf : rf == null ? lf : rt;
    }
}
