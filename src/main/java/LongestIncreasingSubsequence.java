import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public static void main(String[] a) {
        System.out.print(lengthOfLIS2(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    public static int lengthOfLIS(int[] a) {
        if (a == null || a.length == 0) return 0;
        int[] s = new int[a.length + 1];
        int max = 1;
        for (int i = 1; i < s.length; i++) s[i] = max;
        for (int i = 1; i < s.length; i++) {
            for (int j = i; j > 0; j--) {
                if (a[i - 1] > a[j - 1]) {
                    if (s[j] + 1 > s[i]) {
                        s[i] = s[j] + 1;
                        if (s[i] > max) max = s[i];
                    }
                }
            }
        }
        return max;
    }

    public static int lengthOfLIS2(int[] a) {
        int[] dp = new int[a.length];
        int len = 0;

        for (int x : a) {
            int i = Arrays.binarySearch(dp, 0, len, x);
            if (i < 0) i = -(i + 1);
            dp[i] = x;
            if (i == len) len++;
        }
        return len;
    }
}
