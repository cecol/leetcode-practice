package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC265PaintHouseII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC265PaintHouseII();
        LC.minCostIISlow(new int[][]{{1, 5, 3}, {2, 9, 4}});
    }


    /**
     * https://leetcode.com/problems/paint-house-ii/solutions/69495/fast-dp-java-solution-runtime-o-nk-space-o-1/
     * 用 dp[n][k] 走 n * k * k 可以很直觀解出來
     * 就是每當看 dp[i] 就是看 dp[i-1][0 to k] 種 min 配上 costs[i][0 to k] 找出最小
     *
     * 但其實可以更快 就是透過 只存 preMin, preMinColor, prevSecMin
     * 因為在看當前 dp[i], dp[i] 只要跟前面 dp[i-1] 顏色不一樣就好
     * 但如果 dp[i] 挑的顏色剛好是 dp[i-1] 最小 case 一模一樣顏色時候
     * 其實就得退而求其次 求 dp[i-1] 的 secMin, 所以要記載
     * preMin, prevSecMin 然後根據當前 dp[i] 挑到的是否是 preMinColor 來拿 preMin or prevSecMin
     * 這樣就足夠了
     * 最後回傳 preMin
     * */
    public int minCostII(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        if (k == 1) return (n == 1 ? costs[0][0] : -1);
        int preMin = 0, preMinColor = -1, prevSecMin = 0;
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE, minColor = -1, secMin = Integer.MAX_VALUE;
            for (int c = 0; c < k; c++) {
                int val = costs[i][c] + (c == preMinColor ? prevSecMin : preMin);
                if (minColor < 0) {
                    min = val;
                    minColor = c;
                } else if (val < min) {
                    secMin = min;
                    min = val;
                    minColor = c;
                } else if (val < secMin) {
                    secMin = val;
                }
            }
            preMin = min;
            preMinColor = minColor;
            prevSecMin = secMin;
        }
        return preMin;
    }

    public int minCostIISlow(int[][] costs) {
        int n = costs.length;
        int k = costs[0].length;
        int[][] dp = new int[n][k];
        dp[0] = costs[0];
        for (int i = 1; i < n; i++) {
            int[] cost = costs[i];
            for (int ic = 0; ic < k; ic++) {
                dp[i][ic] = Integer.MAX_VALUE;
                for (int j = 0; j < k; j++) {
                    if (ic == j) continue;
                    dp[i][ic] = Math.min(dp[i][ic], dp[i - 1][j] + cost[ic]);
                }
            }
        }
        int min = dp[n - 1][k - 1];
        for (int i : dp[n - 1]) min = Math.min(i, min);
        return min;
    }
}
