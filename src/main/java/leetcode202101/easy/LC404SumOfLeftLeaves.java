package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class LC404SumOfLeftLeaves extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC404SumOfLeftLeaves();
    var s = LC.sumOfLeftLeaves(null);
  }

  public int sumOfLeftLeaves(TreeNode root) {
    if (root == null) return 0;
    if (root.right == null && root.left == null) return root.val;
    return sumOfLeftLeavesL(root.left, true) + sumOfLeftLeavesL(root.right, false);
  }

  public int sumOfLeftLeavesL(TreeNode root, boolean isl) {
    if (root == null) return 0;
    if (root.right == null && root.left == null && isl) return root.val;
    return sumOfLeftLeavesL(root.left, true) + sumOfLeftLeavesL(root.right, false);
  }
}
