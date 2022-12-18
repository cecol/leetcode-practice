package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC730CountDifferentPalindromicSubsequences extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC730CountDifferentPalindromicSubsequences();
    }


    /**
     * https://leetcode.com/problems/count-different-palindromic-subsequences/solutions/632436/java-memorization-sol/
     * 取不同的 Palindrom 個數難的在於怎麼避免重複計算
     * dp[i][j] 可以 繼承 dp[i+1][j], dp[i][j-1], 但又重複算到 dp[i+1][j-1] case
     * 已知只有 4 種 a,b,c,d 字母 可以組合, 那就是拿 這4種分開去算
     * dp[n][n][4]
     * dfs 帶入 i/j 區間 還有當前找的字母 int pos
     * if(i == j && pos == s.charAt(l) - 'a') 這樣 dp[i][j] 才可以算 1
     *
     * 其他 i/j 區間 case
     * 1. if(s.charAt(l) == s.charAt(r) && s.charAt(l) - 'a' == pos), 是要找的字元
     * - if (s.charAt(i) == s.charAt(j) && s.charAt(i) - 'a' == pos) for (int m = 0; m < 4; m++);
     * -   dp[i+1][j-1] 內 遞迴 都可以考慮, 且配合各種 其他4種組合
     * 2. s.charAt(l) != s.charAt(r)
     * - 下去看 dp[i+1][j], dp[i][j-1], 但要減去重複算到 dp[i+1][j-1]
     * */
    int mod = (int) 1e9 + 7;
    long[][][] dp;

    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        dp = new long[n][n][4];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                Arrays.fill(dp[i][j], -1);
        long res = 0;
        for (int i = 0; i < 4; i++) res += dfs(s, 0, n - 1, i);
        return (int) (res % mod);
    }

    long dfs(String s, int i, int j, int pos) {
        if (i > j) return 0;
        if (i == j) {
            if (pos == s.charAt(i) - 'a') {
                dp[i][j][pos] = 1;
                return 1;
            }
            return 0;
        }

        if (dp[i][j][pos] != -1) return dp[i][j][pos];
        long res = 0;
        if (s.charAt(i) == s.charAt(j) && s.charAt(i) - 'a' == pos) {
            for (int m = 0; m < 4; m++) {
                res += dfs(s, i + 1, j - 1, m);
                res %= mod;
            }
            res += 2;
        } else {
            res += dfs(s, i + 1, j, pos);
            res += dfs(s, i, j - 1, pos);
            res -= dfs(s, i + 1, j - 1, pos);
            res %= mod;
        }
        dp[i][j][pos] = res;
        return res;
    }
}
