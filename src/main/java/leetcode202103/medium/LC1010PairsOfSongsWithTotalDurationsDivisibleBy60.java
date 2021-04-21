package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1010PairsOfSongsWithTotalDurationsDivisibleBy60 extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1010PairsOfSongsWithTotalDurationsDivisibleBy60();
        LC.numPairsDivisibleBy60(new int[]{60, 60, 60});
    }

    /**
     * 完全沒想到只會有 60個 slots 所以用 int[] slows = new int[60] 來解就好
     * https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/discuss/256738/JavaC%2B%2BPython-Two-Sum-with-K-60
     * 基本上 time % 60 就是 two sum的概念
     * how many x satisfy (t + x) % 60 = 0.
     * The straight forward idea is to take x % 60 == 60 - t % 60,
     * But if t % 60 = 0, x % 60 = 0 instead of 60.
     * One solution is to use x % 60 == (60 - t % 60) % 60,
     * the other idea is to use x % 60 == (600 - t) % 60.
     * 為了處理0的 case -> slots[(600 - t) % 60] 就是最直接的辦法
     */
    public int numPairsDivisibleBy60(int[] time) {
        int[] slots = new int[60];
        int res = 0;
        for (int t : time) {
            res += slots[(600 - t) % 60];
            slots[t % 60]++;
        }
        return res;
    }

    /**
     * 有過, 用HashMap紀錄所有 time的 index, 但超慢
     * 227 ms, faster than 5.01% of Java
     */
    public int numPairsDivisibleBy60Slow(int[] time) {
        int res = 0;
        Map<Integer, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < time.length; i++) {
            int k = time[i] % 60;
            if (!m.containsKey(k)) m.put(k, new ArrayList<>());
            m.get(k).add(i);
        }
        for (int i = 0; i < time.length - 1; i++) {
            int lookFor = 60 - (time[i] % 60 == 0 ? 60 : time[i] % 60);
            if (m.containsKey(lookFor)) {
                List<Integer> idx = m.get(lookFor);
                int ii = 0;
                while (ii < idx.size() && idx.get(ii) <= i) ii++;
                res += idx.size() - ii;
            }
        }
        log.debug("{}", m);
        return res;
    }
}
