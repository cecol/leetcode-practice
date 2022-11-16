package leetcode202211.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC594LongestHarmoniousSubsequence extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC594LongestHarmoniousSubsequence();
    }

    public int findLHS(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int n : nums) m.put(n, m.getOrDefault(n, 0) + 1);
        int res = 0;
        for (int n : nums) {
            if (m.containsKey(n - 1)) res = Math.max(res, m.get(n - 1) + m.get(n));
            if (m.containsKey(n + 1)) res = Math.max(res, m.get(n + 1) + m.get(n));
        }
        return res;
    }
}
