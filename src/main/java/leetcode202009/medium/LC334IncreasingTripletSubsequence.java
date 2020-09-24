package leetcode202009.medium;

/**
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * https://leetcode.com/problems/increasing-triplet-subsequence/discuss/79126/Share-my-O(n)-Java-solution-(inspired-by-Longest-Increasing-Subsequece-O(nlgn)-solution)
 * this is only a special case in "Longest Increasing Subsequence O(nlgn) solution"
 *     where the length of "min ending here" sequence is at most 3.
 *
 *     in other words, we only need to consider:
 *     minimum ending of LIS whose length is 1, and
 *     minimum ending of LIS whose length is 2
 */
public class LC334IncreasingTripletSubsequence {
    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[]{9, 4, 2, 8, 1, 5, 7, 0}));
    }

    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int s1 = Integer.MAX_VALUE;
        int s2 = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n <= s1) {
                s1 = n;
            } else if (n <= s2) {
                s2 = n;
            } else {
                return true;
            }
        }
        return false;
    }
}
