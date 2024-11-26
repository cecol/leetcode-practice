package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;


public class LC101SymmetricTree extends BasicTemplate {
    public static void main(String[] args) {
    }

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

    // 初見殺, 想了一下, 但推演過就很順可以找到遞迴公式
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return sym(root.left, root.right);
    }

    boolean sym(TreeNode a, TreeNode b) {
        if(a == null && b == null) return true;
        if(a == null || b == null) return false;
        if(a.val != b.val) return false;
        return sym(a.left, b.right) && sym(a.right, b.left);
    }
}
