package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

public class LC41FirstMissingPositive extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 答案其實只有兩種類
    // n+1 or 1 to n 之間
    // 先把 nums[i] set 成 n+1, if <= 0 or >= n
    // loop nums if nums[i] < n 代表他是正整數 1 -> n 之間
    // 把 nums[i] 變成負數
    // loop nums if nums[i] > 0 代表 offset i 沒出現過 -> 最小整數
    // 不然就是 n+1
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) nums[i] = n + 1;
        }
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num > n) continue;
            int idx = num - 1;
            if (nums[idx] > 0) nums[idx] *= -1;
        }
        for (int i = 0; i < n; i++) if (nums[i] < 0) return i + 1;
        return n + 1;
    }
}
