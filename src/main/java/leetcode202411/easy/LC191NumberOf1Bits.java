package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC191NumberOf1Bits extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 秒解 bj4, 跟 LC338 一樣
    public int hammingWeight(int n) {
        if (n == 0) return 0;
        if (n % 2 == 0) return hammingWeight(n / 2);
        return hammingWeight(n / 2) + 1;
    }
}
