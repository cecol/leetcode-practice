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
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/044.Wildcard-Matching
     * 這邊有更完整解釋他原本應該是
     * 如果p[j]='*'，它可以匹配s结尾的任意多个元素，所以有：
     * for (int k=0; k<=i; k++)
     * -  if (dp[k][j-1]==1) dp[i][j]=1;
     * - 其實是 dp[i][j] = dp[0][j-1] || dp[1][j-1] || dp[2][j-1] || ... || dp[i-1][j-1] || dp[i][j-1]。
     * - 0 to i 中只要任一中 j-1  中間沒中都都被 j(*) 吸收
     * 就好比 i-3 match j-i, 那 i-2/i-1/i 都被 j(*) Matches any sequence of characters,
     * 直接繼承 dp[i-3][j-1] (前面 j-1 要有中任意位置, 中間 i-1/i-2 沒中被 j(*) 吸收也算中, 前面 j-1 都沒中就不要妄想全部都給 j(*) 吸收, 因為要 j-1 有中才可以考慮 j)
     * 然後  dp[0][j-1] || dp[1][j-1] || dp[2][j-1] || ... || dp[i-1][j-1] 可以簡化成 dp[i-1][j]
     *
     * 同理 i match j(*), i-1 時候也檢查是否 match j(*), 也是針對 j-1 展開
     * dp[i-1][j] = {dp[0][j-1] || dp[1][j-1] || dp[2][j-1] || ... || dp[i-1][j-1]}
     * dp[i][j] = {dp[0][j-1] || dp[1][j-1] || dp[2][j-1] || ... || dp[i-1][j-1]} || dp[i][j-1]。
     * 就可以被替換成 dp[i-1][j] || dp[i][j-1]
     *
     *  dp[i][j-1] -> 可視為 j(*) match 0, 拿 i match j-1 直接繼承
     *  因為 Matches any sequence of characters 中的 (including the empty sequence).
     *  dp[i-1][j] 是指 0 to i-1 match j-1, 只要 i 前面有任一中 j-1, 中間沒match 被 j(*) Matches any sequence of characters 吸收
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++)
            if (p.charAt(j) != '*') break;
            else dp[0][j] = true;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if (curP == '?' || curP == curS) dp[i][j] = dp[i - 1][j - 1];
                else if (curP == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
