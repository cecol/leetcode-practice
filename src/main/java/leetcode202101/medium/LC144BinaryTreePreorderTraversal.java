package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LC144BinaryTreePreorderTraversal extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC144BinaryTreePreorderTraversal();
    LC.preorderTraversal(null);
  }

  public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    res.add(root.val);
    pre(res, root.left);
    pre(res, root.right);
    return res;
  }

  private void pre(List<Integer> res, TreeNode r) {
    if (r == null) return;
    res.add(r.val);
    pre(res, r.left);
    pre(res, r.right);
  }
}
