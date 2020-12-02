package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC808SoupServings extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC808SoupServings();
        var s = LC.soupServings(500);
    }


    /**
     * https://juejin.cn/post/6844904148513587208
     */
    public double soupServings(int N) {
        if (N >= 4800) return 1; //when N reach 4800 or more, probability of 'a' being empty first is close to 1
        int n = (int) Math.ceil(N / 25D);
        double[][] dp = new double[n + 1][n + 1];
        dp[0][0] = 0.5; // a && b == 0 => equal case => 0.5 probability of 'a' being empty first
        for (int i = 1; i <= n; i++) dp[i][0] = 0D; // b == 0, a > 0 => no probability of 'a' being empty first for every b == 0
        for (int i = 1; i <= n; i++) dp[0][i] = 1.0D; // b > 0, a == 0 => 1.0 probability of 'a' being empty first for every b > 0
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
