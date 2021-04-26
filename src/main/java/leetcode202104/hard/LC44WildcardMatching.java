package leetcode202104.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC44WildcardMatching extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC44WildcardMatching();
    }

    /**
     * 因為解過 LC10 Regular Expression Matching 所以對這題的pattern 有自己推出來
     * 看過解答也差不多
     * 基本思考概念
     * 1. boolean[][] dp = new boolean[m + 1][n + 1];
     * -> dp[0][j] 就是空字串 配 pattern, dp[i][0] 就是有字串配 0 pattern
     * -> dp[0][0] = true, 什麼都沒有一定 match
     * -> dp[0][j] j從1到n, 如果是連續 *, 就是一直允許有空字串match下去
     * -> 如果中間非 * -> 代表空字串pattern 無法延續到後面j, 就算更後面還有 *, 但中間的非* 導致不允許空字串了
     * 2.  if (curP == '?' || curP == curS) dp[i][j] = dp[i - 1][j - 1];
     * -> 很直觀 單一字元字元比對有成, 就可以繼承前一層(所以前一層有, 這一層就有, 前一層沒有, 這一層就算match也不算有, match有連續性)
     * 3. else if (curP == '*')  -> dp[i][j] = dp[i - 1][j] || dp[i][j-1];
     * 就是說 * 可以比對任意字元任意長度, 所以
     * -> 1. 直接繼承前一 pattern dp[i][j-1] 的成果 -> 能到,當前dp[i][j]也可以到 -> 當前j當作沒有 0 match 取代掉, j-1 拿來用
     * -> 2. 直接繼承前一字元 dp[i-1][j] 到這個 j = '*' 的成果 -> 能到,當前dp[i][j]也可以到 -> 當前i當作* 取代掉, i-1 拿來用
     * */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) if (p.charAt(j) != '*') break;else dp[0][j] = true;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if (curP == '?' || curP == curS) dp[i][j] = dp[i - 1][j - 1];
                else if (curP == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j-1];
                }
            }
        }
        return dp[m][n];
    }
}
