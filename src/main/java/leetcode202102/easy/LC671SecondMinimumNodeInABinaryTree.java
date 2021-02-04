package leetcode202102.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC671SecondMinimumNodeInABinaryTree extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC671SecondMinimumNodeInABinaryTree();
    var s = LC.findSecondMinimumValue(null);
  }

  /**
   * https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/discuss/107233/Java-4-lines
   * 應該是有想到怎麼解
   * 但解法太困難, 看到標準答案才理解到我想太多了
   * */
  public int findSecondMinimumValue(TreeNode root) {
    if (root.left == null) return -1;
    int l = root.val == root.left.val ? findSecondMinimumValue(root.left) : root.left.val;
    int r = root.val == root.right.val ? findSecondMinimumValue(root.right) : root.right.val;
    return l == -1 || r == -1 ? Math.max(l, r) : Math.min(l, r);
  }
}
