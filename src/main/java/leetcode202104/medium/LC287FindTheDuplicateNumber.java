package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC287FindTheDuplicateNumber extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC287FindTheDuplicateNumber();
        LC.findDuplicate(new int[]{2, 3, 3, 1, 3});
    }

    /**
     * 因為 nums 裡面只有 1 -n, 那就用 swap 到正確位置來解 O(N) 1 ms, faster than 69.08%
     * 不過題目有近一步幾個限制
     * solution with O(n) time and O(1) space without modifying the array
     * 有 binary search 解法 https://leetcode.com/problems/find-the-duplicate-number/discuss/72841/Java-O(1)space-using-Binary-Search
     * 還有 linked list , fast/slow 解法 https://leetcode.com/problems/find-the-duplicate-number/discuss/72846/My-easy-understood-solution-with-O(n)-time-and-O(1)-space-without-modifying-the-array.-With-clear-explanation.
     * 很像 linked list 找cycle 解法, 先fast 走到跟 slow依樣 -> 然後start在走到 slow 就是 結果
     * 這是蠻有趣的
     * */
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        if(n > 1){
            int slow = nums[0];
            int fast = nums[nums[0]];
            while(slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }
            fast = 0;
            while(fast != slow) {
                fast=nums[fast];
                slow=nums[slow];
            }
            return slow;
        }
        return -1;
    }

    public int findDuplicateOld(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) if (i + 1 != nums[i]) return nums[i];
        return -1;
    }

    private void swap(int[] n, int i, int j) {
        int t = n[i];
        n[i] = n[j];
        n[j] = t;
    }
}
