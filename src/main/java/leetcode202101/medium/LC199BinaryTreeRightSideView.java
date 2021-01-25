package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC199BinaryTreeRightSideView extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC199BinaryTreeRightSideView();
    LC.rightSideView(null);
  }

  public List<Integer> rightSideView(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    List<TreeNode> l = new ArrayList<>();
    l.add(root);
    addR(res, l);
    return res;
  }

  private void addR(List<Integer> res, List<TreeNode> l) {
    if (l.size() == 0) return;
    res.add(l.get(l.size() - 1).val);
    List<TreeNode> nl = new ArrayList<>();
    for (TreeNode r : l) {
      if (r.left != null) nl.add(r.left);
      if (r.right != null) nl.add(r.right);
    }
    addR(res, nl);
  }
}
