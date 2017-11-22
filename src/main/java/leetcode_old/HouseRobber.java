package leetcode_old;

public class HouseRobber {
    public static void main(String[] a) {
        int[] n1 = {99, 11, 11, 99};
        System.out.println(rob(n1));

    }

    public static int rob(int[] nums) {
        int i = 0;
        int e = 0;
        for (int n : nums) {
            int t = e;
            e = Math.max(i, e);
            i = n + t;
        }
        return Math.max(i, e);
    }
}
