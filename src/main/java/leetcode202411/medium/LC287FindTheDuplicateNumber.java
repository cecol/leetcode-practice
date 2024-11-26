package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC287FindTheDuplicateNumber extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 完全沒概念這題其實是, ListNode 壹模壹樣的找到 circle 起點
    // nums[nums[i]] 就是下一個往下走的地方
    // 快慢先走, 互相遇到之後  fast 從頭1步走, 相會點就是 circle 點
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        if (n > 1) {
            int slow = nums[0];
            int fast = nums[nums[0]];
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            fast = 0;
            ;
            while (fast != slow) {
                fast = nums[fast];
                slow = nums[slow];
            }
            return slow;
        }
        return -1;
    }
}
