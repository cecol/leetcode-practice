package leetcode_old;

/**
 * lee code question address
 * https://leetcode.com/problems/search-in-rotated-sorted-array/#/description
 * have nothing to talk about... too easy
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
    }

    public int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) return i;
        }
        return -1;
    }
}
