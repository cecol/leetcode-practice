package leetcode202104.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC10RegularExpressionMatching extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC10RegularExpressionMatching();
        LC.isMatch("aaaaa", "a*");
    }

    /**
     * https://leetcode.com/problems/regular-expression-matching/discuss/5651/Easy-DP-Java-Solution-with-detailed-Explanation
     * 我其實一開始就沒有很直觀想到這就是DP[i][j]
     * dp[i][0] 一定是 false, 空字串 pattern match nothing
     * dp[0][j] -> what pattern matches 空字串?
     * -> p[j] 要是 *(所以必然在偶數位字) 且 p[j]前面都是match空白字串
     * -> 所以p 長得像 #*#*#*#* -> #* 代表第一個就match不到 s所有字元(s只有小寫組成, 所以只要match到小寫以外的)
     * -> for (int j = 2; j <= pn; j++) if (pc[j - 1] == '*') dp[0][j] |= dp[0][j - 2];
     * -> 從第j=2開始檢查, 如果 p[j-1] 是 '*' 且前面 j-2 之前比對就是 true(也是空白字串) dp[0][j] |= dp[0][j - 2];
     * -> 代表當前的 p[j-1] 是 '*' 當作 0 match case -> 可以當作空字串, 所以直接繼承 dp[0][j-2] 的結果
     * -> 這會從 dp[0][0] = true(第一個就是空字串), 往後延伸到某一格 p[j-1] 不是 *
     * <p>
     * if (pc[j] == '.' || pc[j] == sc[i]) dp[i][j] = dp[i-1][j-1] 當前字元一樣 -> 就是回推前一個字元的比對 或者 p[j] = . 就是隨意比對
     * -> !!為什麼不是 dp[i][j] 直接等於 true? -> 因為前面也要有中, 這邊才可以true, 所以 dp[i][j] = dp[i-1][j-1] -> 比對是連續的
     * -> 原意應該是 dp[i][j] = dp[i-1][j-1] && true
     * else if p[j] == '*' 代表要往回推 pattern
     * -> if (pc[j - 1] != sc[i] && pc[j-1] != '.') dp[i][j] = dp[i][j - 2]; -> 前一個字沒比對到 且也非 '.',
     * -> j-1 是 preP, preP 也沒中, 只好回溯到 j-2
     * -> 等於說該 pc[j] 是 '*' 的 pattern 整個都沒比對到, 當作 0 match 跳過, 所以拿 dp[i][j-2] 的情況
     * -> else dp[i][j] = (dp[i][j - 2] || dp[i - 1][j - 1] || dp[i - 1][j]);
     * -> 有比對前一字元 or 是 '.'(可以中任意字元) -> 有很多選項都可以取(全部 or 起來)
     * -> dp[i][j - 2] -> 無視 p[j] 是 * 的整個 pattern 就是當作 0 match
     * -> dp[i - 1][j - 2] -> 有拿p[j] * 的pattern -> 1 match -> 當前j有中 -> 所以回頭看看 i-1, j-2 的情況
     * -> dp[i - 1][j] -> 有拿p[j] * 的pattern -> many match -> 當前j有中 -> 就是拿 i-1 match到j 的結果來用
     * ->   這部分我沒有很理解, 目前理解方式就是, 如果 s(i-1) char == s(i) char, ex: P: a*, S: aaaaa
     * ->   那dp[i-1][j]就會是 true, 因為j是 *, 重複多個可以納入
     * ->   如果 s(i-1) char != s(i) char, 那dp[i-1][j]就會是 false, 那也不影響當前的 dp[i][j]
     * -> (因為i-1也是可以往後match到j 如果當時計算時候有算到 true)
     *
     * 再次回來釐清 為什麼 if (preCurP != '.' && preCurP != curS) dp[i][j] = dp[i][j - 2]; 都沒中時候是 j-2?
     * -> 因為 #####a(i)
     * ->     ####b*(j) -> a(i) 沒中* 也沒中 b(j-1), 只好退到 j-2, dp[i][j] = dp[i][j - 2];
     * -> 所以把 *(j), b(j-1) 當成空氣來比對, 所以直接拿 dp[i][j-2]
     * -> 不能想成把 a(i) 當成空氣, 因為 s是要比對字串, 不能被當成空氣, 能被當成空氣的只有 p的 *(j) 與 b(j-1)
     * -> 有中的時候
     * -> ######a(i)
     * -> ####a*(j) -> a(i) 沒中*(j) 但有中 a(j-1), 自然可以 dp[i][j] = dp[i-1][j];
     * -> a(i) 有中, 也可能 #(i-1) 當時也比對到 j, 所以也拿來用 many match
     * -> many match 意思就是 i中, i-1 有中, 如果 i-1 沒中, 這時候拿到就是 false, 就不是 many match
     * 或者
     * -> ####a*(j) -> a(i) 沒中*(j) 但有中 a(j-1), 自然可以 dp[i][j] = dp[i-1][j-2];
     * -> 直接 a(i) 中了 a(j-1), 但也要跟前面 (i-1) 串連
     * -> 因為 j-1 被當前 i 拿去比對了, 所以前面的 i-1 只可以比對 j-2
     * 所以更簡化的是, 既然拿 preCurP, preCurP 就是 j-1 狀態, 如果 s != preCurP, 自然只能繼承 j-2
     * 如果 s == preCurP -> 那就是 當前 i 比對到 j-1, 所以可以拿
     * dp[i-1][j-2], i 當成比對到 j-1, 所以串連前面結果 i-1, j-2
     * dp[i][j-2], j, j-1 都當成空氣
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 2; j < n + 1; j += 2) if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 2];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if (curS == curP || curP == '.') dp[i][j] = dp[i - 1][j - 1];
                else if (curP == '*') {
                    char preCurP = p.charAt(j - 2);
                    if (preCurP != '.' && preCurP != curS) dp[i][j] = dp[i][j - 2];
                    else dp[i][j] = (dp[i][j - 2] || dp[i - 1][j - 2] || dp[i - 1][j]);
                }
            }
        }
        logBooleanArray(dp);
        return dp[m][n];
    }
}
