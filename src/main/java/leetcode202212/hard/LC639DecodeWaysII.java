package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC639DecodeWaysII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC639DecodeWaysII();
    }

    /**
     * https://leetcode.com/problems/decode-ways-ii/solutions/2745574/java-dp-use-the-map-can-be-much-easy-understand-reduce-if-else/
     * 這題是 LC91DecodeWays 的延伸, 多個 * 規則, * 可代表 [1-9]
     * 我們要用 dp[n+1] 來建立
     * dp[0] == empty string
     * dp[1] = s[0] 的 case
     *
     * 當計算 dp[i]  得考慮 dp[i-1] & dp[i-2]
     * (記得我們是找 dp[n+1], 所以看 dp[i], 其實是檢視 s[i-1]/s[i-2] 情況)
     * 得看 s[i-1] & s[i-2]
     * 先看 dp[i-1] 是否能用
     * 1. s[i-1] != 0 才有機會, s[i-1] == 0 時 因為最小 A == '1' 根本沒有 code 可以解
     * 2. s[i-1] == '*', 等於 s[i-1,i] 有 9 種可能, 1..9 -> dp[i]  += 9 * dp[i-1]
     * 3. s[i-1] = 1..9 -> 直接繼承可能性 -> dp[i] += dp[i - 1];
     *
     * 在看 dp[i-2]
     * 1. first, or second 任一是 '*' -> 找 map 已先與佩好的可能性 下去
     * - dp[i] += map.getOrDefault(key, 0) * dp[i - 2];
     * 2. first, second 都是數字
     * - 確認 first + second 在 10 - 26 區間 直接繼承
     * - dp[i] += dp[i - 2];
     *
     * 其實這題要用 top down 概念下去看
     * 因為 dp[n+1], 所以最後走到 dp[n], 根本沒有 s[n] (s length == n)
     * 所以是加總 s[n-1], s[n-2] 累積的 case
     * 就是 dp[n-1], dp[n-2] 的 cases
     *
     * 直觀來說 如果 s[n-1] 是數字 1-9 就是直接繼承(你也沒得加了吧) dp[i] += dp[i - 1];
     * s[n-2] + s[n-1] 在 10 - 26 區間 dp[i] += dp[i - 2];
     *
     * 最後在配上 * case 就可以
     *
     * */
    public int numDecodings(String s) {
        int n = s.length();
        int mod = (int) 1e9 + 7;
        Map<String, Integer> map = new HashMap<>();
        helper(map);
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : s.charAt(0) == '*' ? 9 : 1;
        for (int i = 2; i <= n; i++) {
            char first = s.charAt(i - 2);
            char second = s.charAt(i - 1);

            if (second != '0') {
                if (second == '*') {
                    dp[i] += 9 * dp[i - 1];
                } else {
                    dp[i] += dp[i - 1];
                }
            }

            if (first == '*' || second == '*') {
                String key = s.substring(i - 2, i);
                dp[i] += map.getOrDefault(key, 0) * dp[i - 2];
            } else if (first == '1' || first == '2' && second < '7') {
                dp[i] += dp[i - 2];
            }

            dp[i] %= mod;
        }
        return (int) dp[n];
    }

    void helper(Map<String, Integer> map) {
        map.put("1*", 9); // 11,12....19
        map.put("2*", 6); //21,22....,26
        map.put("*0", 2); //10,20
        map.put("*1", 2); //11,21
        map.put("*2", 2);
        map.put("*3", 2);
        map.put("*4", 2);
        map.put("*5", 2);
        map.put("*6", 2);
        map.put("*7", 1); //17 only
        map.put("*8", 1);
        map.put("*9", 1);
        map.put("**", 15); //11 ..[no 20] 19,..26 -> * cannot be 0 -> 15 個
    }
}
