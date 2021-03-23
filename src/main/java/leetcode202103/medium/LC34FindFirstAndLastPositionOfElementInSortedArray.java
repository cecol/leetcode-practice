package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC34FindFirstAndLastPositionOfElementInSortedArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC34FindFirstAndLastPositionOfElementInSortedArray();
//        LC.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        LC.searchRange(new int[]{1, 1, 2}, 1);
    }


    /**
     * 自己有解開, faster than 100.00% of Java online, less than 96.51% of Java online
     * 但花很多時間才debug出怎理解 Arrays.binarySearch(fromIndex, toIndex) 來解這題
     * */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int idx = Arrays.binarySearch(nums, target);
        if (idx < 0) return new int[]{-1, -1};
        int lbr = idx;
        while (Arrays.binarySearch(nums, 0, lbr, target) >= 0) {
            lbr = Arrays.binarySearch(nums, 0, lbr, target);
        }

        int rbr = idx;
        while (Arrays.binarySearch(nums, rbr + 1, nums.length, target) >= 0) {
            rbr = Arrays.binarySearch(nums, rbr + 1, nums.length, target);
        }

        int[] res = new int[]{lbr, rbr};
        log.debug("idx:{}, res:{}", idx, res);
        return res;
    }
}
