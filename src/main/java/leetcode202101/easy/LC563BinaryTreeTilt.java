package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

public class LC563BinaryTreeTilt extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC563BinaryTreeTilt();
    var s = LC.findTilt(null);
  }

  int result = 0;

  public int findTilt(TreeNode root) {
    if (root == null) return 0;
    postorder(root);
    return result;
  }

  private int postorder(TreeNode n) {
    if (n == null) return 0;
    int l = postorder(n.left);
    int r = postorder(n.right);
    result += Math.abs(l - r);
    return l + r + n.val;
  }

}
