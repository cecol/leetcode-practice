package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC91DecodeWays extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC91DecodeWays();
        LC.numDecodings("11106");
    }

    /**
     * https://leetcode.com/problems/decode-ways/discuss/30358/Java-clean-DP-solution-with-explanation
     * 太久沒複習DP, 都忘記怎麼解了
     * 所以看起來是
     * 間單來說dp[i] 能解出來的可能性是依據在 dp[i-1] & dp[i-2]
     * dp[i] 如果是可以自己decode的(就是decode出來是 1 - 9, 只要不是0都可以) 那可以累計 dp[i-1] 的次數
     * 所以反之如果 dp[i] == 0 -> dp[i] 是不能累計 dp[i-1] 的次數(因為dp[i] 不能自己decode, 所以dp[i-1]的不能計算)
     * dp[i] 與 dp[i-1] 若2個字元可以一起decode -> (就是decode出來是 10 - 26) 那可以累計 dp[i-2] 的次數
     * -> 但是要看目前的 i 是否 >= 2, 如果i 是 1 -> 那麼前面也沒得累計, 所以還是只能累計 + 1
     * dp[0] 因為前面沒有, 只能看當前字元 -> s.charAt(0) == '0' ? 0 : 1; 字元0是沒有什麼decode方式
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
        log.debug("{}", dp);
        return dp[s.length() - 1];
    }

}
