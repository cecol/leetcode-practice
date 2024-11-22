package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC53MaximumSubarray extends BasicTemplate {
    public static void main(String[] args) {
    }

    // N^2 時間很容易, 但完全想不起來 O(N) 怎麼辦到, 直好去看舊的筆記
    // 以前是 easy, 現在變成 medium
    public int maxSubArray(int[] nums) {
        int curMax = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curMax = Math.max(nums[i], nums[i] + curMax);
            res = Math.max(res, curMax);
        }
        return res;
    }
}
