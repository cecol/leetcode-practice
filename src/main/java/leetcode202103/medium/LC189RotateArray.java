package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC189RotateArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC189RotateArray();
        LC.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 2);
    }

    /**
     * https://leetcode.com/problems/rotate-array/discuss/54252/Java-O(1)-space-solution-of-Rotate-Array.
     * 我想了很久 space O(1) 解法想不出來
     * 看到答案才發覺我根本想錯, 但我根本想不到  reverse 有這種功效 太神奇了...
     * nums = "----->-->"; k =3
     * result = "-->----->";
     *
     * reverse "----->-->" we can get "<--<-----"
     * reverse "<--" we can get "--><-----"
     * reverse "<-----" we can get "-->----->"
     * this visualization help me figure it out :)
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 2) return;
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);

    }

    private void reverse(int[] nums, int i, int j) {
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
