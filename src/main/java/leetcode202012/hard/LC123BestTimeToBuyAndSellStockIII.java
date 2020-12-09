package leetcode202012.hard;

import leetcode20200921to20201031.BasicTemplate;

public class LC123BestTimeToBuyAndSellStockIII extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC123BestTimeToBuyAndSellStockIII();
        var s = LC.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * <p>
     * T[i][2][0] = max(T[i-1][2][0], T[i-1][2][1] + price[i])
     * T[i][2][1] = max(T[i-1][2][1], T[i-1][1][0] - price[i])
     * T[i][1][0] = max(T[i-1][1][0], T[i-1][1][1] + price[i])
     * T[i][1][1] = max(T[i-1][1][1], T[i-1][0][0] - price[i] => -price)
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 1) return 0;
        int ti20 = 0;
        int ti21 = Integer.MIN_VALUE;
        int ti10 = 0;
        int ti11 = Integer.MIN_VALUE;
        for (int p : prices) {
            ti20 = Math.max(ti20, ti21 + p);
            ti21 = Math.max(ti21, ti10 - p);
            ti10 = Math.max(ti10, ti11 + p);
            ti11 = Math.max(ti11, -p);
        }

        return ti20;
    }
}
