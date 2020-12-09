package leetcode202012.hard;

import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC188BestTimeToBuyAndSellStockIV extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC188BestTimeToBuyAndSellStockIV();
        var s = LC.maxProfit(3, new int[]{7, 1, 5, 3, 6, 4});
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * if k >= prices.length(days)/2 => no more transaction we can do(1 transaction needs 2 days)
     * => same as k = infinite case => Leetcode 123 case
     */
    public int maxProfit(int k, int[] prices) {
        if (k >= prices.length / 2) {
            int tik0 = 0;
            int tik1 = Integer.MIN_VALUE;
            for (int p : prices) {
                tik0 = Math.max(tik0, tik1 + p);
                tik1 = Math.max(tik1, tik0 - p);
            }
            return tik0;
        }
        int[] ti0 = new int[k + 1];
        int[] ti1 = new int[k + 1];
        Arrays.fill(ti1, Integer.MIN_VALUE);
        for (int p : prices) {
            for (int K = 1; K <= k; K++) {
                ti0[K] = Math.max(ti0[K], ti1[K] + p);
                ti1[K] = Math.max(ti1[K], ti0[K - 1] - p);
            }
        }
        return ti0[k];
    }
}
