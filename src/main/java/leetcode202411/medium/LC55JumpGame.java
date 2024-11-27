package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

public class LC55JumpGame extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 完全忘記
    // 適用 last = n - 1 開始往後退, i = n-2 開始一個一個檢查,
    // 任一 nums[i] + i >= last, 代表可以逐步走到最後
    // last 一路退到 0 就是對的了
    public boolean canJump(int[] nums) {
        int last = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= last) last = i;
        }
        return last == 0;
    }
}
