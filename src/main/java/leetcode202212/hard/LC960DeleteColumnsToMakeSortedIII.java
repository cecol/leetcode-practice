package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC960DeleteColumnsToMakeSortedIII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC960DeleteColumnsToMakeSortedIII();
    }

    /**
     * https://leetcode.com/problems/delete-columns-to-make-sorted-iii/solutions/205669/java-dp-longest-increasing-subsequence/
     * 想不到這題其實是 LIS 觀念  只是
     * 1. 返過來看, 刪掉最少成為遞增 -> 就是取得最長 LIS
     * 2. 因為每個 string 都要符合, 所以 LIS 的結果是跨所有 String
     * O(n*n*k), k = str.length, n = str[0].length()
     *
     * 所以一樣 i = 1 to n
     * 針對 j = 0 to i, 看看是否每個 strs 都是 str[k].charAt(i) < str[k].charAt(j)
     * 是的話才可以 dp[i] = dp[j]+1 -> 記得取最大
     *
     * 最後得到最長的 max , n - max 就是最少要刪除的 使其 subsequence 是遞增
     * */
    public int minDeletionSize(String[] strs) {
        int n = strs[0].length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int mx = 1;
        for (int i = 1; i < n; i++) {

            for (int j = i - 1; j >= 0; j--) {
                boolean mark = true;
                for (String k : strs) {
                    if (k.charAt(i) < k.charAt(j)) {
                        mark = false;
                        break;
                    }
                }
                if (mark) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            mx = Math.max(mx, dp[i]);
        }
        return n - mx;
    }
}
