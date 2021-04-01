package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC371SumOfTwoIntegers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC371SumOfTwoIntegers();
    }

    /**
     * https://leetcode.com/problems/sum-of-two-integers/discuss/84278/A-summary%3A-how-to-use-bit-manipulation-to-solve-problems-easily-and-efficiently
     * 這篇是很仔細解釋 bit manipulation 各種可能
     * https://leetcode.com/problems/sum-of-two-integers/discuss/132479/Simple-explanation-on-how-to-arrive-at-the-solution
     * 所以當a + b沒有產生 bit 進位 -> a + b == a ^ b
     * 1   =>  001
     * 2   =>  010
     * 1^2 =>  011 (2+1 = 3)
     * 可是如果a + b有產生 bit 進位 -> a ^ b 中的  1 ^ 1 = 0 -> xor就無法反應出進位的數值
     * 3  => 011
     * 2  => 010
     * 3^2=> 001
     * 要找出哪邊有進位就是 a & b -> 但是 a&b完的 1 還是在原本位置, 而非往前前進一格 -> 所以進位得 (ａ＆ｂ) << 1
     * 3    =>  011
     * 2    =>  010
     * 3&2  =>  010
     * XOR(非進位的加法) 配上 &(處理進位case) 來實踐加法
     * ex:
     * 3 ^2        =>  001
     * (3&2)<<1    =>  100
     * 在xor 上面的結果就是答案 3 + 2 = 5
     * 所以就可以一路重複到 進位變成 0
     * 所以 a + b 就是
     * 1. while b != 0
     * 2. -> carry = a & b -> 先算進位
     * 3. -> a = a ^ b -> 先把非進位的bit 加在a上面
     * 4. -> b = carry << 1
     * ->     原本b 的非進位已在上一步加入a了, 進位點也記錄在 carry, 只是要往前一格再加入 -> 但當前沒加入下一個 loop在加入
     * ->     後續b都是記錄進位點, 每次進位點, 跟下次加入都會產生新的進位點, 所以後續的 loop都是進位點後續再處理(進位再進位),
     * ->     最後得到進位點變成0帶表結束
     */
    public int getSum(int a, int b) {
        int carry;
        while (b != 0) {
            carry = (a & b);
            a = a ^ b;
            b = carry << 1;
        }
        return a;
    }
}
