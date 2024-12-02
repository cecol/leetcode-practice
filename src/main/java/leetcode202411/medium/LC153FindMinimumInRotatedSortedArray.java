package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC153FindMinimumInRotatedSortedArray extends BasicTemplate {
    public static void main(String[] args) {
    }


    // 沒過
    // 關鍵是跟 r 比就好, nums[m] < nums[r] 代表 MIN 就不在這區間
    public int findMin(int[] nums) {
        int n = nums.length, l = 0, r = n - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] < nums[r]) {
                r = m;
            } else l = m + 1;
        }
        return nums[l];
    }
}
