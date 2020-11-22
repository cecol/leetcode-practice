package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC576OutOfBoundaryPaths extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC576OutOfBoundaryPaths();
        var s = LC.findPaths(2, 2, 2, 0, 0);
        var s2 = LC.findPaths(1, 3, 3, 0, 1);
    }

    public int findPaths(int m, int n, int N, int i, int j) {
        if(N <= 0) return 0;
        int mod = (int) Math.pow(10, 9) + 7;
        int[][][] dp = new int[N][m][n];
        dp[0][i][j] = 1;
        int[][] move = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int run = 1; run < dp.length; run++) {
            for (int x = 0; x < m; x++)
                for (int y = 0; y < n; y++)
                    for (int[] mv : move) {
                        int fromX = x + mv[0];
                        int fromY = y + mv[1];
                        if (fromX >= 0 && fromX < m && fromY >= 0 && fromY < n)
                            dp[run][x][y] = (dp[run][x][y] + dp[run - 1][fromX][fromY]) % mod;
                    }
        }

        int sum = 0;
        for (int layer = 0; layer < N; layer++) {
            for (int up = 0; up < n; up++) sum = (sum + dp[layer][0][up]) % mod;
            for (int down = 0; down < n; down++) sum = (sum + dp[layer][m - 1][down]) % mod;
            for (int left = 0; left < m; left++) sum = (sum + dp[layer][left][0]) % mod;
            for (int right = 0; right < m; right++) sum = (sum + dp[layer][right][n - 1]) % mod;
        }
        log.debug("dp 0 layer");
        logIntArray(dp[0]);
        log.debug("dp N layer");
        logIntArray(dp[N - 1]);
        log.debug("s: {}", sum);
        return sum;
    }
}
