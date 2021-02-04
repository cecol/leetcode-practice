package leetcode202102.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC572SubtreeOfAnotherTree extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC572SubtreeOfAnotherTree();
    var s = LC.isSubtree(null, null);
  }

  public boolean isSubtree(TreeNode s, TreeNode t) {
    if (isIdentical(s, t)) return true;
    return (s.left == null ? false : isSubtree(s.left, t)) ||
        (s.right == null ? false: isSubtree(s.right, t));
  }

  private boolean isIdentical(TreeNode s, TreeNode t) {
    if (s == null && t != null) return false;
    if (s != null && t == null) return false;
    if (s == null && t == null) return true;
    if (s.val != t.val) return false;
    return isIdentical(s.left, t.left) && isIdentical(s.right, t.right);
  }
}
