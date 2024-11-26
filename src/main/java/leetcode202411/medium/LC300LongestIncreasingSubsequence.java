package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class LC300LongestIncreasingSubsequence extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 有想到 TreeSet 上下界處理, 但是沒有把完整細節處理好
    // 1. 先檢查上屆, 因為是遞增, 超過直接加
    // 2. 沒超過就是找 ceiling, 移除 ceiling, 加上當前值
    // 3. 有包含就跳掉
    // 4. 最終 Size 就是最長的
    public int lengthOfLIS(int[] nums) {
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > ts.last()) {
                ts.add(nums[i]);
            } else {
                if (!ts.contains(nums[i])) {
                    Integer h = ts.ceiling(nums[i]);
                    if(h != null) {
                        ts.remove(h);
                        ts.add(nums[i]);
                    }
                }
            }
        }
        return ts.size();
    }
}
