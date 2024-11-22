package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC238ProductOfArrayExceptSelf extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 有想到 用一個 mx 往右刷過去 在往左刷過去
    // 但一直以為只能原地改 nums
    // 其實可以做一個 int[] res, 避免改到 nums
    // res 往左刷就存下 乘到一半的, 往右刷乘上去就滿足條件
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int mx = 1;
        for (int i = 0; i < n; i++) {
            if(i > 0) mx *= nums[i-1];
            res[i] = mx;
        }
        mx = 1;
        for (int i = n - 1; i >= 0; i--) {
            if(i < n-1) mx*=nums[i+1];
            res[i] *= mx;
        }
        return res;
    }
}
