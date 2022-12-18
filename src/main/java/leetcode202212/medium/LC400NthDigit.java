package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC400NthDigit extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC400NthDigit();
    }

    /**
     * 看了超久才看懂 nth 的意思
     * https://www.jianshu.com/p/73ae878eb3e9
     * 当序列中的数字是两位数、三位数等等后，
     * 第n个数就不再是序列中的第n个数了，
     * Ex: 10 中的 1 是 10th digit，0 是 11th digit
     * -   11 中的 第一個1 是 12th digit, 第二個1 是 13th digit,
     * 所以就是自然数序列看成一个长字符串，问我们第N位上的数字是什么
     * <p>
     * https://leetcode.com/problems/nth-digit/solutions/1215056/easy-to-understand-with-clear-explanation/
     * 所以 n 先不段減去前面
     * - 1 位數總數 [1..9] 共 9 個
     * - 2 位數總數 [10..99] 共 2 x 90 {99-10+1 = 90} 個
     * - 3 位數總數 [10..99] 共 3 x 900 {999-100+1 = 900}個
     * 直到 n 小於該位數總數
     * <p>
     * kDigit - 當前位數值
     * kDigitCount - 當前位數總個數
     * kDigitMin - 當前位數最小值
     * <p>
     * 當剩下 n 位數 (前面減掉了) 是在 kDigit 位數內
     * String num = (kDigitMin + (n - 1) / kDigit) + "";
     * 當 剩下的n 到了 kDigit 位數, 要 看剩下的n 是到了 kDigit 位數第幾個數字
     * (n-1)/k 就是跳過幾個 k 位數
     * 到達某個數字, 該數字 就有 nth digit
     *
     * 為什麼 (n-1) in String num = (kDigitMin + (n - 1) / kDigit) + "";
     * Ex: 因為我們是 kDigitMin 開始往前算 所以少一個
     *
     * 為什麼 (n-1) in int idx = (n - 1) % (int) kDigit;
     * 因為是算 offset
     */
    public int findNthDigit(int n) {
        long kDigit = 1;
        long kDigitCount = 9;
        long kDigitMin = 1;
        while (n > kDigit * kDigitCount) {
            n -= (int) kDigit * kDigitCount;
            kDigit++;
            kDigitCount *= 10;
            kDigitMin *= 10;
        }
        String num = (kDigitMin + (n - 1) / kDigit) + "";
        int idx = (n - 1) % (int) kDigit;
        return num.charAt(idx) - '0';
    }
}
