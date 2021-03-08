package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC190ReverseBits extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC190ReverseBits();
    }

    /**
     * https://leetcode.com/problems/reverse-bits/discuss/54738/Sharing-my-2ms-Java-Solution-with-Explanation
     * 真的很不熟 bit operation 邏輯
     * 其實想法很基本
     * 1. 先設定 result = 0; -> iterate 過程中 result 一直往左shift
     * 2. 看看 n 的 last bit 是否是1 -> yes -> result++
     * -> 然後 iterate 過程中 n 一直往右shift
     * 其實就是因為 n 的 last bit放到 result last bit, 因為result 會一直往左shift,所以達成 reverse 效果
     * */
    public int reverseBits(int n) {
        if (n == 0) return 0;

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            if ((n & 1) == 1) result++;
            n >>= 1;
        }
        return result;
    }
}
