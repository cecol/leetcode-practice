package leetcode202011.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC712MinimumASCIIDeleteSumForTwoStrings extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC712MinimumASCIIDeleteSumForTwoStrings();
//        var s = LC.minimumDeleteSum("sea", "eat");
        var s2 = LC.minimumDeleteSum("ccaccjp", "fwosarcwge");
    }

    /**
     * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/discuss/108810/Concise-DP-solution
     * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/discuss/108811/JavaDP(With-Explanation)
     */
    public int minimumDeleteSum(String s1, String s2) {
        if (s1 == null || s1.length() == 0) return s2.chars().sum();
        if (s2 == null || s2.length() == 0) return s1.chars().sum();
        int[][] count = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i < count.length; i++) {
            count[i][0] = count[i - 1][0] + s1.charAt(i - 1);
        }
        for (int i = 1; i < count[0].length; i++) {
            count[0][i] = count[0][i - 1] + s2.charAt(i - 1);
        }
        for (int i = 1; i < count.length; i++) {
            for (int j = 1; j < count[0].length; j++) {
                int si = i - 1;
                int sj = j - 1;
                int cost = (s1.charAt(si) == s2.charAt(sj)) ? 0 : s1.charAt(si) + s2.charAt(sj);
                count[i][j] = Math.min(count[i - 1][j] + s1.charAt(si), count[i][j - 1] + s2.charAt(sj));
                count[i][j] = Math.min(count[i][j], count[i - 1][j - 1] + cost);
            }
        }
        logIntArray(count);
        return count[s1.length()][s2.length()];
    }
}
