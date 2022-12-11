package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class LC656CoinPath extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC656CoinPath();
        LC.cheapestJump(new int[]{1, 2, 4, -1, 2}, 2);
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/656.Coin-Path
     * 這題如果只是單純求出走到 n 花費最少是很直觀的從 i = 0 to n 每一格往後跳 k格 看看 有沒有比較小
     * 但他因為要組出路徑, 所以當 dp[i], i 前面的 maxJump 內有 dp[x], dp[y] 都是最小, x < y 就選 x 嗎?
     * 可是 0 -> x 可能字典序 比 0 -> y 還大
     *
     * 得反過來從 n - 1 往回頭找, 計算邏輯跟 i = 0 to n 算法一樣, 只是看起來是往後累計 n-1 to 0 越累計 cost 越大,
     * dp[0] 會是總最低花費
     * 但這種方向就可以很單純的用
     * - for (int k = 1; k <= maxJump; k++) {
     * -   if (dp[i + k] + coins[i] < dp[i]) 符合就是
     * 因為 k = 1 to maxJump, k 越走越大 越只能取 <, 不能取 <=,
     * 因為 == case 雖然花費一樣 但是 i+k 字典序 後面的輸給前面
     *
     * 另一個寫法是
     * - for (int k = maxJump; k >= 1; k--) {
     * -   if (dp[i + k] + coins[i] <= dp[i])
     * 因為 k 越走越小, 所以 == case 雖然花費一樣, 但 i+k 字典序 上面贏
     *
     * 最後就是把 紀錄的 path 取出來
     * 中途如果遇到 -1, 就代表 根本走不到
     * */
    public List<Integer> cheapestJump(int[] coins, int maxJump) {
        int n = coins.length;
        int[] dp = new int[n];
        int[] path = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        Arrays.fill(path, -1);
        dp[n-1] = coins[n-1];
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= maxJump; k++) {
                if (i + k >= n) continue;
                if (coins[i + k] == -1) continue;
                if (dp[i + k] + coins[i] < dp[i]) {
                    dp[i] = dp[i + k] + coins[i];
                    path[i] = i + k;
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        int i = 0;
        while (i < n) {
            res.add(i + 1);
            if (i == n - 1) break;
            if (path[i] == -1) return new ArrayList<>();
            else i = path[i];
        }
        return res;
    }
}
