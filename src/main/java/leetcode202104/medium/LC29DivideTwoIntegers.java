package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC29DivideTwoIntegers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC29DivideTwoIntegers();
        LC.divide(10, 3);
    }

    /**
     * 2022/11/9 以前都沒搞懂這題要考什麼, 不知道這跟 Binary search 有什麼關係
     * https://github.com/wisdompeak/LeetCode/tree/master/Binary_Search/029.Divide-Two-Integers
     * 這邊有解釋到:
     * 1. 基本思想就是将divisor不断用左移的方法乘以2来逼近dividend
     * (divisor不断用左移的方法乘以2 -> 就是 Binary search,)
     *
     * 2. 然后将dividend减去倍乘之后的divisor，再重复这个过程直至被除数小于除数。记录这个过程中divisor“倍乘”的总次数，即为答案。
     * 所以才有下面的 divideH(int divide, int divisor)
     *
     * 這題看起來是不能用 long 來處理問題
     * https://leetcode.com/problems/divide-two-integers/discuss/13417/No-Use-of-Long-Java-Solution
     * 有不少答案都是用long
     * 不用long 有很多細節要處理
     * 1. dividend == Integer.MIN_VALUE && divisor == -1 要特別處理, 好像沒比較好的方式
     * 2. 因為要處理過程中的 overflow(小於 Integer.MIN_VALUE) 乾脆dividend, divisor都帶入負值下去運算
     * -> 為什麼要負值處理? 因為 Math.abs will not work for Integer.MIN_VALUE
     * -> 所以不能 Math.abs() 後下去處理除法
     * divideH:
     * if (divisor < dividend) return 0; -> 正整數來說應該是 dividend < divisor, 但負值後就倒過來才是可以繼續除
     *  while ((Integer.MIN_VALUE - sum < sum) && (sum + sum > dividend)) 中
     *  1. Integer.MIN_VALUE - sum < sum 其實是比較有無 overflow -> Integer.MIN_VALUE < sum + sum
     *  -> 如果 sum * 2 後小於Integer.MIN_VALUE 那就別玩了, out of while
     *  -> 也不能直接用 Integer.MIN_VALUE < sum + sum 這樣比較, 所以反過來
     *  -> Integer.MIN_VALUE - sum < sum 比較安全, 因為 sum < 0, Integer.MIN_VALUE - sum 只會更大m 不會往 overflow方向走
     *  2. sum + sum > dividend -> 因為是負數除法 -> 所以就是反過來比較
     *  3. int m = 1; 就是加了多少次 divisor(乘幾倍) 來除裡dividend
     *  4. 接下去遞迴
     *  為什麼要遞迴? 因為這邊是 左移的方法乘以2来逼近dividend, 每次都是 double Ex: divisor = 3 => 3, 6, 12, 24 逼近
     *  if dividend = 231, 31/3=8, 所以第一次應該算到 24 就停了, 拿 dividend = 31-24 = 7 下去 divideH(7, 3) 遞迴
     *
     *  這題裡面處理while ((Integer.MIN_VALUE - sum < sum) && (sum + sum > dividend)) {
     *  很多解法都是用 >> 1 bit offset來算 但我覺得太難看懂
     *  還好找到了一個比較明白的辦法
     */
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        boolean sign = (dividend < 0) == (divisor < 0);
        dividend = dividend > 0 ? -dividend : dividend;
        divisor = divisor > 0 ? -divisor : divisor;
        int res = divideH(dividend, divisor);
        return sign ? res : -res;
    }

    private int divideH(int dividend, int divisor) {
        if (dividend > divisor) return 0;
        int sum = divisor, m = 1;
        while ((Integer.MIN_VALUE - sum < sum) && (sum + sum > dividend)) {
            sum += sum;
            m += m;
        }
        return m + divideH(dividend - sum, divisor);
    }
}
