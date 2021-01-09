package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC714BestTimeToBuyAndSellStockWithTransactionFee extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC714BestTimeToBuyAndSellStockWithTransactionFee();
        var s = LC.maxProfit(new int[]{}, 2);
    }

    /**
     * best idea and solutions
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
     * */
    public int maxProfit(int[] prices, int fee) {
        int tik0 = 0;
        int tik1 = Integer.MIN_VALUE;
        for (int p : prices) {
            tik0 = Math.max(tik0, tik1 + p);
            tik1 = Math.max(tik1, tik0 - p - fee);
        }
        return tik0;
    }
}
