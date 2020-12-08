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
     */
    public int maxProfit(int[] prices) {
        int ti_1k0 = 0; // T[-1][k][0] = 0 ==> initial no stock case and no buy
        int ti_1k1 = Integer.MIN_VALUE; // T[-1][k][1] = MIN_VALUE ==> initial no stock case but buy 1 case
        log.debug("ti_1k0: {}, ti_1k1: {}", ti_1k0, ti_1k1);
        for (int p : prices) {
            ti_1k0 = Math.max(ti_1k0, ti_1k1 + p);
            ti_1k1 = Math.max(ti_1k1, ti_1k0 - p);
            log.debug("ti_1k0: {}, ti_1k1: {}", ti_1k0, ti_1k1);
        }
        return ti_1k0;
    }
}
