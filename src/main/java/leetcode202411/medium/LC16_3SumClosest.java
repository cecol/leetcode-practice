package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC16_3SumClosest extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 其實就是考 3SUM
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1, k = nums.length - 1;
            while (j < k) {
                int s = nums[i] + nums[j] + nums[k];
                int diff = Math.abs(target - s);
                if (diff < minDiff) {
                    res = s;
                    minDiff = diff;
                }
                if (s > target) k--;
                else j++;
            }
        }
        return res;
    }
}
