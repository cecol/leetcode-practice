package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC651_4KeysKeyboard extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC651_4KeysKeyboard();
        LC.maxA(7);
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/651.4-Keys-Keyboard
     * dp[i] 是第 i 次操作後能產生的最大字數
     * 1. i 是 ctrl-A - dp[i] = dp[i - 1] + 1;
     * 2. i 是 ctrl-V, 那麼前面 ctrl-V 幾次了
     * - 因為要預留 ctrl-A + ctrl-C, 所以最大只能 i - 2 次 ctrl-V -> j from 0 to i-2
     * - Ex: j == 0, dp[i - j - 2] 就是 dp[i-2] 是 ctrl-A, dp[i-1] 是 ctrl-C, dp[i] 是 ctrl-V,
     * -     ctrl-V, (0 + 1)一次在  dp[i-2] 時候的長度 變成 dp[i] 的長度
     * -     j == 1, dp[i - j - 2] 就是 dp[i-3] 是 ctrl-A, dp[i-2] 是 ctrl-C, dp[i], dp[i-1] 是 ctrl-V,
     * -     ctrl-V, (1 + 1)兩次在  dp[i-3] 時候的長度 變成 dp[i] 的長度
     * */
    public int maxA(int n) {
        int[] dp = new int[n + 1];
        if(n == 1) return 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j <= i - 2; j++) {
                dp[i] = Math.max(dp[i], dp[i - j - 2] * (j + 1));
            }
        }
        return dp[n];
    }
}
