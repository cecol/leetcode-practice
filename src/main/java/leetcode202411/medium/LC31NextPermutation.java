package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

public class LC31NextPermutation extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過, 只有模糊印象
    // 關鍵在於找出 next lexicographically greater permutation of its integer
    // 1. 重尾巴找到 第一組遞增(從0往前看), 這邊才是 next lexicographically ,找到山峰: ..|...
    // 2. 如果沒找到 代表整個反轉: |....
    // 3. 找到 -> 就是從尾巴找到一個 > nums[i] -> next lexicographically greater
    // 3-1. swap(i,j)
    // 4. rev (i+1, nums.length-1), 因為大的往前調過去了, 後段要開始重排成最小的 lexicographically permutation
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        rev(nums, i + 1, nums.length - 1);
    }

    void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    void rev(int[] nums, int i, int j) {
        while (i < j) swap(nums, i++, j--);
    }
}
