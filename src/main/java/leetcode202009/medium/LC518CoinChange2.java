package leetcode202009.medium;

import leetcode202009.BasicTemplate;

import java.util.Arrays;

public class LC518CoinChange2 extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC518CoinChange2();
        var r = LC.change(5, new int[]{1, 2, 5});
    }

    public int change(int amount, int[] coins) {
        if (amount == 0) return 1;
        if (coins == null || coins.length == 0) return 0;
        int[][] x = new int[coins.length][amount + 1];
        Arrays.sort(coins);
        log.debug("{}", coins);
        for (int i = 0; i < coins.length; i++) x[i][0] = 1;
        for (int i = 0; i < x.length; i++) {
            for (int j = 1; j < x[i].length; j++) {
                int uMax = i > 0 ? x[i - 1][j] : 0;
                int pMax = j >= coins[i] ? x[i][j - coins[i]] : 0;
                x[i][j] = uMax + pMax;
            }
        }
        for (int[] p : x) log.debug("{}", p);
        return x[coins.length - 1][amount];
    }

    public int fastest(int amount, int[] coins) {
        int arr[] = new int[amount + 1];
        Arrays.fill(arr, 0);
        arr[0] = 1;
        for (int c : coins)
            for (int i = c; i <= amount; i++)
                arr[i] += arr[i - c];
        return arr[amount];
    }
}
