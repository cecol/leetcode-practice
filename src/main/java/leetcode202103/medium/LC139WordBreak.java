package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LC139WordBreak extends BasicTemplate {


    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC139WordBreak();
        LC.wordBreak("leetcode", List.of("leet", "code"));
    }

    /**
     * 花了多比較多時間解掉, 速度是快的, 看來我對於 string的DP不是這麼的熟悉
     * 這就是基本的硬幣加總的dp題目, 只是硬幣變成字串, 加總變成 String s
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for (String w : wordDict)
            if (w.length() <= s.length() && s.startsWith(w, 0)) dp[w.length() - 1] = true;
        for (int i = 1; i < dp.length; i++) {
            for (String w : wordDict) {
                if (dp[i]) break;
                int h = i - w.length();
                if (h >= 0 && dp[h]) dp[i] |= s.startsWith(w, h + 1);
            }
        }
        return dp[dp.length - 1];
    }
}
