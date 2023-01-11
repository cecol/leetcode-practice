package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC670MaximumSwap extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC670MaximumSwap();
    }

    /**
     * https://leetcode.com/problems/maximum-swap/solutions/107068/java-simple-solution-o-n-time/
     * 有解出來  但沒有這個解法乾淨
     * 其實只要記載 每個數字的最後 last idx 就好
     * 為什麼只要 last idx?
     * Ex: 1992
     * 如果 是 [1][9]92 兌換 是 9192
     * 但我們要的是 9912
     * <p>
     * 針對每個 nums[i] 去檢查比他大的數字 last idx, 有就 swap
     *
     * 不過跑起來結果跟原本好像差不多
     */
    public int maximumSwap(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int[] bucket = new int[10];
        for (int i = 0; i < s.length; i++) {
            bucket[s[i] - '0'] = i;
        }
        for (int i = 0; i < s.length; i++) {
            for (int k = 9; k > 0 && k > s[i] - '0'; k--) {
                if (bucket[k] > i) {
                    char t = s[i];
                    s[i] = s[bucket[k]];
                    s[bucket[k]] = t;
                    return Integer.parseInt(new String(s));
                }
            }
        }
        return num;
    }
}
