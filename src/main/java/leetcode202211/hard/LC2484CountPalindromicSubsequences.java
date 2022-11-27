package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC2484CountPalindromicSubsequences extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC2484CountPalindromicSubsequences();
    }

    /**
     * 這題看到兩大我很不安的主題就嚇到了
     * 1. Palindromes
     * 2. Subsequences
     * <p>
     * Palindromes 問題
     * 這題是 DP 解的, 他其實不是一般 Palindromes 題目, 就是字串左右比對  還要看字串長度是偶數還是奇數 來判斷中心點
     * 他要的 Palindromes 只有 5 長度
     * 所以只要找到 XY_YX pattern, 就好
     * <p>
     * Subsequences 問題
     * 看起來Subsequences 的唯一關鍵就是 保持次序
     * 所以找到 [XY]_YX pattern 中 前半段, XY 就是任意位置2字元 XY, X 出現在 Y 之前就好
     * 所以找到 XY_[YX] pattern 中 後半段, XY 就是任意位置2字元 XY, X 從尾巴算起 出現在 Y 之前就好
     * <p>
     * 又因為字元只有 '0' - '9'
     * 所以 XY 組合就是 '0' - '9' 下去找
     * https://leetcode.com/problems/count-palindromic-subsequences/discuss/2850442/Java-O(N)-with-comments
     * 1. 所以先從 頭往後 找 [XY]_YX pattern 中 前半段, xBeforeY count -> 儲存在 long[] startCount
     * 2. 所以先從 後往頭 找 XY_[YX] pattern 中 後半段, xBeforeY count * startCount[i-1]
     */
    public int countPalindromes(String s) {
        int n = s.length();
        long res = 0;
        long mod = (long) (1e9 + 7);
        for (char x = '0'; x <= '9'; x++) {
            for (char y = '0'; y <= '9'; y++) {
                long[] startCount = new long[n];
                long xBeforeY = 0;
                long xCount = 0;
                for (int i = 0; i < n; i++) {
                    startCount[i] = xBeforeY;

                    if (s.charAt(i) == y) xBeforeY += xCount;
                    if (s.charAt(i) == x) xCount++;
                }

                xCount = 0;
                xBeforeY = 0;
                for (int i = n - 1; i >= 3; i--) {
                    startCount[i] = xBeforeY;

                    if (s.charAt(i) == y) xBeforeY += xCount;
                    if (s.charAt(i) == x) xCount++;

                    res = (res + startCount[i - 1] * xBeforeY) % mod;
                }
            }
        }
        return (int) res;
    }
}
