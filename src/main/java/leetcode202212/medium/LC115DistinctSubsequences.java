package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC115DistinctSubsequences extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC115DistinctSubsequences();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/115.Distinct-Subsequences
     * 想說前面解這麼多提這題應該會比較看得懂 結果還是卡住
     * dp[i][j] 表示 s(1:i) 有多少中了 t(1:j)
     * 0. dp[i][0] = 1; t 是空字串, s 自然只有一種組成方式 - 全刪
     * 1. 如果 s(i) == t(j) 那就可以考慮
     * - 1. dp[i-1][j-1] - (i/j match 了), 捨去 i/j 往前面 i-1/j-1 case 繼承
     * -    為什麼邊不是  dp[i-1][j-1] + 1? 並不是 i/j match 就多一種組合,
     * -    而是 i-1/j-1 的所有種數組合再配上 i/j, 種數還是一樣！！
     * - 2. dp[i-1][j] - 捨去 i, 往 i-1/j 繼承
     * 2. s(i) != t(j) 就只能考慮, dp[i-1][j] - 捨去 i, 往 i-1/j 繼承
     * */
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        if (m < n) return 0;
        if (m == n) return s.equals(t) ? 1 : 0;
        for (int i = 0; i <= m; i++) dp[i][0] = 1;
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] =  dp[i - 1][j] + dp[i - 1][j - 1];
                else dp[i][j] = dp[i - 1][j];
            }

        return dp[m][n];
    }
}
