package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC190ReverseBits extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 完全忘記這題關鍵是
    // 1. << 向左 SHIFT, 讓 res 一直往前進
    // 2. >> 讓 N bits 不見
    // 3. res += N 最後一個是否為 1
    public int reverseBits(int n) {
        if (n == 0) return 0;
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            if ((n & 1) == 1) res++;
            n >>= 1;
        }
        return res;
    }

}
