package leetcode20200921to20201031.medium;

import leetcode20200921to20201031.BasicTemplate;

/**
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 * https://leetcode.com/problems/increasing-triplet-subsequence/discuss/79126/Share-my-O(n)-Java-solution-(inspired-by-Longest-Increasing-Subsequece-O(nlgn)-solution)
 * this is only a special case in "Longest Increasing Subsequence O(nlgn) solution"
 * where the length of "min ending here" sequence is at most 3.
 * <p>
 * in other words, we only need to consider:
 * minimum ending of LIS whose length is 1, and
 * minimum ending of LIS whose length is 2
 */
public class LC334IncreasingTripletSubsequence extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC334IncreasingTripletSubsequence();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Greedy/334.Increasing-Triplet-Subsequence
     * 這個解法比較直觀 針對每個 nums[i] i = 0 to n-2
     * 找出他們的 leftMin/rightMax 只要找到一組
     * leftMin[i] < nums[i] && rightMax[i] > nums[i], 就可以了
     * <p>
     * 所以算 leftMin[i]
     * - leftMin[0] = Integer.MAX_VALUE;
     * - for (int i = 1; i < n; i++) leftMin[i] = Math.min(leftMin[i - 1], nums[i - 1]);
     * <p>
     * 所以算 rightMax[i]
     * - rightMax[n - 1] = Integer.MIN_VALUE;
     * - for (int i = n - 2; i >= 0; i--) rightMax[i] = Math.max(rightMax[i + 1], nums[i + 1]);
     * -
     */
    public boolean increasingTriplet(int[] nums) {
        int n = nums.length;
        if (n < 3) return false;
        int[] leftMin = new int[n];
        leftMin[0] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) leftMin[i] = Math.min(leftMin[i - 1], nums[i - 1]);
        int[] rightMax = new int[n];
        rightMax[n - 1] = Integer.MIN_VALUE;
        for (int i = n - 2; i >= 0; i--) rightMax[i] = Math.max(rightMax[i + 1], nums[i + 1]);
        for (int i = 1; i < n - 1; i++) {
            if (leftMin[i] < nums[i] && rightMax[i] > nums[i]) return true;
        }
        return false;
    }

    public static boolean increasingTripletOld(int[] nums) {
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
