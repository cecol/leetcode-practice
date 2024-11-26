package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.List;

public class LC189RotateArray extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 完全忘了
    // nums = "----->-->"; k =3
    // result = "-->----->";
    //
    // reverse "----->-->" we can get "<--<-----"
    // reverse "<--" we can get "--><-----"
    // reverse "<-----" we can get "-->----->"
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 2) return;
        k %= nums.length;
        rev(nums, 0, nums.length - 1);
        rev(nums, 0, k - 1);
        rev(nums, k, nums.length - 1);
    }

    void rev(int[] nums, int i, int j) {
        int t = 0;
        while (i < j) {
            t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
            i++;
            j--;
        }
    }

}
