package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class LC862ShortestSubarrayWithSumAtLeastK extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC862ShortestSubarrayWithSumAtLeastK();
        LC.shortestSubarray(new int[]{48, 99, 37, 4, -31}, 140);
        LC.shortestSubarray(new int[]{84, -37, 32, 40, 95}, 167);
    }

    /**
     * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/143726/C%2B%2BJavaPython-O(N)-Using-Deque
     * 這題的是 209 Minimum Size Subarray Sum 的延伸版, 中間有負值,
     * 解題關鍵
     * -> subarray sum >= K, 先轉成 prefix sum array 就可以執行下面
     * -> 用 prefix sum 找 min sliding window >= K (不是原本的 array做 min sliding window)
     * -> 因為有負數, 所以 prefix sum 會在負數時候下降, 這導致後面有可能有 shorter sliding window,
     * -> 所以用遞增queue來記載 prefix sum window start
     * 1. 一個array 儲存到目前為止的prefix sum,
     * 2. 是用一個queue維持遞增的prefix sum index起點
     * -> 因為會有負數, 最佳解的subarray可能包含該負數或者沒有
     * 當做完 prefix sum array B
     * 開始loop過這個 prefix sum B
     * 目前處理到 B[i]
     * 第一個while
     * 檢查 遞增queue裡面記載i之前的 prefix sum 是否 b[i] - b[q[0]] >= k, 有就繼續算下去
     * 第二個while
     * 確保當前加入的b[i]還有保持遞增, 所以把b[q[last]] > b[i]都清理掉
     * 最後加入 b[i] in q
     *
     * 最主要的觀點是維持 prefix sum index in queue is increasing, 原文的回應解釋道:
     * 是因為 if b[i] <= b[q.last], 我們也知道 i > q.last,
     * 因此如果未來有個達成 b[i-future] - b[q.last] >= k
     * 那麼 b[i-future] - b[i] 更一定可以滿足 >= k 且因為 i > q.last 又使得這個 subarray sum >= K, shorter
     * 關鍵在於我們一開始就是勇prefix sum 來尋找 subarray 區間
     *
     * Q: Why keep the deque increase?
     * A: If B[i] <= B[d.back()] and moreover we already know that i > d.back(), it means that compared with d.back(),
     * B[i] can help us make the subarray length shorter and sum bigger. So no need to keep d.back() in our deque.
     *
     * More detailed on this, we always add at the LAST position
     * B[d.back] <- B[i] <- ... <- B[future id]
     * B[future id] - B[d.back()] >= k && B[d.back()] >= B[i]
     * B[future id] - B[i] >= k too
     *
     * so no need to keep B[d.back()]
     *
     */
    public int shortestSubarray(int[] A, int K) {
        int N = A.length, res = N + 1;
        int[] B = new int[N + 1];
        for (int i = 0; i < N; i++) B[i + 1] = B[i] + A[i];
        Deque<Integer> d = new LinkedList<>();
        for (int i = 0; i < N + 1; i++) {
            while (d.size() > 0 && B[i] - B[d.getFirst()] >= K) res = Math.min(res, i - d.pollFirst());
            while (d.size() > 0 && B[i] <= B[d.getLast()]) d.pollLast();
            d.addLast(i);
        }
        return res <= N ? res : -1;

    }
}
