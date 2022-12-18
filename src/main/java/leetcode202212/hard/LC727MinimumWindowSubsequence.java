package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC727MinimumWindowSubsequence extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC727MinimumWindowSubsequence();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Greedy/727.Minimum-Window-Subsequence
     * 這題比較困難的是 我不知道 int[][] dp 裏面是放什麼, 然後最後回溯出最後的 結果 subString
     * int[][] dp 其實還是記載
     * dp[i][j] 代表 s(0 to i) 有 substring 包含 t(0 to j) subsequence
     * 1. s1(i) == s2(j): dp[i][j] = dp[i-1][j-1] + 1 代表 i-1/j-1 case 衍伸到 i/j (多 +1 就是 i/j 的長度)
     * 2. s1(i) != s2(j), 那 dp[i][j] 只能去 dp[i-1][j] + 1 找 (多 +1 還是 i/j 的長度)
     * <p>
     * 那邊界初始? dp[0][j] = Integer.MAX_VALUE / 2;
     * i = 0, empty 根本對不上任何 s2(j) 放 Integer.MAX_VALUE / 2; 後續回溯 String 會有用處
     * 所以想像 dp[1][j], 就算前面有 match 到, 到後面因為長度 1 < j 根本不可能 match s2,
     * 所以就算拿 dp[i - 1][j] or  dp[i - 1][j - 1] 都會拿到 Integer.MAX_VALUE / 2;
     * 只有當真的 i >= j 才有機會累積到 非 Integer.MAX_VALUE / 2; 的值,
     * <p>
     * 所以最後去掃 dp[i][n] 找 min, 以每一個 s1(i) 來 match j 結果
     * 如果還是  Integer.MAX_VALUE / 2 代表 s(i) 根本沒法 match s(j)
     * 找到非 Integer.MAX_VALUE / 2 且最小, 那個 i 就是 s1.substring 結尾,
     * pos = i,
     * len = dp[i][n] 就是 s1(i) 往前推多少 len 有 match 到 s2(j)
     * 結果就是 s1.substring(pos - len + 1, len);
     *
     * 所以我一開始的問題是 int[][] dp 怎表達有找到且找到長度呢?
     * 一開始 dp[0][j] 塞 Integer.MAX_VALUE / 2, 就是讓後續沒找到的 都是 >= Integer.MAX_VALUE / 2
     * 有找到的就會 < Integer.MAX_VALUE / 2, 且值就是 s1(i) match s2(j) substring 長度
     *
     * 這層轉換真的很特別
     */
    public String minWindow(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int j = 1; j <= n; j++) dp[0][j] = Integer.MAX_VALUE / 2;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = dp[i - 1][j] + 1;
            }
        }
        int len = Integer.MAX_VALUE / 2;
        int pos = 0;

        for (int i = 1; i <= m; i++) {
            if (dp[i][n] < len) {
                len = dp[i][n];
                pos = i;
            }
        }
        if (len >= Integer.MAX_VALUE / 2) return "";
        else return s1.substring(pos - len, pos);
    }
}
