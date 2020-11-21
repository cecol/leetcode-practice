package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC790DominoAndTrominoTiling extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC790DominoAndTrominoTiling();
        var s = LC.numTilings(3);
        var s2 = LC.numTilings(4);
        var s3 = LC.numTilings(5);
        var s4 = LC.numTilings(30);
    }


    /**
     * https://leetcode.com/problems/domino-and-tromino-tiling/discuss/116581/Detail-and-explanation-of-O(n)-solution-why-dpn2*dn-1%2Bdpn-3
     * dp[n] = dp[n-1] + dp[n-2] + 2 * (dp[n-3] + ... + dp[0]) -- E1
     * dp[n-1] = dp[n-2] + dp[n-3] + 2 * (dp[n-4] + ... + dp[0]) -- E2
     * .
     * .
     * E1 - E2:
     * dp[n] - dp[n-1] = dp[n-1] + dp[n-3]
     * --> dp[n] = 2*dp[n-1] + dp[n-3]
     */
    public int numTilings(int N) {
        int mod = 1000000007;
        if (N == 1) return 1;
        if (N == 2) return 2;
        if (N == 3) return 5;
        int[] dp = new int[N + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for (int i = 4; i <= N; i++) {
            dp[i] = ((2 * dp[i - 1] % mod) + dp[i - 3]);
            dp[i] = dp[i] % mod;
        }
        log.debug("N: {} -> {}", N, dp[N]);
        return dp[N];
    }
}
