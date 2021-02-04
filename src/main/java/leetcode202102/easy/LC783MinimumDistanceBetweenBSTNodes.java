package leetcode202102.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC783MinimumDistanceBetweenBSTNodes extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC783MinimumDistanceBetweenBSTNodes();
    var s = LC.minDiffInBST(null);
  }

  int min = -1;
  int pre = -1;

  public int minDiffInBST(TreeNode root) {
    inorderCheck(root);
    return min;
  }

  private void inorderCheck(TreeNode n) {
    if (n == null) return;
    inorderCheck(n.left);
    if (pre != -1) {
      if (min == -1) {
        min = n.val - pre;
      } else {
        min = Math.min(min, n.val - pre);
      }
    }
    pre = n.val;
    inorderCheck(n.right);
  }
}
