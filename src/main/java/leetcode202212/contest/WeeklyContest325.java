package leetcode202212.contest;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;


public class WeeklyContest325 extends BasicTemplate {
    public static void main(String[] args) throws IOException {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new WeeklyContest325();
//        LC.closetTarget(
//                new String[]{"hsdqinnoha", "mqhskgeqzr", "zemkwvqrww", "zemkwvqrww", "daljcrktje", "fghofclnwp", "djwdworyka", "cxfpybanhd", "fghofclnwp", "fghofclnwp"},
//                "zemkwvqrww",
//                8);
        LC.takeCharacters("aabaaaacaabc", 2);

    }

    public int closetTarget(String[] words, String target, int startIndex) {
        boolean match = false;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) {
                match = true;
                break;
            }
        }
        if (!match) return -1;
        int l = 0;
        for (int i = startIndex; !words[i].equals(target); i = (i + 1) % words.length) {
            l++;
        }

        int r = 0;
        for (int i = startIndex; !words[i].equals(target); ) {
            i = i - 1;
            if (i < 0) i = words.length - 1;
            r++;
        }
        return Math.min(l, r);
    }

    public int takeCharacters(String s, int k) {
        if (k == 0) return 0;
        int n = s.length();
        if (n < k * 3) return -1;
        int[][] c1 = new int[n + 1][3];
        int[][] c2 = new int[n + 1][3];
        c1[0] = new int[]{0, 0, 0};
        c2[n] = new int[]{0, 0, 0};
        for (int i = 0; i < n; i++) {
            char cc = s.charAt(i);
            c1[i + 1] = Arrays.copyOf(c1[i], 3);
            c1[i + 1][cc - 'a']++;
        }
        for (int i = n - 1; i >= 0; i--) {
            char cc = s.charAt(i);
            c2[i] = Arrays.copyOf(c2[i + 1], 3);
            c2[i][cc - 'a']++;
        }
        if (c1[n][0] < k || c1[n][1] < k || c1[n][2] < k) return -1;

        int l = 1, r = n;
        while (l < r) {
            int mid = l + (r - l) / 2;
            boolean can = match(c1, c2, mid, k);
            if (can) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    boolean match(int[][] c1, int[][] c2, int len, int k) {
        for (int i = 0; i <= len; i++) {
            int c2Idx = c2.length - (len - i) - 1;
            if (c1[i][0] + c2[c2Idx][0] >= k && c1[i][1] + c2[c2Idx][1] >= k && c1[i][2] + c2[c2Idx][2] >= k)
                return true;
        }
        return false;
    }


    long res = 0;

    public int countPartitions(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        boolean[] v = new boolean[n];
        int total = 0;
        for (int i : nums) total += i;
        dfs(nums, v, k, 0, total);
        return (int) res;
    }

    void dfs(int[] nums, boolean[] v, int k, int sum, int total) {
        if (sum >= k && total - sum >= k) {
            res = (res + 1) % ((int) 1e9 + 7);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!v[i]) {
                v[i] = true;
                dfs(nums, v, k, sum + nums[i], total);
                v[i] = false;
            }
            dfs(nums, v, k, sum, total);
        }
    }

}