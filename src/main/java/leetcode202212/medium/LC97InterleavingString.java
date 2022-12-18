package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC97InterleavingString extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC97InterleavingString();
        LC.isInterleave("aabcc", "dbbca", "aadbcbbcac");
    }

    /**
     * https://github.com/wisdompeak/LeetCode/blob/master/Dynamic_Programming/097.Interleaving-String/097.Interleaving-String.cpp
     * 這題其實要看的是 dp[i][j], s1(前i個) s2(前j個) 可以組成 s3(前i+j個)
     * 1. dp[0][0] = true, 大家都是空 一定符合
     * 2. dp[0][j], dp[i][0], 就是 s1(前i個) 單獨比對 s3(前i個),
     * - if (!dp[i - 1][0] || s1.charAt(i - 1) != s3.charAt(i - 1)) break; 一定要前面有中後面才可以連續比對下去
     * - 所以中途哪邊  s1.charAt(i - 1) != s3.charAt(i - 1) 或者前面 i-1 == false 就可以跳開了
     * 3.接下來就是 [i = 1 to m], [j = 1 to n] 下去看
     * - 反正基本概念就是 s1(前i個) or s2(前j個) 任意可以組成 s3(前i+j個)
     * - 所以 s1(i) == s3(i+j) or s2(j) == s3(i+j) 任一符合就好, 當然結果要是繼承前面有沒有 連續符合, 不能中斷
     * - dp[i][j] = dp[i - 1][j] or dp[i][j] = dp[i][j - 1]
     * */
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), k = s3.length();
        if (m + n != k) return false;
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;

        for (int i = 1; i <= m; i++) {
            if (!dp[i - 1][0] || s1.charAt(i - 1) != s3.charAt(i - 1)) break;
            dp[i][0] = true;
        }
        for (int j = 1; j <= n; j++) {
            if (!dp[0][j - 1] || s2.charAt(j - 1) != s3.charAt(j - 1)) break;
            dp[0][j] = true;
        }
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1)) dp[i][j] = dp[i - 1][j];
                else if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) dp[i][j] = dp[i][j - 1];
            }

        return dp[m][n];
    }
}
