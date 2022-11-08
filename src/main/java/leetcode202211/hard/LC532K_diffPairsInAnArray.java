package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC532K_diffPairsInAnArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC532K_diffPairsInAnArray();
    }

    /**
     * 這題有 HashMap + two pointers 2 種解法
     * two pointer: 我有想出大致概念, 但在處理重複這邊 沒處理好
     * java two pointer & hashnap
     * https://leetcode.com/problems/k-diff-pairs-in-an-array/discuss/1757692/Java-or-Easy-to-understand-orTwo-Approaches-or-Sorting-or-HashMap
     * 1. 先 sort array -> 重要前提, 這樣才可以建立 window
     * 2. i=0,j=1, sum 記載看過的重複 case(因為sort過了, 所以sum一樣一定是同一組)
     * 3. if nums[j] - nums[i] == k & sum 是新的 res++,
     * -> j++, i++ 因為當前這組window不用在看了
     * -> if i == j: j++, 確保 window
     */
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0, j = 1, n = nums.length, res = 0, sum = Integer.MAX_VALUE;
        while (j < n && i < n - 1) {
            if (nums[j] - nums[i] == k && (nums[j] + nums[i]) != sum) {
                sum = nums[i] + nums[j];
                res++;
                i++;
                j++;
            } else if (nums[j] - nums[i] > k) i++;
            else j++;
            if (i == j) j++;
        }
        return res;
    }
}
