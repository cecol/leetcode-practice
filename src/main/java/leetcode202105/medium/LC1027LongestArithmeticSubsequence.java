package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC1027LongestArithmeticSubsequence extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1027LongestArithmeticSubsequence();
        LC.longestArithSeqLength(new int[]{
                22, 8, 57, 41, 36, 46, 42, 28, 42, 14, 9, 43, 27, 51, 0, 0, 38, 50, 31, 60, 29, 31,
                20, 23, 37, 53, 27, 1, 47, 42, 28, 31, 10, 35, 39, 12, 15, 6, 35, 31,
                45, 21, 30, 19, 5, 5, 4, 18, 38, 51, 10, 7, 20, 38, 28, 53, 15, 55, 60,
                56, 43, 48, 34, 53, 54, 55, 14, 9, 56, 52});
    }

    /**
     * 我知道是DP. 其實看起來跟找出最長遞增sub sequence 很像，只是這個是要找出區間差值一致的 sub sequence
     * 我用 Map<Integer, Map<Integer, Integer>> dp = new HashMap<>(); 去記載 但就是有 test case 不會過
     * 最大關鍵是在 inner loop 找比當前i 前面的 sub sequence
     * 1. j = 0, j < i -> 很多人這樣做都會過, 也沒解釋為什麼
     * 2. j = i-1, j>=0 -> 就會錯
     * https://leetcode.com/problems/longest-arithmetic-subsequence/discuss/332097/JAVA-DP-O(n2)-no-HashMap-77ms-(beat-91.66)
     * -> 總算理解 j 從 0 -> i-1; 與 j 從 i-1 -> 0, 的差異
     * -> 因為因為如果 j 從 i-1 -> 0, dp[i][diff] 應該要取 Math.max(dp[i][diff], dp[j][diff] + 1)
     * -> 因為檢查是從最長可能性 i-1 檢查到 0 最短可能性, 所以後面短的如果出現一樣diff 會蓋掉長的
     * -> 所以得先比較短的是否真的有比較長的 Longest Arithmetic Subsequence
     * -> 才可以去更新 dp[i][diff]
     *
     * -> 但如果是 j 從 0 -> i - 1, 是從最短可能性 0的 subsequence 檢查到 最長可能性的 subsequence i-1
     * -> 所以不會有短的覆蓋長的, 所以可以省去 Math.max(dp[i][diff], dp[j][diff] + 1) 的比較,
     * -> 後面遇到一樣的 diff只會更長, 不會更短
     *
     * -> 這題關鍵是否用完整的 DP 概念去理解 當前i的 diff sub sequence
     * -> 最安全還是用 Math.max(dp[i][diff], dp[j][diff] + 1) 來解題是最好的
     * -> 這樣不管 j 從頭走還是層尾巴走都是對的
     */
    public int longestArithSeqLength(int[] A) {
        Map<Integer, Map<Integer, Integer>> dp = new HashMap<>();
        int n = A.length;
        int res = 1;
        dp.put(0, new HashMap<>());
        for (int i = 1; i < n; i++) {
            dp.put(i, new HashMap<>());
            for (int j = 0; j < i ; j--) {
                int diff = A[i] - A[j];
                Map<Integer, Integer> jm = dp.get(j);
                int prev = jm.getOrDefault(diff, 0) + 1;
                int cur = Math.max(dp.get(i).getOrDefault(diff, 0), prev);
                dp.get(i).put(diff, cur);
                res = Math.max(res, cur);
            }
        }
        return res + 1;
    }
}
