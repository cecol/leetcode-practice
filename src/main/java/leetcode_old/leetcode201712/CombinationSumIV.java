package leetcode_old.leetcode201712;


public class CombinationSumIV {
    public static void main(String[] a) {
        System.out.println(
                combinationSum4(new int[]{3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25}, 10));
    }

    public static int combinationSum4(int[] nums, int target) {
        int[] result = new int[target + 1];
        result[0] = 1;
        int c = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j : nums) {
                if (i - j >= 0) {
                    result[i] += result[i - j];
                }
            }
        }
        return result[target];
    }
}
