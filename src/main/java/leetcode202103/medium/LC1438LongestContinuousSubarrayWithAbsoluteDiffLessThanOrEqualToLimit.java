package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1438LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit();
        LC.longestSubarray(new int[]{8, 2, 4, 7}, 4);
    }

    /**
     * 這題我知道min max可允許情況下擴增
     * 但是如果不允許時候  沒有想到更有效率的辦法來 縮表, 得用一些資料結構記住看過的 然後才能找到縮表後的 min/max
     * 但後來看到 deque來找 min max 才發現可以這樣
     * 應該是subarray連續特性可以用 deque
     * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/discuss/609771/JavaC%2B%2BPython-Deques-O(N)
     * max deque, min deque
     * 都只記載 max 遞減, min 遞增
     * 然後用 max.last - min.last > limit判定
     * deque max 遞減, deque min 遞增的意義是(更完整解釋可以參考LC239)
     * 基本上
     * deque max會砍掉比自己小的代表往後的window 要繼續當window max的更沒希望
     * deque min會砍掉比自己大的代表往後的window 要繼續當window min的更沒希望
     * 若 window 目前max, min(一定都在queue的頭) 超過limit -> 縮表 -> 更新min/max queue
     * 最後的 j - i 就是最大的 window
     */
    public int longestSubarray(int[] nums, int limit) {
        Deque<Integer> max = new LinkedList<>();
        Deque<Integer> min = new LinkedList<>();
        int i = 0, j = 0;
        for (; j < nums.length; j++) {
            while (max.size() > 0 && nums[j] > max.peekLast()) max.pollLast();
            while (min.size() > 0 && nums[j] < min.peekLast()) min.pollLast();
            max.add(nums[j]);
            min.add(nums[j]);
            if(max.peek() - min.peek() > limit) {
                if(max.peek() == nums[i]) max.poll();
                if(min.peek() == nums[i]) min.poll();
                i++;
            }
        }
        return j - i;
    }
}
