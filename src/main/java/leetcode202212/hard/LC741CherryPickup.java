package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC741CherryPickup extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC741CherryPickup();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/741.Cherry-Pickup
     * 看這種重頭走到尾 尾走回頭, 其實也可以看做 頭走到尾2次
     * 就是左上角走到右下角 x2
     * 兩條路線看怎樣 cherry 最多, 如果有重疊 只算一次
     * 單一路線很單純 DP[i][j] ＝ max{ DP[i-1][j], DP[i][j-1] } + grid[i][j]
     * 兩條路線就有兩個點 [i, j] & [x, y] 得一起考慮
     * [i, j] 考慮 max{ DP[i-1][j], DP[i][j-1] } + grid[i][j]
     * [x, y] 考慮 max{ DP[x-1][y], DP[x][y-1] } + grid[x][y]
     * 還得考慮重疊
     * 基本上是 O(N^4)
     * 但因為 [i, j] [x, y] 走法都一樣  都只能往右下
     * 所以 [i, j] 不管選 [i-1, j] or [i, j-1]
     * [x, y] 也是擇選 [x-1, y] or [x, y-1]
     * 所以要反堆 i+j=x+y
     * y = i+j-x, 所以可以少一維, 因為路徑是相對的, 決定了 [i,j,x] y 不可以是任意點, 因為左上走到右下部數是有限的,
     * [i,j] [x,y] 走的總步數是一樣的
     * 所以可以簡化成 O(N^3)
     * 所以
     * dp[i][j][x] = Math.max(dp[i][j][x], dp[i - 1][j][x - 1]); 隱含 y
     * dp[i][j][x] = Math.max(dp[i][j][x], dp[i][j - 1][x - 1]); 隱含 y
     * dp[i][j][x] = Math.max(dp[i][j][x], dp[i - 1][j][x]); 隱含 y-1
     * dp[i][j][x] = Math.max(dp[i][j][x], dp[i][j - 1][x]); 隱含 y-1
     * <p>
     * https://leetcode.com/problems/cherry-pickup/solutions/399996/why-doesn-t-it-work/
     * 我一直不懂為什麼不能直接走兩輪 O(N^2)
     * 原來是第一輪最貪心走法, 會導致第二輪有些拿不到
     * 所以其實是兩輪要一起考慮  才有可能拿到最多
     * 就是說 第一論可能要次貪心, 第二輪才會拿到剩下的
     * 因為走法限制是 往右下走
     * Ex:
     * 110
     * 011
     * 110
     * 011
     * <p>
     * If we just find 2 paths, the first sweep leaves us with the matrix:
     * <p>
     * 000
     * 001
     * 100
     * 000
     * <p>
     * 剩下那兩個其實只能拿到一個
     */
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n + 1][n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++)
                for (int x = 0; x <= n; x++)
                    dp[i][j][x] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                for (int x = 1; x <= n; x++) {
                    int y = i + j - x;
                    if (y < 1 || y > n) continue;
                    if (grid[i - 1][j - 1] == -1 || grid[x - 1][y - 1] == -1) continue;
                    if (i == 1 && j == 1 && x == 1) {
                        dp[i][j][x] = grid[0][0];
                        continue;
                    }
                    dp[i][j][x] = Math.max(dp[i][j][x], dp[i - 1][j][x - 1]);
                    dp[i][j][x] = Math.max(dp[i][j][x], dp[i][j - 1][x - 1]);
                    dp[i][j][x] = Math.max(dp[i][j][x], dp[i - 1][j][x]);
                    dp[i][j][x] = Math.max(dp[i][j][x], dp[i][j - 1][x]);

                    if (i == x && j == y) dp[i][j][x] += grid[i - 1][j - 1];
                    else dp[i][j][x] += grid[i - 1][j - 1] + grid[x - 1][y - 1];
                }
        }
        return Math.max(0, dp[n][n][n]);
    }
}
