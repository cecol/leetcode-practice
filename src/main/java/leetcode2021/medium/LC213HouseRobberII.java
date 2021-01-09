package leetcode2021.medium;

import leetcode20200921to20201031.BasicTemplate;

import java.util.Arrays;

public class LC213HouseRobberII extends BasicTemplate {
    public static void main(String[] args) {
        var LC = new LC213HouseRobberII();
        var s = LC.rob(new int[]{});
    }

    /**
     * https://leetcode.com/problems/house-robber-ii/discuss/59934/Simple-AC-solution-in-Java-in-O(n)-with-explanation
     * 就是很基本的第一版HouseRobberI 算2次
     * 第一次 0 不偷 -> 算到 nums.length - 1
     * 第二次 0 偷 -> 只算到 nums.length - 2
     * 取兩者的max
     * */
    public int rob(int[] nums) {
        int s = nums.length;
        if (s == 0) return 0;
        if (s == 1) return nums[0];
        int[] nr = new int[s];
        int[] r = new int[s];
        for (int i = 1; i < s; i++) {
            nr[i] = Math.max(nr[i - 1], r[i - 1]);
            r[i] = nr[i - 1] + nums[i];
        }
        int n0Max = Math.max(nr[s - 1], r[s - 1]);
        Arrays.fill(nr, 0);
        Arrays.fill(r, 0);
        r[0] = nums[0];
        for (int i = 1; i < s - 1; i++) {
            nr[i] = Math.max(nr[i - 1], r[i - 1]);
            r[i] = nr[i - 1] + nums[i];
        }
        int nnMax = Math.max(nr[s - 2], r[s - 2]);
        return Math.max(n0Max, nnMax);
    }
}
