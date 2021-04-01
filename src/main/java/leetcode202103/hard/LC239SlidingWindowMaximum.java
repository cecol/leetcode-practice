package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class LC239SlidingWindowMaximum extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC239SlidingWindowMaximum();
        LC.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
    }

    /**
     * 因為這題才好好的理解為什麼 遞減 deque可以拿來找 sliding window中的max
     * https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation
     * deque的意義是
     * 1. 只存目前sliding window 看到的數字
     * 2. deque 從頭開始, 只存遞減數值, 代表queue head最大 一路往tail變小 -> 當有新數值a[x]從tail開始檢查 省略比當前數值a[x]小的
     * -> 因為新數值a[x] 在當前window, 比x早出現的都比a[x], 那麼更後面的window, 比x早出現還比a[x]小的更不可能有機會
     * -> (因為1.不在window, 2.a[x]比你大了你就別爭了, 讓a[x]去爭就好)
     * -> 當然如果 max 是出現在前面, 也會先用個res=Math.max(res,curMax) 記起來, 就算在算後面的window,
     * -> 僅管真正max被window排出了(不是因為大小輸了), 但res還是有記錄下來
     * -> 原文回答解釋
     * Now only those elements within [i-(k-1),i] are in the deque.(存目前sliding window 看到的數字)
     * We then discard elements smaller than a[i] from the tail.
     * This is because if a[x] < a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i],
     * or any other subsequent window: a[i] would always be a better candidate.
     */
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
