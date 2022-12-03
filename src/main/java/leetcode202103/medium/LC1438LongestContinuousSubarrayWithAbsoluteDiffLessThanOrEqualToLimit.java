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
        LC.longestSubarray(new int[]{1, 5, 6, 7, 8, 10, 6, 5, 6}, 4);
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

     * 2022/12/3 一直對於 i++ 且回傳 j - i 有困惑
     * - if (max.peek() - min.peek() > limit) {
     * -   if (max.peek() == nums[i]) max.poll();
     * -   if (min.peek() == nums[i]) min.poll();
     * -   i++;
     * - }
     * 沒有很看懂, 後來看到 TreeMap 解法才發現也是 i++, 回傳 j-i
     * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/discuss/609771/JavaC++Python-Deques-O(N)/710540
     * <p>
     * 後來才想到 i 不是直接代表 i offset to j offset f 區間的 subarray
     * i 是代表目前 subarray 可能大小, 因為 j 一定會走到 n, 如果 i == 0, 都沒遇到超界, 代表整個 subarray n 都是符合
     * 如果遇到超界, 代表拿 i = 0, 去找 subarray 是不可能存在
     * 所以得 i++, 代表目前可能可以的 subarray size 是 n-1
     * 所以得
     * if (max.peek() == nums[i]) max.poll();
     * if (min.peek() == nums[i]) min.poll();
     * 看看目前是否  nums[i] 剛好是 min or min, 也可能都不是, 代表下一圈又要 i++, 直到真的有刪到 min or max
     * 或者就是 一路加到 剩下 j-i=1
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
            if (max.peek() - min.peek() > limit) {
                if (max.peek() == nums[i]) max.poll();
                if (min.peek() == nums[i]) min.poll();
                i++;
            }
        }
        return j - i;
    }
}
