package leetcode202009.medium;

import leetcode202009.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LC322CoinChange extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC322CoinChange();
        var r = LC.coinChange(new int[]{2}, 3);
    }

    public int coinChange(int[] coins, int amount) {
        int[] x = new int[amount + 1];
        x[0] = 0;
        for (int i = 1; i < x.length; i++) {
            x[i] = Integer.MAX_VALUE - 1;
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j]) {
                    x[i] = Math.min(x[i], x[i - coins[j]] + 1);
                }
            }
        }
        return x[amount] == Integer.MAX_VALUE - 1 ? -1 : x[amount];
    }
}
