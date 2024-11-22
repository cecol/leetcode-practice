package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC70ClimbingStairs extends BasicTemplate {
    public static void main(String[] args) {
    }

    Map<Integer, Integer> m = new HashMap<>();
    public int climbStairs(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if(m.containsKey(n)) return m.get(n);
        int nn = climbStairs(n - 1) + climbStairs(n - 2);
        m.put(n, nn);
        return nn;
    }
}
