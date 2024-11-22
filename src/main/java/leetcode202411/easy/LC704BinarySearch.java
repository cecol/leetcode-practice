package leetcode202411.easy;


import leetcode20200921to20201031.BasicTemplate;


public class LC704BinarySearch extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 幾個小技巧
    // m 的拿法有 (l + r)/2 or l + (r-l) / 2, 記得後面會避開一些坑
    // nums[m] 跟 target 比較, r or l 要擇一: r = m - 1 or l = m + 1, 不然會陷入無窮迴圈, 通常選 l = m + 1;
    // 最後看找到的 L 是否跟 target 一樣, 不是就是 -1
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) r = m;
            else l = m + 1;
        }
        return target == nums[l] ? l : -1;
    }
}
