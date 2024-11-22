package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;


public class LC110BalancedBinaryTree extends BasicTemplate {
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


    public boolean isBalanced(TreeNode rt) {
        if(rt == null) return true;
        return isBalanced(rt.left) && isBalanced(rt.right) && Math.abs(h(rt.left) - h(rt.right)) <= 1;
    }

    int h(TreeNode t) {
        if (t == null) return 0;
        return Math.max(h(t.left), h(t.right)) + 1;
    }
}
