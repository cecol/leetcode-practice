package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC343IntegerBreak extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC343IntegerBreak();
    }

    /**
     * https://leetcode.com/problems/integer-break/solutions/80691/very-simple-java-solution-with-explanation/
     * 這題除了 DP 下去解之外
     * 有個特性是當 n < 8, 對半拆分的乘積最大
     * 2 = 1 + 1
     * 3 = 2 + 1
     * 4 = 2 + 2
     * 5 = 2 + 3
     * 6 = 3 + 3
     * 7 = 4 + 3
     *
     * https://github.com/wisdompeak/LeetCode/tree/master/Math/343.Integer-Break
     * 拆成自然對數 2.718... 的整數 - 2  or 3  來算會是最大乘積
     * 又以拆成 3 優先
     * 所以當 n > 7, n-=3... 直到 n < 7 再看 [對半乘積]
     */
    public int integerBreak(int n) {
        int prod = 1;
        while (n > 0) {
            if (n < 8) {
                return prod * (n / 2) * ((n + 1) / 2);
            }
            prod *= 3;
            n -= 3;
        }
        return prod;
    }
}
