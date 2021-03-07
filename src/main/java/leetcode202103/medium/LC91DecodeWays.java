package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC91DecodeWays extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC91DecodeWays();
        LC.numDecodings("12");
    }

    /**
     * https://leetcode.com/problems/decode-ways/discuss/30358/Java-clean-DP-solution-with-explanation
     * 太久沒複習DP, 都忘記怎麼解了
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        dp[0] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            int c1 = Integer.parseInt(s.substring(i, i + 1));
            int c2 = Integer.parseInt(s.substring(i - 1, i + 1));
            if (c1 >= 1 && c1 <= 9) dp[i] += dp[i - 1];
            if (c2 >= 10 && c2 <= 26) dp[i] += i >=2 ? dp[i-2] : 1;
        }
        return dp[s.length() - 1];
    }

}
