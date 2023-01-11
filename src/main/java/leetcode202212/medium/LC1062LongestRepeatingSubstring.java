package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class LC1062LongestRepeatingSubstring extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1062LongestRepeatingSubstring();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/String/1044.Longest-Duplicate-Substring
     * 此題再考 rolling hash
     * 就是說 先用 binary search 找可能的 RepeatingSubstring 長度, 然後下去看看是不是真的存在
     * 如果存在 l = mid, else r = mid -1;
     * <p>
     * 基本找法式 (N^2) 每一個長度都下去找
     * https://leetcode.com/problems/longest-repeating-substring/solutions/304399/java-o-log-n-n-2-binary-search-solution/
     * <p>
     * 或者 O(1) 算 rolling hash 是否有遇到一樣的
     * rolling hash 有個缺陷是會 collision , 測資可以針對 collision 做就是
     */
    public int longestRepeatingSubstring(String s) {
        int n = s.length();
        int l = 1, r = n - 1;
        int res = 0;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (exist(s, m)) {
                res = m;
                l = m + 1;
            } else r = m - 1;
        }
        return res;
    }

    boolean exist(String s, int len) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i + len <= s.length(); i++) {
            String sub = s.substring(i, i + len);
            if (!seen.add(sub)) return true;
        }
        return false;
    }
}
