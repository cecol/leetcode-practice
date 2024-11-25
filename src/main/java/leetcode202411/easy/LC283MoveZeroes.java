package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;

import java.util.HashMap;
import java.util.Map;

public class LC283MoveZeroes extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 秒解 bj4
    public void moveZeroes(int[] nums) {
        int mv = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[mv] = nums[i];
                mv++;
            }
        }
        for (int i = mv; i < nums.length; i++) nums[i] = 0;

    }

}
