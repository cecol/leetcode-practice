package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class LC1296DivideArrayInSetsOfKConsecutiveNumbers extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/discuss/470238/JavaC%2B%2BPython-Exactly-Same-as-846.-Hand-of-Straights
     * 這題真的很好操作 TreeMap
     * 先建立 TreeMap key = nums[i], value nums[i] 出現次數
     * 然後 loop TreeMap.keySet
     * 因為 TreeMap.keySet 一定從最小到最大 這是很關鍵特性
     * 所以可以往後找該元素 next Consecutive to k in TreeMap key,
     * 如果 next Consecutive to k in TreeMap key 的總數不足, 那就是無法成為 Consecutive set
     * if (tm.getOrDefault(nextNum, 0) < tm.get(cur)) return false;
     *
     * 如果有找到就減去, 再往下個數找
     */
    public boolean isPossibleDivide(int[] nums, int k) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int n : nums) tm.put(n, tm.getOrDefault(n, 0) + 1);
        for (Integer cur : tm.keySet()) {
            if (tm.get(cur) > 0) {
                for (int i = 1; i < k; i++) {
                    int nextNum = cur + i;
                    if (tm.getOrDefault(nextNum, 0) < tm.get(cur)) return false;
                    tm.put(nextNum, tm.getOrDefault(nextNum, 0) - tm.get(cur));
                }
            }
        }
        return true;
    }
}
