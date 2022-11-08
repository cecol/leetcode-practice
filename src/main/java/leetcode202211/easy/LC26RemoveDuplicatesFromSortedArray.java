package leetcode202211.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC26RemoveDuplicatesFromSortedArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC26RemoveDuplicatesFromSortedArray();
    }

    /**
     * leetcode 是 easy, 但 wisdompeak 上是 H-
     * https://github.com/wisdompeak/LeetCode/tree/master/Two_Pointers/026.Remove-Duplicates-from-Sorted-Array
     * 很直觀的快慢 two pointer,
     * slow one 是要填的
     * fast one 找到非重複拿去填 slow one
     * */
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) nums[k++] = nums[i];
        }
        return k;
    }
}
