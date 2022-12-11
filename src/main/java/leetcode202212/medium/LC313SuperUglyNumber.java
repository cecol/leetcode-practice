package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Int;

import java.util.Stack;

public class LC313SuperUglyNumber extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC313SuperUglyNumber();
        LC.nthSuperUglyNumber(5911, new int[]{2, 7, 13, 19});
    }


    /**
     * 這題其實觀念跟 LC264UglyNumberII 100% 一樣
     * 但是因為測資會讓你用 ugly[uglyIdx[j]] * primes[j > Integer.MAX_VALUE
     * 變成負數, 導致結果完全錯誤
     * 所以一開始要用 long[] ugly = new long[n]; 來儲存
     * 不然算超過一個長度會爆炸 結果變成負數
     * */
    public int nthSuperUglyNumber(int n, int[] primes) {
        long[] ugly = new long[n];
        int p = primes.length;
        int[] uglyIdx = new int[p];
        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            ugly[i] = Integer.MAX_VALUE;
            for (int j = 0; j < p; j++) {
                while (ugly[uglyIdx[j]] * primes[j] <= ugly[i - 1]) uglyIdx[j]++;
                ugly[i] = Math.min(ugly[uglyIdx[j]] * primes[j], ugly[i]);
            }
        }
        return (int)ugly[n - 1];
    }
}
