package leetcode_old;

/**
 * lee code question address
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/#/description
 * too easy
 */
public class SearchInRotatedSortedArrayII {
    public static void main(String[] args) {
    }

    public boolean search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return true;
        }
        return false;
    }
}