package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.Employee;
import util.TreeNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC226InvertBinaryTree extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC226InvertBinaryTree();
        var s = LC.invertTree(null);
    }

    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode il = invertTree(root.left);
        TreeNode ir = invertTree(root.right);
        root.left = ir;
        root.right = il;
        return root;
    }
}
