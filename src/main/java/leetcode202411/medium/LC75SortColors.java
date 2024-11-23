package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC75SortColors extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 完全忘記 c0, c2 各自記載 0 & 2 個字填的位置, 配合 i 往前走, 就可以跟 c0, c2 swap, 換到最後, 中間就都是 1
    // 1. while (nums[i] == 2 && i < c2) swap(nums, i, c2--); 一直往後換到 nums[i] != 2
    // 2. while (nums[i] == 0 && i > c0) swap(nums, i, c0++); 一直往前換到 c0 都是 0
    // 3. 最後就自然 中間是 1
    public void sortColors(int[] nums) {
        int c0 = 0, c2 = nums.length - 1;
        for (int i = 0; i <= c2; i++) {
            while (nums[i] == 2 && i < c2) swap(nums, i, c2--);
            while (nums[i] == 0 && i > c0) swap(nums, i, c0++);
        }
    }

    void swap(int[] n, int p, int q) {
        int t = n[p];
        n[p] = n[q];
        n[q] = t;
    }
}
