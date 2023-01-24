package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC403FrogJump extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC403FrogJump();
    }

    /**
     * https://leetcode.com/problems/frog-jump/solutions/88806/java-dfs-memotable-beats-70/
     * 這很直觀是 DFS, 不過直接下去會 TLE, 要配上 memorization
     * 難的是 memorization 要 memorization 什麼？
     * 關鍵是 當前的 stone + 當前要跳的格數 String k = curStone + "x" + jump;
     * 然後用一個 Map<String, Boolean> can = new HashMap<>(); 來儲存
     * <p>
     * 另一個關鍵事是 dfs 的先後順序
     * 一定是先原
     * +0 跳 -> dfs(curStone + jump, jump, ts, can, t)
     * 不行才 +1 -> || dfs(curStone + jump + 1, jump + 1, ts, can, t)
     * 不行才 -1 -> || dfs(curStone + jump - 1, jump - 1, ts, can, t);
     *
     * 我卡在先 +0 步數 || -1 步數 || +1 步數
     * 導致一直 stackoverflow
     *
     * 主要原因可能是因為如果先 -1 跳, 一直遞迴下去.. 就會變成往後跳!!
     * 所以要 -1 優先就要多個判斷看 是否往回走
     */
    public boolean canCross(int[] stones) {
        TreeSet<Integer> ts = new TreeSet<Integer>();
        Map<String, Boolean> can = new HashMap<>();
        for (int n : stones) ts.add(n);
        if (stones[0] + 1 != stones[1]) return false;
        return dfs(stones[1], 1, ts, can, stones[stones.length - 1]);
    }

    boolean dfs(int curStone, int jump, TreeSet<Integer> ts, Map<String, Boolean> can, int t) {
        String k = curStone + "x" + jump;
        if (can.containsKey(k)) return can.get(k);
        if (jump <= 0 || !ts.contains(curStone)) return false;
        if (curStone == t) {
            can.put(k, true);
            return true;
        }
        boolean search = dfs(curStone + jump, jump, ts, can, t)
                || dfs(curStone + jump + 1, jump + 1, ts, can, t)
                || dfs(curStone + jump - 1, jump - 1, ts, can, t);
        can.put(k, search);
        return search;
    }
}
