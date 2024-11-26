package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC152MaximumProductSubarray extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 忘記細節, 除了要記載 max & min
    // 還要檢查 nums[i] < 0, 要 MIN MAX 互換
    // 然後繼續記載 max(max, max* nums[i]) min(min, min * nums[i])
    public int maxProduct(int[] nums) {
        int res = nums[0], max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int t = max;
                max = min;
                min = t;
            }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
