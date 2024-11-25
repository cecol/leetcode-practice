package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC136SingleNumber extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 秒解 只要記得 XOR 特性, 這題秒解
    public int singleNumber(int[] nums) {
        int xor = nums[0];
        for(int i = 1;i<nums.length;i++) xor = xor ^ nums[i];
        return xor;
    }

}
