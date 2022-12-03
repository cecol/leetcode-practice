package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class LC1425ConstrainedSubsequenceSum extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1425ConstrainedSubsequenceSum();
    }


    /**
     * 這題其實跟 LC239SlidingWindowMaximum 很像
     * 但不是很直觀
     * 1. 維持一個 int[] sum, sum[i] 等同於 目前看到 nums[i] 的 sum max
     * 2. Deque<Integer> 其實在維持一個 sliding window + monotonic decrease 裏面放 index
     * - 在 Deque 的頭 只要跟目前 i 的距離還是 k 以內, 那還在 window 內, 不然要 pop 掉
     * - Deque 裏面放的 idx 就是指 window 內 sum[idx] 是 sum[i] monotonic decrease
     * - 如果目前遇到 sum[i] > sum[dq.last] => 代表這個 sum 會繼續往後長, 且可以關聯更後面
     * - 所以可以 dq.popLast, 最後 sum[i] 會進入 dq
     * - if (dq.size() > 0) sum[i] = Math.max(sum[i], sum[dq.peek()] + nums[i]);
     * - 是指 dq 裏面有前面可以關聯 sum[i], sum[i] 可能透過這個關聯更大
     * - 所以 sum[i] 是基於關聯累加來的 而且 dp.peek 是 i - 1 前最大
     * - 最後 if (sum[i] > 0) dq.offer(i); 只有正負我才放入, 畢竟要求 max
     *
     * https://github.com/wisdompeak/LeetCode/blob/master/Deque/1425.Constrained-Subsequence-Sum/1425.Constrained-Subsequence-Sum_deque.cpp
     * Deque sliding window + monotonic decrease
     * 有兩個特性
     * 頭是還能保留下來的 oldest idx 是因為他可以造成更大關聯 且還在 k 範圍內
     * 後面是小於頭 但未來可能在 頭被 window 砍掉之後可以造成最大關聯
     * 如果有當前關聯 可以剩我尾巴關聯, 那就尾巴 pop
     */
    public int constrainedSubsetSum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        Deque<Integer> dq = new ArrayDeque<>();
        int[] sum = new int[nums.length];
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (dq.size() > 0 && dq.peek() < i - k) dq.poll();
            sum[i] = nums[i];
            if (dq.size() > 0) sum[i] = Math.max(sum[i], sum[dq.peek()] + nums[i]);
            while (dq.size() > 0 && sum[dq.peekLast()] <= sum[i]) dq.pollLast();
            dq.offer(i);
        }
        for (int n : sum) res = Math.max(res, n);
        return res;
    }

}
