package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

public class LC209MinimumSizeSubarraySum extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC209MinimumSizeSubarraySum();
        LC.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
    }

    /**
     * 原本自己寫的就是最快了  但code有點冗長
     * 所以參考最佳解的精簡化
     * https://leetcode.com/problems/minimum-size-subarray-sum/discuss/59078/Accepted-clean-Java-O(n)-solution-(two-pointers)
     */
    public int minSubArrayLen(int target, int[] nums) {
        int i = 0, res = Integer.MAX_VALUE, s = 0;
        for (int j = 0; j < nums.length; j++) {
            s += nums[j];
            while (s >= target && i <= j) {
                res = Math.min(res, j - i + 1);
                s -= nums[i++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
