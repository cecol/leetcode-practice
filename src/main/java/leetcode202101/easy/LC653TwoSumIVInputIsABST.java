package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class LC653TwoSumIVInputIsABST extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC653TwoSumIVInputIsABST();
    var s = LC.findTarget(null, 1);
  }

  public boolean findTarget(TreeNode root, int k) {
    Set<Integer> preSum = new HashSet<>();
    return preSumT(root, k, preSum);
  }

  public boolean preSumT(TreeNode root, int target, Set<Integer> preSum) {
    if (root == null) return false;
    if(preSum.contains(target - root.val)) return true;
    preSum.add(root.val);
    return preSumT(root.left, target, preSum) || preSumT(root.right, target, preSum);
  }
}
