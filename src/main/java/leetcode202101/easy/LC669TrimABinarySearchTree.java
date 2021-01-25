package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

public class LC669TrimABinarySearchTree extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC669TrimABinarySearchTree();
        var s = LC.trimBST(null, 0, 0);
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.val < low || root.val > high) {
            TreeNode l = trimBST(root.left, low, high);
            TreeNode r = trimBST(root.right, low, high);
            if(l != null) {
                TreeNode lr = l;
                while (lr.right != null) lr = lr.right;
                lr.right = r;
                return l;
            } else if(r != null) {
                TreeNode rl = r;
                while (rl.left != null) rl = rl.left;
                rl.left = l;
                return r;
            } else return null;
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }
}
