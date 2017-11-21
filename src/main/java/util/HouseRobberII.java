package util;

public class HouseRobberII {
    public static void main(String[] a) {
        int[] n1 = {99, 11, 11, 99, 11};
        System.out.println(rob(n1));

    }

    public static int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(rob1(nums, 1, nums.length - 1), rob1(nums, 0, nums.length - 2));
    }

    public static int rob1(int[] nums, int start, int end) {
        int i = 0;
        int e = 0;
        for (int j = start; j <= end; j++) {
            int t = e;
            e = Math.max(i, e);
            i = nums[j] + t;
        }
        return Math.max(i, e);
    }
}
