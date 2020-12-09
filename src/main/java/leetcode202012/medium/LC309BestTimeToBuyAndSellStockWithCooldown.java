package leetcode202012.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC309BestTimeToBuyAndSellStockWithCooldown extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC309BestTimeToBuyAndSellStockWithCooldown();
        var s = LC.maxProfit(new int[]{});
    }

    public int maxProfit(int[] prices) {
        int tik0 = 0; // i-1 day
        int tik0_pre = 0; // i-2 day
        int tik1 = Integer.MIN_VALUE; // i-1 day
        for(int p:prices) {
            int t = tik0;
            tik0 = Math.max(tik0, tik1 + p);
            tik1 = Math.max(tik1, tik0_pre - p);
            tik0_pre = t; // before move on, i-2 move
        }

        return tik0;
    }
}
