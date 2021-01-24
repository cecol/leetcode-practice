package leetcode202101.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC108ConvertSortedArrayToBinarySearchTree extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC108ConvertSortedArrayToBinarySearchTree();
        var s = LC.sortedArrayToBST(null);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0) return null;
        return getT(0, nums.length - 1, nums);
    }

    private TreeNode getT(int s, int e, int[] nums) {
        if (s > e) return null;
        if (s == e) return new TreeNode(nums[s]);
        int p = (s + e) / 2;
        return new TreeNode(nums[p], getT(s, p - 1, nums), getT(p + 1, e, nums));
    }
}
