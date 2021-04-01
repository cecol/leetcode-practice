package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC829ConsecutiveNumbersSum extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC829ConsecutiveNumbersSum();
    }

    /**
     * 數學題
     * https://leetcode.com/problems/consecutive-numbers-sum/discuss/1117669/Mathematical-Solution
     * 這個答案比較仔細解釋
     * 基本上知道 連續數字的加總公式
     * 1. of length k only one consecutive sequence is possible. -> 長度k的連續數字加總只有唯一可能 (往前往後shift是遞減or遞增)
     * 2. x+ (x+1) + (x+2) ..... (x+k-1)=n
     * 3. kx + k(k-1)/2 = n (梯形加總公式)
     * 4. kx=n - k*(k-1)/2 -> k 跟 x 一定都 > 0 所以
     * 5. n-k*(k-1)/2 > 0
     * 6. n > k*(k-1)/2
     * 7. n > k*(k)/2 // approximation
     * 8. 2n>k^2
     * 9. sqrt(2n)>k
     * 10. now we have range for k . k>0 and k<sqrt(2n)
     * 11. kx = n - k*(k-1)/2
     * -> left side is mutliple of k. 等式左邊一定是k的倍數 x可以是任意整數值
     * -> if right side (n-k*(k-1)/2) % k == 0 means right side is also a multiple of k.
     * -> 如果等式右邊算出來也是k的倍數(%k==0), 代表你就是找到x 也是唯一個 x 開始連續加下去k個達成 N
     * -> So if this condition follows increase answer count 1.
     */
    public int consecutiveNumbersSum(int N) {
        int res = 0;
        for (int k = 1; k * k < 2 * N; k++) {
            if ((N - k * (k - 1) / 2) % k == 0) res++;
        }
        return res;
    }
}
