package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1208GetEqualSubstringsWithinBudget extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1208GetEqualSubstringsWithinBudget();
        LC.equalSubstring("abcd", "bcdf", 3);
    }
    /**
     * 因為先解過424所以理解這樣問題的基本解法
     * 基本概念就是 sliding window 的總計算 是合適maxCost就看一下是否是max 然後繼續擴增
     * 如果sliding window 的總計算是不合適maxCost, 代表要縮表
     * */
    public int equalSubstring(String s, String t, int maxCost) {
        int i = 0, res = 0, m = 0;
        for (int j = 0; j < s.length(); j++) {
            m += Math.abs(s.charAt(j) - t.charAt(j));
            while (m > maxCost) {
                m -= Math.abs(s.charAt(i) - t.charAt(i));
                i++;
            }
            res = Math.max(res,j-i+1);
        }
        return res;
    }
}
