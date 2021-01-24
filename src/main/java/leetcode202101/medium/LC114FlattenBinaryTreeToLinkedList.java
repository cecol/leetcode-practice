package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC114FlattenBinaryTreeToLinkedList extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC114FlattenBinaryTreeToLinkedList();
        LC.flatten(null);
    }

    public void flatten(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;
        TreeNode left = root.left;
        TreeNode right = root.right;
        flatten(left);
        flatten(right);
        if (root.left != null) {
            root.right = left;
            while (left.right != null) left = left.right;
            left.right = right;
            root.left = null;
        }
    }
}
