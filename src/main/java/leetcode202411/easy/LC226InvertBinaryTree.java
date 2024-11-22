package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;


public class LC226InvertBinaryTree extends BasicTemplate {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
    }

    public TreeNode invertTree(TreeNode rt) {
        if(rt == null) return rt;
        TreeNode l = invertTree(rt.left);
        TreeNode r = invertTree(rt.right);
        rt.left = r;
        rt.right = l;
        return rt;
    }
}
