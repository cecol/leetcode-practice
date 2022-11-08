package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC923_3SumWithMultiplicity extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    /**
     * 總算開始體會進階題, 這題是 H-, 3sum 概念是知道 要用 2 pointer, 但改變一下就卡住
     * https://leetcode.com/problems/3sum-with-multiplicity/discuss/181128/10-lines-Super-Super-Easy-Java-Solution
     * 直觀的解法, arr 每個元素走一遍, 走到當前元素看前面有沒有以記錄過的 target - arr[i], 有的話就加上去
     * 然後把當前元素跟前面再兩兩加總算上去 map
     *
     * 但是原本的 2 pointer 應該也是可以解 C++ 版本
     * https://leetcode.com/problems/3sum-with-multiplicity/discuss/1921963/2pointer-and-Hashmap-solution-with-explanations-in-C%2B%2B
     */
    public int threeSumMulti(int[] arr, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        int res = 0;
        int mod = 1000000007;
        for (int i = 0; i < arr.length; i++) {
            res = (res + m.getOrDefault(target - arr[i], 0)) % mod;
            for (int j = 0; j < i; j++) {
                int t = arr[i] + arr[j];
                m.put(t, m.getOrDefault(t, 0) + 1);
            }
        }
        return res;
    }
}
