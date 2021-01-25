package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LC102BinaryTreeLevelOrderTraversal extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC102BinaryTreeLevelOrderTraversal();
    LC.levelOrder(null);
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    List<TreeNode> cur = new ArrayList<>();
    cur.add(root);
    level(res, cur);
    return res;
  }

  private void level(List<List<Integer>> res, List<TreeNode> cur) {
    if (cur.size() == 0) return;
    List<TreeNode> nl = new ArrayList<>();
    res.add(cur.stream().map(i -> i.val).collect(Collectors.toList()));
    for (TreeNode t : cur) {
      if (t.left != null) nl.add(t.left);
      if (t.right != null) nl.add(t.right);
    }
    level(res, nl);
  }
}
