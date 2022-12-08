package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC351AndroidUnlockPatterns extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC351AndroidUnlockPatterns();
    }

    /**
     * https://leetcode.com/problems/android-unlock-patterns/discuss/82463/Java-DFS-solution-with-clear-explanations-and-optimization-beats-97.61-(12ms)
     * 一看到九宮格, 還要想各種可能走法 就腦袋混亂了
     * 直到看到解答, 好好跟著走一遍才理解
     * 1. 基本概念還是 DFS, 只是要處理跳耀格問題
     * - 所以 DFS 從每一格下去走, 比如說先從 1, 然後接著看能選 1-9 哪些當下一個
     * - 所以要帶
     * - 1. boolean[] visited -> 走過不能走 -> inf DFS: if(!visited[i]
     * - 2. int[][] skip 記載有哪些跳格, 且跳走誰?, Ex: skip[1][3] = 2, 就是說 從 1 跳 3 會跳過過 2
     * -    所以要先看看 2 是否走過才可以 1 跳 3
     * -    所以 DFS 裏面能不能走邏輯是
     * -    1. skip[cur][i] == 0 代表兩格之間沒跳 又 visited[i] == false -> 可以走
     * -    2. 或者 visited[skip[cur][i]] -> 有跳格, 但被跳格已被走過, 所以可以跳走！！
     * 3. 最後就是 DFS 開頭不用從 1,2..,9 一個個下去走, 只要
     * - res += dfs(visited, skip, 1, i - 1) * 4; -> 1,3,7,9 對稱, 每個當起點得數量是一致的
     * - res += dfs(visited, skip, 2, i - 1) * 4; -> 2,4,6,8 對稱, 每個當起點得數量是一致的
     * - res += dfs(visited, skip, 5, i - 1) ; -> 5 不跟誰對稱, 所以走法得自己來
     */
    public int numberOfPatterns(int m, int n) {
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        boolean[] visited = new boolean[10];
        int res = 0;
        for (int i = m; i <= n; i++) {
            res += dfs(visited, skip, 1, i - 1) * 4;
            res += dfs(visited, skip, 2, i - 1) * 4;
            res += dfs(visited, skip, 5, i - 1);
        }
        return res;
    }

    int dfs(boolean[] visited, int[][] skip, int cur, int remain) {
        if (remain < 0) return 0;
        if (remain == 0) return 1;
        visited[cur] = true;
        int res = 0;
        for (int i = 1; i <= 9; i++) {
            if (!visited[i] && (skip[cur][i] == 0 || visited[skip[cur][i]])) {
                res += dfs(visited, skip, i, remain - 1);
            }
        }
        visited[cur] = false;
        return res;

    }
}
