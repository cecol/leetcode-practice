package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC998MaximumBinaryTreeII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC998MaximumBinaryTreeII();
        var s = LC.insertIntoMaxTree(null, 0);
    }

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val > root.val) return new TreeNode(val, root, null);
        root.right = insertIntoMaxTree(root.right, val);
        return root;
    }
}
