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
     * best solution and idea
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/discuss/108870/Most-consistent-ways-of-dealing-with-the-series-of-stock-problems
     * dp[i][k] -> i & k == 第i天用k個交易來達成最佳獲利 -> 可以拆解成子問題
     * initial:
     * dp[i][0] -> 第任何i天, 但因為都不能執行交易, 結果都為0
     * dp[-1][k] -> 第-1天, 不存在任何 prices, 所以不管有多少k交易, 結果都是0
     * 股票交易只有買跟賣, dp[i][k] -> dp[i][k][0] & dp[i][k][1]
     * -> dp[i][k][0] -> 第i天已進行了k次交易, 最後手上沒有股票(0代表沒有股票)的最後成果
     * -> dp[i][k][1] -> 第i天已進行了k次交易, 最後手上有股票(1代表有股票)的最後成果
     * 總結:
     * base case
     * dp[-1][k][0] = 0 -> 第-1天(股市尚未開始),無論任何k次交易,最後手上沒有股票 == 0
     * dp[-1][k][1] = -Infinity 第-1天(股市尚未開始),無論任何k次交易,最後手上有股票 -> 不合理, 放-Infinity
     * dp[i][0][0] = 0 第i天, 都無任何次交易,最後手上沒有股票 == 0
     * dp[i][0][1] = -Infinity 第i天, 都無任何次交易,最後手上有股票 -> 不合理, 放-Infinity
     * sub-problem logic
     * dp[i][k][0] = max(dp[i-1][k][1] + prices[i], dp[i-1][k][0])
     * dp[i][k][1] = max(dp[i-1][k-1][0] - prices[i], dp[i-1][k][1])
     *
     * 這題邏輯應該是
     * dp[i][1][0] = max(dp[i-1][1][1] + prices[i], dp[i-1][1][0])
     * dp[i][1][1] = max(dp[i-1][0][0] - prices[i], dp[i-1][1][1]) -> max(-prices[i], dp[i-1][1][1])
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
