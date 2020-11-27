package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC1143LongestCommonSubsequence extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC1143LongestCommonSubsequence();
        var s = LC.longestCommonSubsequence("abcde", "ace");
        var s1 = LC.longestCommonSubsequence("bl", "yby");
        var s3 = LC.longestCommonSubsequence("bsbininm", "jmjkbkjkv");
        var s4 = LC.longestCommonSubsequence("psnw", "vozsh");
    }

    /**
     *  https://hackmd.io/@Zero871015/LeetCode-1143
     *  https://blog.csdn.net/DaVinciL/article/details/99545037
     *  https://www.youtube.com/watch?v=NnD96abizww&ab_channel=TusharRoy-CodingMadeSimple
     *  dp[i][j] two cases => i, j當下是不考慮的
     *  1. text1[i] == text2[j] => extend longest case => dp[i-1][j-1] +1
     *     所以當i, j 一起考慮時且可以延長字串 => 就是拿i, j都未考慮的sub case + 1 => 就是dp[i-1][j-1] +1
     *  2. text1[i] != text2[j] => choose max of either dp[i-1][j] or dp[i][j-1]
     *     i, j不相等, 所以只能退一步拿各只考慮其中一個的sub case的最大值來當i, j
     *  Tips: dp[m][n] must be text1.size +1 and text2.size +1
     *        extra 1 for 0 length case
     *        and all dp[0][i] and dp[i][0] are 0 => 0 length string can only has 0 LCS
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null || text1.length() == 0 || text2.length() == 0) return 0;
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < dp.length; i++)
            for (int j = 1; j < dp[0].length; j++) {
                boolean s = text1.charAt(i - 1) == text2.charAt(j - 1);
                if (s) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
            }

        log.debug("dp result");
        logIntArray(dp);
        return dp[m][n];
    }
}
