package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC1922CountGoodNumbers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * 完全想錯這題
     * https://leetcode.com/problems/count-good-numbers/discuss/1317451/Java-or-Step-by-Step-clear-Explanation-with-Pictures-or-O(logN)
     * 這篇寫很好
     * n = 1, 1 位數, 就是只有 0,2,4,6,8: 5種偶數選擇
     * n = 2, 2 位數, 第1味 5 種, 第2位 4 質數種: 2,3,5,7
     * 公式: 如果 n 是偶數, 種數 5^n/2 * 4^n/2 (一半位數是偶數, 一半位數是質數)
     * 公式: 如果 n 是幾奇數, 種數 5^(n/2+1) * 4^n/2 (n/2+1 位數是偶數, n/2 位數是質數)
     * <p>
     * 但 N 可以大到 10^15 -> 不可能直接算 5^(N/2) 太大也太慢: O(N)
     * <p>
     * power 要自己做
     * 比如說 2^10 可以拆解成 2^5 * 2^5
     * 所以 power(x ,y) 要下去遞迴
     * if y%2==0 power(x, y/2) * power(x, y/2)
     * if y%2==1 power(x, y/2) * power(x, y/2) * x
     */
    int mod = (int) 1e9 + 7;

    public int countGoodNumbers(long n) {
        long first = n % 2 == 0 ? n / 2 : n / 2 + 1;
        long second = n / 2;
        long mul1 = power(5, first) % mod;
        long mul2 = power(4, second) % mod;
        return (int) (mul1 * mul2 % mod);
    }

    long power(long x, long y) {
        long temp;
        if (y == 0) return 1;
        temp = power(x, y / 2);
        if (y % 2 == 0) return temp * temp % mod;
        return x * temp * temp % mod;
    }
}
