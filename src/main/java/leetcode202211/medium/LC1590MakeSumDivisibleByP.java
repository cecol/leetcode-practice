package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC1590MakeSumDivisibleByP extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/make-sum-divisible-by-p/discuss/854197/JavaC%2B%2BPython-Prefix-Sum
     * 我知道要用餘數來找, 但太多細節沒處理好
     *
     * 1. 加總的 sum 會液出 int, 所以其實加總過程都要 %p,
     * 因為我們要知道沒有整除 p 的 [模數] 是多少, 才可以去找 preSum 的差是該 [模數]
     * 2. 算 preSum過程也要 %p, 避免太大
     * 再者 want = (cur-need+p) % p, cur - need 可能為負數, 所以就 +p %p 讓他變成正數  我們要取模數  不是餘數
     * 3. preSum Map 只要存最後看到的 index, 因為我們要最短, i 從 0 to n, 所以最後看到的就是 index 就正好是要的 smallest subarray
     * 4. res 一開始設置成 n, 因為最長就是自己, 自己減自己就是會得到0 一定整除 p
     * 如果找不到 want, default 就給 -n,   i - (-n) 只會更大
     * 一開始放的 mc.put(0, -1);, 也是最後沒有找到的 最長就是自己 case 除理辦法
     */
    public int minSubarray(int[] nums, int p) {
        int n = nums.length, res = n, need = 0, cur = 0;
        for (int i : nums) need = (need + i) % p;
        Map<Integer, Integer> mc = new HashMap<>();
        mc.put(0, -1);
        for (int i = 0; i < n; i++) {
            cur = (cur + nums[i]) % p;
            int want = (cur - need + p) % p;
            mc.put(cur, i);
            res = Math.min(res, i - mc.getOrDefault(want, -n));
        }
        return res < n ? res : -1;
    }
}
