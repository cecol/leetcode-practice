package leetcode20200921to20201031.medium;

import leetcode20200921to20201031.BasicTemplate;

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

    /**
     * https://leetcode.com/problems/coin-change-2/discuss/176706/Beginner-Mistake%3A-Why-an-inner-loop-for-coins-doensn't-work-Java-Soln
     * 這篇有解釋為什麼這個解法的邏輯 coin loop outer, amount inner
     * 相較於 amount outer loop, coin inner loop
     * coin loop outer, amount inner 其實是原本的 dp[coin][amount] 的縮減版
     * 原版的 dp[i][j] 是指 number of ways to get sum 'j' using 'first i' coins
     * 第 i 個coin 針對每個 amount j 都是第一次使用 -> 所以才沒有重複計算的問題 因為那個 coin i 只有被使用一次(在當時的 amount j)
     * 然後縮減成 1 維陣列 dp[amount] 時候 是使用前面 'previous /first i coins'
     * 所以只要一維 dp[amount]
     * 因為每換一次 coin, 就把前面 coins 累加上來
     * coin loop outer 就代表 coin 只有被使用一次(對每個 amount j)
     *
     * coin loop outer, amount inner 是本來的 dp[coin][amount] 就是先走 coin 外層 再走每一個 amount
     *
     * 這題還有個關鍵 他是背包問題的 但是該 coin 可以拿多次
     *
     * coin loop outer, amount inner 是這題自己的dp語意的結果
     * 相較於 amount outer loop, coin inner loop -> dp語意不一樣
     * -> 這dp是指 組成amount j , 可以由過去任何 amount j-coin 組合回來(但有些組合是重複的也納入計算)
     * -> 兩者dp是不一樣的邏輯
     * */
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
