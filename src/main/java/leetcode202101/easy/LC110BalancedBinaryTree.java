package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

public class LC110BalancedBinaryTree extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC110BalancedBinaryTree();
    var s = LC.isBalanced(null);
  }


  public boolean isBalanced(TreeNode root) {
    if(root == null) return true;
    int lh = h(root.left);
    int rh = h(root.right);
    return Math.abs(lh-rh) <= 1 && isBalanced(root.left) && isBalanced(root.right);
  }

  private int h(TreeNode n) {
    if (n == null) return 0;
    return 1 + Math.max(h(n.left), h(n.right));
  }
}
