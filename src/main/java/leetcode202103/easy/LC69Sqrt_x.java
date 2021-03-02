package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC69Sqrt_x extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC69Sqrt_x();
        LC.mySqrt(8);
    }

    /**
     * 完全沒有想到牛頓法取開根號
     * https://leetcode.com/problems/sqrtx/discuss/25057/3-4-short-lines-Integer-Newton-Every-Language
     * https://www.mofish.work/thread/236 牛頓法解釋
     * long r = x;
     * while (r*r > x)
     * r = (r + x/r) / 2;
     * return (int) r;
     * 但一直沒好好看懂牛頓法
     * 改用 binary search
     * https://leetcode.com/problems/sqrtx/discuss/25198/3-JAVA-solutions-with-explanation
     * 我原本也想用 binary search來解, 但沒辦法好好處理好 floor case
     * 就是 mySqrt(8) 應該要回傳2 但我都算到3 (事實上是2.82842)
     * 後來看到 if (m <= x / m && (m + 1) > x / (m + 1)) return m;
     * 其中就是用 m 跟 x/m來比較 這就處理好了 floor問題
     * 我原本是 m*m 跟 x 比較就比較難處理 floor case
     * 所以得到的 m 必然 <= x/m, 且 (m + 1) > x / (m + 1)
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        int i = 1, j = x;
        while (i < j) {
            int m = (i + j) / 2;
            if (m <= x / m && (m + 1) > x / (m + 1)) return m;
            else if (m > x / m) j = m;
            else i = m + 1;
        }
        return i;
    }
}
