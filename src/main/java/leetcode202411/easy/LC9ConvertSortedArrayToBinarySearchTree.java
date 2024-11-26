package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC9ConvertSortedArrayToBinarySearchTree extends BasicTemplate {
    public static void main(String[] args) {
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 秒解 Bj4
    public TreeNode sortedArrayToBST(int[] nums) {
        int n = nums.length;
        if (n == 0) return null;
        int idx = n / 2;
        TreeNode rt = new TreeNode(nums[idx]);
        if (idx > 0) {
            rt.left = sortedArrayToBST(Arrays.copyOfRange(nums, 0, idx));
        }
        if (idx < n - 1) {
            rt.right = sortedArrayToBST(Arrays.copyOfRange(nums, idx + 1, n));
        }

        return rt;
    }
}
