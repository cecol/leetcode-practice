package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC446ArithmeticSlicesIISubsequence extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC446ArithmeticSlicesIISubsequence();
    }

    /**
     * 這題要 dp 解題
     * 如果真對每一個數值 檢查往後公差的可能性, DFS 下去會 TLE
     * DFS 做不到 DP 就是一個辦法, 尤其求方案數量 DP 嫌疑很大
     * https://github.com/wisdompeak/LeetCode/tree/master/Hash/446.Arithmetic-Slices-II-Subsequence
     * <p>
     * https://www.cnblogs.com/grandyang/p/6057934.html
     * DP 思路
     * 假設一維 DP, DP[i] 跟 DP[j] 到底 DP[i] 跟 DP[j] 之間關係是什麼？
     * DP[i] 跟 DP[j] 如果有個 k diff 存在
     * DP[i] 才可以延續 DP[j] 自己的 k diff
     * 所以其實每個 DP[i] 都有自己的 diff 長度紀錄
     * i, j 先算一個 diff,
     * DP[i] 檢查 DP[j] 是否已經有該 diff 紀錄, 有的話就是+= DP[j].diff 紀錄, 因為更加延長
     * 是把 res += DP[j].diff
     * 不是 res += DP[i].diff
     * 因為有當前 i, 前面j 的 diff 情況才是是新的等差數列, 才可以列入計算
     * :2     4     6     8     10
     * :      2->1  4->1  6->1  8->1
     * :            2->2  4->1  6->1
     * :                  2->3  4->2
     * :                        2->4
     * 比如当i=3指向数字8时，j=2指向数字6，那么二者差值为2，此时先在dp[3]建立 2->1 的映射，
     * 由于dp[j=2]中有 2->2 的映射，那么加上数字8
     * 其实新增了两个等差数列 [2,4,6,8] 和 [4,6,8]
     *
     * 另外有個更關鍵的理由是因為 called arithmetic if it consists of at least three elements
     * 要有三個 元素才會成為 arithmetic sequence,
     * 所以當前 dp[i] 有的 diff , dp[j] 也有, 代表 已滿足至少 at least three elements
     * 比如說 [2,6,10] diff 4 組成的 arithmetic sequence 是只有走到 10 才會 計算到有 2,6,10
     * 走到 6 時候, 2 根本沒還有 diff 4 紀錄
     * 走到 10 時候, 是拿 6 的 diff 4 -> 1 紀錄 剛好是一個 -> [2,6,10] -> at least three elements
     * 而非是 10 的 diff 4 -> 2, 10 的 diff 4 是給如果後面還有 14 時候會 加總 diff 4 -> 2
     * 這時候紀錄的 arithmetic sequence 有
     * 1. 2,6,10,14
     * 2. 6,10,14
     * 3. 2,6,10 在 10 已計入
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length, res = 0;
        Map<Integer, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long delta = (long) nums[i] - nums[j];
                if (delta > Integer.MAX_VALUE || delta < Integer.MIN_VALUE) continue;
                int diff = (int) delta;
                dp[i].put(diff, dp[i].getOrDefault(diff, 0) + 1);
                if (dp[j].containsKey(diff)) {
                    res += dp[j].get(diff);
                    dp[i].put(diff, dp[i].get(diff) + dp[j].get(diff));
                }
            }
        }
        return res;
    }
}
