package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LC31NextPermutation extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC31NextPermutation();
    }


    /**
     * https://leetcode.com/problems/next-permutation/discuss/13872/Easiest-JAVA-Solution
     * java 解
     * 比較仔細解釋
     * https://leetcode.com/problems/next-permutation/discuss/13866/Share-my-O(n)-time-solution
     * 1. 從尾巴找第一組遞增 nums[i-1] < nums[i] -> 所以 i to n-1 都是遞減 -> 所以i to n-1不能找到下一個 lexicographically next greater
     * 2. 要找適合 swap的得從最右邊開始找, 找到 nums[j] > nums[i]
     * -> to minimize the increased amount,
     * -> we want to increase the number at index i-1,
     * -> clearly, swap it with the smallest number between num[i,n-1] that is larger than num[i-1]
     * 3. 最後要reverse [i, n-1] -> 因為他們是遞減, 所以reverse後就變成最小, 那你當前的這一個就是 lexicographically next greater
     * -> The last step is to make the remaining higher position part as small as possible,
     * -> we just have to reversely sort the num[i,n-1]
     * */
    public void nextPermutation(int[] nums) {
        "".split("", 1);
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

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    private void rev(int[] nums, int i, int j) {
        while (i < j) swap(nums, i++, j--);
    }
}
