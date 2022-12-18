package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC879ProfitableSchemes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC879ProfitableSchemes();
    }

    /**
     * https://leetcode.com/problems/profitable-schemes/solutions/154617/c-java-python-dp/comments/192260
     * 也是背包問題,
     * n 總人數, minProfit 達成的 profit
     * dp[n+1][minProfit + 1] 就是指花了 多少人數 [1 to n] 達成 profit [1 to minProfit]
     * 要輪詢每個 group[], profit[],
     * 針對當前 group[k], profit[k], k = 0 to group.length -1
     * 針對所有人力可能 i = 0 to n,  及所有  profit 可能 j = 0 to minProfit
     * 如果目前人力 i 有足夠需求 group[k] 的人力
     * 那就可以繼承 dp[i-g][j-p] 的 scheme 個數
     *
     * 最後加總 人力 0 to n 能達成 minProfit 的所有可能性
     *
     * 這題很多最佳化答案都是 i, j 從最大開始回推  不是從0 開始算
     * 是因為其實這本來是 dp[k][n][minProfit], 考慮下一個 group[i], 是基於上一個 group[i-1] 結果繼承來的
     * 但因為很多都簡化成 2D dp[n][minProfit], 從  right bottom to top left 算法不會多算到當前算的
     * 但這個優化實在不熟悉, 想要 top left 開始算就是要 temp[][] 來避免多算
     * */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int[][] dp = new int[n + 1][minProfit + 1];
        dp[0][0] = 1;
        int res = 0, mod = (int) 1e9 + 7;
        for (int k = 0; k < group.length; k++) {
            int g = group[k], p = profit[k];
            int[][] temp = new int[dp.length][dp[0].length];

            for (int i = 0; i < dp.length; i++) {
                temp[i] = Arrays.copyOf(dp[i], dp[i].length);
            }
            for (int i = 0; i <= n; i++)
                for (int j = 0; j <= minProfit; j++) {
                    if (i >= g) {
                        temp[i][j] = (dp[i][j] + dp[i - g][Math.max(0, j - p)]) % mod;
                    }
                }

            dp = temp;
        }
        for (int i = 0; i <= n; i++) res = (res + dp[i][minProfit]) % mod;
        return res;
    }
}
