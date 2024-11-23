package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC33SearchInRotatedSortedArray extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 還是忘記核心邏輯
    // 1. 算出來的 m 要知道 跟 target 是 '一起' 在左半還是右半 ?
    // 2. 比較左半或者右半 -> 就是直接跟 nums[0] 比就好, 不要去想跟 當前 l/r 比較
    // 3. 分辨完是否跟 target 同一邊, 就可以 l/r 往內縮
    // 4. 重點在移 l -> l = m + 1;, 主因是最後是拿 l 當答案確認, 反之如果是 r, 就得是 r = m - 1 ; 這是很細微差異
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if ((nums[m] < nums[0]) == target < nums[0]) {
                if (nums[m] < target) l = m + 1;
                else r = m;
            } else {
                if (target < nums[0]) l = m + 1;
                else r = m;
            }
        }

        return target == nums[l] ? l : -1;
    }
}
