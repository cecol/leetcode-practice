package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC268MissingNumber extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 想太複雜, 直接拿 1 + .. + n 總值倒扣回去就好
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        for(int i:nums) sum-=i;
        return sum;
    }

}
