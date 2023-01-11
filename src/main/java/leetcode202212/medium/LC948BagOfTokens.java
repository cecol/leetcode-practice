package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC948BagOfTokens extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC948BagOfTokens();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Greedy/948.Bag-of-Tokens
     * 其實可以是很直觀的 Greedy
     * 1. token 先排序, 由小到大, token 次序根本都沒差, 但我們最佳策略就是
     * - 1. 一直花費 power 在 小token, 所以從最小 token 花費起
     * - 2. 如果 power 不足,  就花費拿到的 score 去拿最大 token 變成 power, 然後再繼續消費
     * -    1. 但有可能花費 score 拿到更多 power 後卻沒有 token 可以買了, 這個話費是多餘的 所以要留一個 max
     * -       來記載 上一個已經是最佳的 score
     * - 根本不用想太複雜 說要留哪個 小 token 來拿來換 score 再去兌換更大 power,
     * - 就是貪心換下去, 能多大 power 加上能 花費在多少 最小 token 都是 local optimization,
     * - 然後過程記錄看到 max score 當作 global optimization
     * - 所以準備 int i = 0, 拿來花費 power 來換 score
     * - 所以準備 int j = tokens.length - 1;, 拿來花費 score 來換 更多 power 來下一輪兌換
     * - 直到 i <= j 被打破
     * */
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int i = 0, j = tokens.length - 1;
        int res = 0;
        int max = 0;
        while (i <= j) {
            if (power >= tokens[i]) {
                power -= tokens[i];
                res++;
                i++;
                max = Math.max(max, res);
            } else if (res > 0) {
                res--;
                power += tokens[j];
                j--;
            } else break;
        }
        return max;
    }
}
