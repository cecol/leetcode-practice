package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class LC514FreedomTrail extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC514FreedomTrail();
    }

    /**
     * https://leetcode.com/problems/freedom-trail/solutions/98898/easy-to-understand-dfs-solution/
     * 答案很簡短 沒有什麼解釋...
     * 1. 準備 dp HashMap<String, Integer>, key = keyIdx + ":" + ringIdx,
     * - value 等於 當前這個 keyIdx 配上 ringIdx 最終走到 key.last 的最短距離
     * 2. 所以 dfs 下去就是起始狀態 keyIdx = 0, ringIdx = 0,
     * 所以針對當前的 [0,0], 下去走過 i = 0 tn n of char[] rings
     * 只要 keys[keyIdx] == rings[i] 就是一個可以考慮的結果 -> 下去 dfs 看看回來結果
     * 所以當前的 keyIdx 配上 ring[i] 所產生的低消 diff = 帶進來的 ringIdx 跟 i 距離(選左轉右轉哪個近的)
     * 然後 低消 diff 加上 繼續走到底 dfs(keyIdx + 1, i) 看看走完結果, 看是否是 minDist
     * 然後回存 dp[keyIdx][ringIdx]
     * */
    public int findRotateSteps(String ring, String key) {
        char[] rings = ring.toCharArray();
        char[] keys = key.toCharArray();
        return dfs(rings, keys, 0, 0, new HashMap<String, Integer>()) + key.length();
    }

    int dfs(char[] rings, char[] keys, int keyIdx, int ringIdx, HashMap<String, Integer> dp) {
        if (keyIdx == keys.length) return 0;
        String key = keyIdx + ":" + ringIdx;
        if (dp.containsKey(key)) return dp.get(key);
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < rings.length; i++) {
            if (rings[i] != keys[keyIdx]) continue;
            int diff = Math.abs(i - ringIdx);
            int dist = Math.min(diff, rings.length - diff) + dfs(rings, keys, keyIdx + 1, i, dp);
            minDist = Math.min(minDist, dist);
        }
        dp.put(key, minDist);
        return minDist;
    }
}
