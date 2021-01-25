package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

public class LC501FindModeInBinarySearchTree extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC501FindModeInBinarySearchTree();
        var s = LC.findMode(null);
    }

    public int[] findMode(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return new int[]{root.val};
        int[] l = findMode(root.left);
        int[] r = findMode(root.right);
        if (l == null && r != null) {

        } else if (l != null && r == null) {

        } else {
            
        }
    }
}
