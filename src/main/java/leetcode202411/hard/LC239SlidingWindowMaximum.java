package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC239SlidingWindowMaximum extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 1. deque 存遞減 Offset
    // 2. 總數是 n - k + 1
    // 3. loop j from 0 to nums.length - 1
    // 4. 當前 q head 已經超出 window -> 排出
    // 5. 當前 nums[j] > nums[q.peekLast] -> 排出, 小的一定沒機會
    // 6. q.offer(j)
    // 7. if j >= k -1 -> res[i++] = nums[q.peek()] -> 當 j 滿足 window size -> 就可以往下走
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[]{};
        int n = nums.length;
        int[] r = new int[n - k + 1];
        int i = 0;
        Deque<Integer> q = new LinkedList<>();
        for (int j = 0; j < nums.length; j++) {
            while (q.size() > 0 && q.peek() < j - k + 1) q.poll();
            while (q.size() > 0 && nums[q.peekLast()] < nums[j]) q.pollLast();
            q.offer(j);
            if (j >= k - 1) r[i++] = nums[q.peek()];
        }
        return r;
    }
}
