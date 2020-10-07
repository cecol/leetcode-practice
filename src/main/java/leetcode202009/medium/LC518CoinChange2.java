package leetcode202009.medium;

import leetcode202009.BasicTemplate;

public class LC518CoinChange2 extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC518CoinChange2();
        var r = LC.change(5, new int[]{1, 2, 5});
    }

    public int change(int amount, int[] coins) {
        int[] x = new int[amount + 1];
        x[0] = 1;
        for (int i = 1; i < x.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    x[i] += x[i - coins[j]];
                }
            }
        }
        log.debug("{}", x);
        return x[amount];
    }
}
