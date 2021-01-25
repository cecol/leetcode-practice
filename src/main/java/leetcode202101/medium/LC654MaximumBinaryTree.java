package leetcode202101.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.Arrays;

public class LC654MaximumBinaryTree extends BasicTemplate {
  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger("Main");
    var LC = new LC654MaximumBinaryTree();
    LC.constructMaximumBinaryTree(null);
  }


  public TreeNode constructMaximumBinaryTree(int[] nums) {
    if (nums.length == 0) return null;
    if (nums.length == 1) return new TreeNode(nums[0]);
    int mi = 0;
    for (int i = 0; i < nums.length; i++) if (nums[i] > nums[mi]) mi = i;
    TreeNode l = null;
    TreeNode r = null;
    if (mi > 0) {
      l = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, mi));
    }
    if (mi < nums.length - 1) {
      r = constructMaximumBinaryTree(Arrays.copyOfRange(nums, mi + 1, nums.length));
    }
    return new TreeNode(nums[mi], l, r);
  }
}
