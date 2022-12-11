package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC600NonNegativeIntegersWithoutConsecutiveOnes extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC600NonNegativeIntegersWithoutConsecutiveOnes();
        LC.findIntegers(13);
    }


    /**
     * https://leetcode.com/problems/non-negative-integers-without-consecutive-ones/solutions/266338/java-1ms-100-100-solution/
     * https://en.wikipedia.org/wiki/Fibonacci_coding
     * 這題關鍵是要先識別 i bits 2進位 能組成的沒連續 1 總數是
     * dp[i] = dp[i-1] + dp[i-2]
     * -> 就是說 i bit 我們放 0, 那剩下 i-1 bits 可以任意組 == dp[i-1]
     * -> 就是說 i bit 我們放 1, 那 i-1 bit 只能放0, 那剩下 i-2 bits 可以任意組 == dp[i-2]
     *
     * 跑一下範例
     * 1-bit, 2 numbers
     * 2-bit, 3 numbers
     * 3-bit, 5 numbers...
     * It's a Fibanocci sequence which can be proven easily by DP.
     *
     * 這是 Fibanocci coding
     * 可以用 Fibanocci 組合來找出 i bits 2進位 能組成的沒連續 1 總數是
     *
     * 不過題目給的是 n number, 不是 n bit
     * 所以要先看 n 轉換成多少 bits 看他低消是 Fibanocci coding 第幾個
     *
     * Ex: n = 77 = [1,001,101], bits = 7
     * 1. 有 Fibanocci[6] 低消 0 to [111,111], (7 bits 會是 0 to 1,111,111 遠超過 77 case)
     * 2. 然後接下來, 要計算剩餘的 1,000,000 to 1,001,101 有多少非連續 1 case
     * - 1,000,000 to 1,001,101 區間都有 [100] prefix, 所以只要看 [1,101]
     * - for (; i > 0 && (n & 1 << (i - 1)) == 0; i--) ; 就是看扣除 prefix, 剩下的區間是多少
     * - if(i == 0) -> 整個 prefix 都一樣,
     * -   Ex: n = 64 case: 就會取 Fib[6] 低消 再加上 1,000,000 to 1,000,000, 1 個 case
     * - else if(i == bits - 1) total += cnt[bits-2]; break;
     * -   Ex: n = 109[1,101,101] 沒有 prefix 可以消除, 結果直接是 Fib[6] + Fib[5]
     * -   看起來 prefix [11] 是非法的, 所以拿掉 11, 剩下 5bits 組合都可以取
     * -   因為 11 開頭後面的 [1101] 不管怎麼組都非法,  所以只有  [1,100,000] 以內的 [11,111] = Fib[5] 可以取
     * - else bits = i; 去掉 prefix [100], 剩下 [1,101] 遞迴
     * -   1101 就是 Fib[3], 0 to 111 case + 101 遞迴
     * */
    public int findIntegers(int n) {
        int bits = 0;
        for (int t = n; t > 0; t = t / 2, bits++) ;
        int[] cnt = {1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597,
                2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811
                , 514229, 832040, 1346269, 2178309, 3524578};
        int total = 0;
        while (true) {
            total += cnt[bits - 1];
            int i = bits - 1;
            for (; i > 0 && (n & 1 << (i - 1)) == 0; i--) ;
            if(i == 0) {
                total+=1;
                break;
            } else if(i == bits - 1) {
                total += cnt[bits-2];
                break;
            } else bits = i;
        }
        return total;
    }
}
