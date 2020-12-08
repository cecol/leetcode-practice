package leetcode202012.easy;

import leetcode20200921to20201031.BasicTemplate;

public class LC121BestTimeToBuyAndSellStock extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC121BestTimeToBuyAndSellStock();
        var s = LC.maxProfit(new int[]{7, 1, 5, 3, 6, 4});
    }

    /**
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
     * T[i][1][0] = max(T[i-1][1][0], T[i-1][1][1] + prices[i])
     * T[i][1][1] = max(T[i-1][1][1], T[i-1][0][0] - prices[i]) = max(T[i-1][1][1], -prices[i])
     * */
    public int maxProfit(int[] prices) {
        int t1iMinus10 = 0; // T[-1][1][0] = 0 ==> initial no stock case and no buy
        int t1iMinus11 = Integer.MIN_VALUE; // T[-1][1][1] = MIN_VALUE ==> initial no stock case but buy 1 case

        for (int p : prices) {
            t1iMinus10 = Math.max(t1iMinus10, t1iMinus11 + p);
            t1iMinus11 = Math.max(t1iMinus11, -p); // the original one is T[-1][0][0] == 0
        }
        return t1iMinus10;
    }
}
