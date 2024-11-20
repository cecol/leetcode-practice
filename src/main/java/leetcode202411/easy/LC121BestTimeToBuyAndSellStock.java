package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

public class LC121BestTimeToBuyAndSellStock extends BasicTemplate {
    public static void main(String[] args) {
    }

    public int maxProfit(int[] prices) {
        int noHold = 0;
        int hold = Integer.MIN_VALUE;
        for (int p : prices) {
            noHold = Math.max(noHold, hold + p);
            hold = Math.max(-p, hold);
        }
        return Math.max(noHold, hold);
    }
}
