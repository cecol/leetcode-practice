package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LC257BinaryTreePaths extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC257BinaryTreePaths();
    var s = LC.binaryTreePaths(null);
  }

  public List<String> binaryTreePaths(TreeNode root) {
    List<String> res = new ArrayList<>();
    if (root == null) return res;
    goBST(res,root,new ArrayList<>());
    return res;
  }

  private void goBST(List<String> res, TreeNode root, List<Integer> cur) {
    if (root == null) return;
    if (root.left == null && root.right == null) {
      cur.add(root.val);
      res.add(cur.stream().map(String::valueOf).collect(Collectors.joining("->")));
      cur.remove(cur.size() - 1);
      return;
    }
    if (root.left != null) {
      cur.add(root.val);
      goBST(res, root.left, cur);
      cur.remove(cur.size() - 1);
    }
    if (root.right != null) {
      cur.add(root.val);
      goBST(res, root.right, cur);
      cur.remove(cur.size() - 1);
    }
  }
}
