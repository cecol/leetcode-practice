package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class LC103BinaryTreeZigzagLevelOrderTraversal extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC103BinaryTreeZigzagLevelOrderTraversal();
    LC.zigzagLevelOrder(null);
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    List<TreeNode> cur = new ArrayList<>();
    cur.add(root);
    zigzag(res, cur, false);
    return res;
  }

  private void zigzag(List<List<Integer>> res, List<TreeNode> cur, boolean r) {
    if (cur.isEmpty()) return;
    List<TreeNode> nl = new ArrayList<>();
    for(TreeNode t:cur) {
      if(t.left != null) nl.add(t.left);
      if(t.right != null) nl.add(t.right);
    }
    List<Integer> cl = cur.stream().map(t -> t.val).collect(Collectors.toList());
    if(r) Collections.reverse(cl);
    res.add(cl);
    zigzag(res, nl, !r);
  }
}
