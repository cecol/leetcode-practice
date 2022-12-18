package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC174DungeonGame extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC174DungeonGame();
    }

    /**
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/174.Dungeon-Game
     * 這題確實不難, 但想法要轉一下
     * 1. 我們求的是 最小生命值走到 dungeon[m][n], 所以要從 [m,n] 回頭累計最小血量走到 [0,0]
     * 2. 不可以欠命, 就是說後面有補的, 不代表現在可以生命值變負的
     * 3. 因為是 [m,n] 回頭走到 [0,0], 所以 [i,j] 是從 [i+1, j], [i,j+1] 算回來 代表 dp[i][j] 走向 [i+1,j] or [i,j+1]
     * - dp[i][j] 是走到 dp[i][j] 時剩下多少命, 所以一開始 dp[m][n] = 1 走到終點時候 剛好剩 1 滴
     * - dp[m][n] = 1 這個一滴開始回推,
     * - 又 dp[i][j] 要最少生命 - 所以是 dp[i][j] = Math.min(dp[i][j + 1] - dungeon[i][j + 1], dp[i + 1][j] - dungeon[i + 1][j]);
     * - 為什麼是減法? dungeon[i][j + 1] 如果是負的, dp[i][j + 1] 是走到這剩下多少生命可以再往後走
     * - dp[i][j] = dp[i][j + 1] - dungeon[i][j + 1] => 加上損血值 使其可以 dp[i][j] 走向 dungeon[i][j + 1] 還剩下 dp[i][j + 1] 繼續往後走
     * - 不可以欠命, 就是說後面有補的, 不代表現在可以生命值變負的 這邊是在 dp[i][j] = Math.max(dp[i][j], 1); 把它恢復成 1
     * - 如果 dungeon[i][j + 1] 是補血, 那麼  dp[i][j] = dp[i][j + 1] - dungeon[i][j + 1] 會是負值, 帶表 dp[i][j] 是欠命往後走
     * - 但不允許, 所以 dp[i][j] 至少要 1, 因為 dp[i][j] 還活著剩多少血
     * 4. 最後要補回 dungeon[0][0] 的損血
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m][n];

        for (int i = m - 1; i >= 0; i--)
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) dp[i][j] = 1;
                else if (i == m - 1) dp[i][j] = dp[i][j + 1] - dungeon[i][j + 1];
                else if (j == n - 1) dp[i][j] = dp[i + 1][j] - dungeon[i + 1][j];
                else {
                    dp[i][j] = Math.min(dp[i][j + 1] - dungeon[i][j + 1], dp[i + 1][j] - dungeon[i + 1][j]);
                }
                dp[i][j] = Math.max(dp[i][j], 1);
            }
        dp[0][0] = dp[0][0] - dungeon[0][0];
        dp[0][0] = Math.max(dp[0][0], 1);
        return dp[0][0];
    }
}
