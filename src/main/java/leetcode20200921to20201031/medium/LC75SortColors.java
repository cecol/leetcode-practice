package leetcode20200921to20201031.medium;

import leetcode20200921to20201031.BasicTemplate;

public class LC75SortColors extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC75SortColors();
        LC.sortColors(new int[]{1, 1, 1, 0, 0, 2, 1, 0, 1});
    }

    /**
     * https://leetcode.com/problems/sort-colors/discuss/26472/Share-my-at-most-two-pass-constant-space-10-line-solution
     * 覺得之前的解法太困難好好理解並記下來, 這個比較直覺
     * 比較特別就是 while (nums[i] == 2 && i < c2) 當c2 本身也是 2的時候 就一直換下去直到 c2指向不是2(當前nums[i] != 2) or i == c2(終止條件)
     * 比較特別就是 while (nums[i] == 0 && i > c0) 當c0 本身也是 0的時候 就一直換下去直到 c0指向不是0(當前nums[i] != 0)
     */
    public void sortColors(int[] nums) {
        int c0 = 0;
        int c2 = nums.length - 1;
        for (int i = 0; i <= c2; i++) {
            while (nums[i] == 2 && i < c2) swap(i, c2--, nums);
            while (nums[i] == 0 && i > c0) swap(i, c0++, nums);
        }
    }

    /**
     * https://leetcode.com/problems/sort-colors/discuss/26549/Java-solution-both-2-pass-and-1-pass
     */
    public void sortColors2(int[] nums) {
        int s = 0;
        int e = nums.length - 1;
        int i = 0;
        while (i <= e) {
            if (nums[i] == 0) {
                swap(i, s, nums);
                s++;
            }
            if (nums[i] == 2) {
                swap(i, e, nums);
                e--;
                i--;
            }
            i++;
        }
        log.debug("{}", nums);
    }

    public void swap(int i, int j, int[] n) {
        int t = n[i];
        n[i] = n[j];
        n[j] = t;
    }
}
