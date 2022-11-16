package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC974SubarraySumsDivisibleByK extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        LC974SubarraySumsDivisibleByK LC = new LC974SubarraySumsDivisibleByK();
        LC.subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5);
    }

    /**
     * https://leetcode.com/problems/subarray-sums-divisible-by-k/discuss/217985/JavaC++Python-Prefix-Sum/235979
     * 知道是 preSum 問題, 但不知道怎麼找出 前面 preSum 剛好整除 k
     * <p>
     * 假設有 subarray sum 整除 k, 就是 k 整數倍
     * a-b = n*k, a = 當前的array加總 preSum, b = 前面走過的 preSum,
     * 要找到 b => b = a - n*k
     * 但我們不知道 n, 但可以透過這樣消除n, b%n = a%n - (n*k)%k
     * 因此要找的是 b%k == a%k
     * <p>
     * 但是計算 preSum 因為有負數, 所以要  prefix=(prefix+a%K+K)%K?
     * JAVA:   -11%3 = -2 -> 不在 0-2 區間 JAVA 是取餘數
     * Python: -11%3 = 1 -> 在 0-2 區間 PYTHON 是取模數
     * Java % 完再加上 k 就會得到 python 的 -11%3 = 1 模數
     * 這題上面公式preSum要取的是模數, 不是餘數!!
     */
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> mc = new HashMap<>();
        int res = 0, sum = 0;
        mc.put(0, 1);
        for (int n : nums) {
            sum = (sum + n % k + k) % k;
            res += mc.getOrDefault(sum, 0);
            mc.put(sum, mc.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
