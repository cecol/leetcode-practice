package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.*;

public class LC483SmallestGoodBase extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC483SmallestGoodBase();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Binary_Search/483.Smallest-Good-Base
     * 這題題目花點時間細思沒有很複雜, 但也需要一些基礎數學知識
     * n 只能由 x base 組成, 且這個組成只能有 1
     * 最直觀來說 n = 28, 用 base 27 來換算剛好是 11, 27^0*1 + 27^1*1 = 1 + 27 = 28
     * 但我們要找最小 k base 達成都是 1
     * 所以就變成 28 = k^0 + k^1 +k^2 + … + k^(m-1), => 等比数列求和公式：n = (k^m - 1)/(k-1)
     * k 越大 m 越小(極端值就是 k = n-1 ) , k 越小 m 越大
     * 找出最小 k, k 最小只能從2開始(1不能納入, 不然直接拿1就解了)
     * <p>
     * 因为k最小是2，那么对应项数m最大就是(這邊公式推導真花時間)
     * n = (k^m - 1)/(k-1) => k = 2 -> n = 比数列求和公式: (2^m - 1)/2-1 => n = 2^m - 1
     * 我們知道 Log2(8) = 3, 所以 Log2(n+1) = m,
     * 加上對數換底公式: LogX(Y) = LogC(X)/LogC(Y), 所以 Log10(n+1)/log10(2) = m
     * https://leetcode.com/problems/smallest-good-base/discuss/96589/Java-solution-with-hand-writing-explain
     *
     * 這個解法比較直觀
     * m = 2 to 60 來找 (因為 input n max = 10^18+1, 配上 min k = 2, Log(10^18+1)/Log(2) = 60)
     * k = 2 to n 來找 -> binary search
     * 比数列求和公式：n = (k^m-1)/(k-1) =>
     * n(k-1) = k^m - 1
     * n(k-1) > k^m - 1 -> k 太大
     * n(k-1) < k^m - 1 -> m 太大
     */
    public String smallestGoodBase(String nn) {
        long n = Long.parseLong(nn);
        long res = 0;

        for (int m = 60; m >= 2; m--) {
            long l = 2, r = n;
            while (l < r) {
                long mid = l + (r - l) / 2; // k

                BigInteger left = BigInteger.valueOf(mid);
                left = left.pow(m).subtract(BigInteger.ONE); // k^m - 1
                BigInteger right = BigInteger.valueOf(n).multiply(BigInteger.valueOf(mid).subtract(BigInteger.ONE)); // n(k-1)
                int cmr = left.compareTo(right);
                if (cmr == 0) {
                    res = mid;
                    break;
                } else if (cmr < 0) l = mid + 1;
                else r = mid;

            }
            if (res != 0) break;
        }
        return "" + res;
    }
}
