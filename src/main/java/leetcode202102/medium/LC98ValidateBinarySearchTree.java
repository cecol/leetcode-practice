package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC98ValidateBinarySearchTree extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC98ValidateBinarySearchTree();
    var s = LC.isValidBST(null);
  }


  /**
   * 我一開始想說用很簡單的遞迴下去檢查, 但其實完全遺漏子子樹也得跟祖父輩比較
   * 原本解法
   * (root.left == null || (root.val > root.left.val && isValidBST(root.left))) &&
   * (root.right == null || (root.val < root.right.val && isValidBST(root.right)))
   * 完全忽略 isValidBST(root.left) 裡面的子子孫其實也要跟root.val 比較
   * <p>
   * 所以應該用range方式來檢查子孫, 父親把自己的range帶下去
   * https://leetcode.com/problems/validate-binary-search-tree/discuss/32109/My-simple-Java-solution-in-3-lines
   */
  public boolean isValidBST(TreeNode root) {
    return checkBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }

  public boolean checkBST(TreeNode n, long b, long e) {
    if (n == null) return true;
    if (n.val <= b || n.val >= e) return false;
    return checkBST(n.left, b, n.val) && checkBST(n.right, n.val, e);
  }
}
