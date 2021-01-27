package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import util.TreeNode;

public class LC235LowestCommonAncestorOfABinarySearchTree extends BasicTemplate {
  public static void main(String[] args) {
    var LC = new LC235LowestCommonAncestorOfABinarySearchTree();
    var s = LC.lowestCommonAncestor(null, null, null);
  }


  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode tmp = p;
    if(p.val > q.val) {
       p = q;
       q = tmp;
    }
    while ((root.val < p.val &&root.right != null ) ||
        (root.val > q.val && root.left != null)
    ) {
      if (root.val < p.val) root = root.right;
      if (root.val > q.val) root = root.left;
    }
    return root;
  }
}
