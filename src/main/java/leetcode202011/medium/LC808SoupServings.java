package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC808SoupServings extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC808SoupServings();
        var s = LC.soupServings(500);
    }

    /**
     * 2022/12/20
     * 這題其實是考機率概念
     * https://github.com/wisdompeak/LeetCode/tree/master/Recursion/808.Soup-Servings
     * - 不要被题目中“the probability that soup A will be empty first, plus half the probability that A and B become empty at the same time”
     * - 里面的这个plus所迷惑。其实这是一个or的关系。
     *
     * 就是說 如果 A 已經是 0, B 不管多少(要 > 0), 機率直接是 100%  A先倒完
     * 就是說 如果 A 已經是 0, B 也是 0, 機率直接是 50%  A先倒完
     * 就是說 如果 A 是 > 0, B 已經是 0, 機率直接是 0%  A先倒完
     *
     * 上面三種都是獨立可以疊加的機率
     * 配上有 4 種 operation, 每種機率都是 1/4
     * 所以是 0.25 * (dfs(a - 100, b) + dfs(a - 75, b - 25) + dfs(a - 50, b - 50) + dfs(a - 25, b - 75));
     *
     * 配上 double[][] dp 記憶之前算過的 case
     *
     * 可是有個細節是 0 <= n <= 10^9, 所以 dp[n][n] 會太大
     * https://leetcode.com/problems/soup-servings/solutions/121711/c-java-python-when-n-4800-just-return-1/
     * 這邊只存 dp[200][200]
     * 為什麼? 因為 沒有 a-0/b-100 case
     * 就是說 n 夠大 b 先變 0 機率會無限變小
     *
     * 這個數是 4800, 所以 n > 4800, return 1
     * 才只要 dp[200][200] (因為我們先把 (n+24)/25 減少, 會先 +24 再除 25 是因為 a 還有剩都還可以 serve)
     * dp[200][200] = dp[5000][5000] case
     * 這樣就可以在有限空間內算完 n
     *
     * 不然就算用 Map<(a/b pair), double> 儲存處理過的數字也會很大
     * */
    double[][] dp = new double[200][200];

    public double soupServings(int n) {

        return n > 4800 ? 1 : dfs((n + 24) / 25, (n + 24) / 25);
    }

    double dfs(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1;
        if (b <= 0) return 0;
        if (dp[a][b] > 0) return dp[a][b];
        dp[a][b] = 0.25 * (dfs(a - 4, b) + dfs(a - 3, b - 1) + dfs(a - 2, b - 2) + dfs(a - 1, b - 3));
        return dp[a][b];
    }

    /**
     * https://juejin.cn/post/6844904148513587208
     */
    public double soupServingsOld(int N) {
        if (N >= 4800) return 1; //when N reach 4800 or more, probability of 'a' being empty first is close to 1
        int n = (int) Math.ceil(N / 25D);
        double[][] dp = new double[n + 1][n + 1];
        dp[0][0] = 0.5; // a && b == 0 => equal case => 0.5 probability of 'a' being empty first
        for (int i = 1; i <= n; i++)
            dp[i][0] = 0D; // b == 0, a > 0 => no probability of 'a' being empty first for every b == 0
        for (int i = 1; i <= n; i++)
            dp[0][i] = 1.0D; // b > 0, a == 0 => 1.0 probability of 'a' being empty first for every b > 0
        // above are base case that 'a' being empty first

        // dp[a][b] is the probability of 'a' being empty when 'a' has a quantity and 'b' has b quantity
        // dp[a][b] is consisted of 4 cases:
        //  1. Serve (4)100 ml of soup A and (0)0 ml of soup B
        //  2. Serve (3)75 ml of soup A and (1)25 ml of soup B
        //  3. Serve (2)50 ml of soup A and (2)50 ml of soup B
        //  4. Serve (1)25 ml of soup A and (3)75 ml of soup B
        // so dp[a][b] = (dp[a-4][b] + dp[a-3][b-1] + dp[a-2][b-2] + dp[a-1][b-3]) * 0.25(all case hase same probability to be choosed)
        for (int a = 1; a <= n; a++)
            for (int b = 1; b <= n; b++) {
                dp[a][b] = (0.25 *
                        (dp[Math.max(a - 4, 0)][b] +
                                dp[Math.max(a - 3, 0)][Math.max(b - 1, 0)] +
                                dp[Math.max(a - 2, 0)][Math.max(b - 2, 0)] +
                                dp[Math.max(a - 1, 0)][Math.max(b - 3, 0)]));
            }
        logDoubleArray(dp);
        return dp[n][n];
    }
}
