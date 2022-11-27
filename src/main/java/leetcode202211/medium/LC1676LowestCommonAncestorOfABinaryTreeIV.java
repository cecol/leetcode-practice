package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC1676LowestCommonAncestorOfABinaryTreeIV extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * 保證找到且有多個 基本上跟 LC236LowestCommonAncestorOfABinaryTree 一模一樣
     * 左右都找到 -> 一定是 rt
     * 左右只有一個找到 -> 一定在 non-null 另一邊
     * */
    public TreeNode lowestCommonAncestor(TreeNode rt, TreeNode[] nodes) {
        if (rt == null) return rt;
        for (TreeNode n : nodes) if (rt == n) return rt;
        TreeNode lf = lowestCommonAncestor(rt.left, nodes);
        TreeNode rf = lowestCommonAncestor(rt.right, nodes);
        return lf == null ? rf : rf == null ? lf : rt;
    }
}
