package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LC515FindLargestValueInEachTreeRow extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC515FindLargestValueInEachTreeRow();
    LC.largestValues(null);
  }

  public List<Integer> largestValues(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    List<TreeNode> cur = new ArrayList<>();
    cur.add(root);
    traversal(res, cur);
    return res;
  }

  private void traversal(List<Integer> res, List<TreeNode> cur) {
    if(cur.isEmpty()) return;
    int max = cur.get(0).val;
    List<TreeNode> nl = new ArrayList<>();
    for(TreeNode t:cur) {
      if(t.left!=null) nl.add(t.left);
      if(t.right!=null) nl.add(t.right);
      max = Math.max(max,t.val);
    }
    res.add(max);
    traversal(res,nl);
  }
}
