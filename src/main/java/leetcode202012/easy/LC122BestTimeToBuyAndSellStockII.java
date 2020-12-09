package leetcode202012.easy;

import leetcode20200921to20201031.BasicTemplate;

public class LC122BestTimeToBuyAndSellStockII extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC122BestTimeToBuyAndSellStockII();
        var s = LC.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
        // buy 1[1] sell 2[5], buy 3[3] sell 4[6] => (5-1)+(6-3) = 7
        //    -1,         7, 1, 5, 3, 6, 4
        // 0:  0,         0, 0, 4, 4, 7, 7
        // 1: -MIN_VALUE,-7,-1,-1, 1, 1, 3
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     *
     * (Note: The caching of the old values of T_ik0, that is, the variable T_ik0_old, is unnecessary. Special thanks to 0x0101 and elvina for clarifying this.)
     * we can look at these 3 equations.
     * ①T[i][0] = max(T[i-1][0], T[i-1][1] + prices[i])
     * ②T[i][1] = max(T[i-1][1], T[i-1][0] - prices[i])
     * ③T[i][1] = max(T[i-1][1], T[i][0] - prices[i])
     *
     * condition 1:If T[i-1][0] >= T[i-1][1] + prices[i],we can get T[i][0] = T[i-1][0] in ①, then ③ is the same with ②.
     *
     * condition 2:If T[i-1][0] < T[i-1][1] + prices[i], then T[i][0] = T[i-1][1] + prices[i] in ①, we replace T[i][0] with T[i-1][1] + prices[i] in ③, then we get T[i][1] = T[i-1][1].
     * As T[i-1][1] > T[i-1][0] - prices[i] ,so we can get T[i][1] = T[i-1][1] from ②. the result of ② and ③ is the same.
     *
     * From condition 1 and 2, we can find that ② is the same with ③. we don't need to cache the old values(T[i-1][0]).
     *
     */
    public int maxProfit(int[] prices) {
        int T_ik0 = 0; // T[-1][k][0] = 0 ==> initial no stock case and no buy
        int T_ik1 = Integer.MIN_VALUE; // T[-1][k][1] = MIN_VALUE ==> initial no stock case but buy 1 case
        log.debug("ti_1k0: {}, ti_1k1: {}", T_ik0, T_ik1);
        for (int p : prices) {
            T_ik0 = Math.max(T_ik0, T_ik1 + p);
            // 這邊看起來算T_ik1時, 拿到的T_ik0已經被更新了, 是可以在更新T_ik0前先暫存T_ik0
            // 但是就算沒暫存 拿更新過的T_ik0其實邏輯也剛好符合
            // 有兩種情況
            // T_ik0 = Math.max(T_ik0, T_ik1 + p); => 要馬是保持以前的T_ik0 or 更新成T_ik1 + p
            // 若是case 1 保持原本的T_ik0 就不影響 T_ik1 = Math.max(T_ik1, T_ik0 - p);
            // 若是case 2 選 T_ik1 + p 我們可以知道 T_ik1 + p > T_ik0 => T_ik1 > T_ik0 - p
            // 那麼在 T_ik1 > T_ik0 - p case 下 T_ik1 = Math.max(T_ik1, T_ik0 - p); 也只會選前面的case
            T_ik1 = Math.max(T_ik1, T_ik0 - p);
            log.debug("ti_1k0: {}, ti_1k1: {}", T_ik0, T_ik1);
        }
        return T_ik0;
    }
}
