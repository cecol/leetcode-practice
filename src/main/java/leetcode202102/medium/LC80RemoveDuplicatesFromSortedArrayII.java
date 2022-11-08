package leetcode202102.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LC80RemoveDuplicatesFromSortedArrayII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC80RemoveDuplicatesFromSortedArrayII();
        var s = LC.removeDuplicates(new int[]{1, 1, 1, 2, 2, 3});
        var s2 = LC.removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3});
        var s3 = LC.removeDuplicates(new int[]{1, 1, 1});
    }

    /**
     * 這題自己想了半天 處理一堆有的沒的 就是沒想到可以這麼簡單...
     * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/discuss/27976/3-6-easy-lines-C%2B%2B-Java-Python-Ruby
     * 快慢 pointer, 關鍵是比較 slow pointer 那一個
     * */
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) nums[i++] = n;
        }
        return i;
    }
}
