package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC465OptimalAccountBalancing extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC465OptimalAccountBalancing();
    }


    /**
     * https://leetcode.com/problems/optimal-account-balancing/solutions/390023/java-solution/
     * 這題是 DP 題目  但我沒找到我可以看懂的 DP 解法
     * https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/465.Optimal-Account-Balancing
     * 這是經典 NP-complete 題目, 我查了下看不出來哪裡經典 可能因為也是要分群吧 分群轉移帳戶導致最後都沒負債
     *
     * 這種題目就是 DFS or DP, DP 我沒看懂
     * 看題意 0 <= from i, toi < 12, 範圍只有 12 人
     * 所以 DFS 是解的出來, 不需要優化剪枝
     *
     * 先把所有人最後 account 狀態算出來,
     * dfs(start, acc)
     * - while acc.get(start) == 0, start++ -> 2; 當前這個人平衡了 別理他
     * - 接下來就是 for(i = start + 1) 往後面找,
     * -   if (acc.get(start) * acc.get(i) < 0) -> 這兩人可戶消 (1正1負)
     * -       acc.set(i, acc.get(i) + acc.get(start)); 設定 acc(i) 互消完結果
     * -       min = Math.min(min, dfs(start + 1, acc) + 1); 繼續 dfs, 從 start 下去,
     * -             因為 當前 acc(start) 跟 acc(i) 互消已變成 0
     * -       acc.set(i, acc.get(i) - acc.get(start)); - 把 acc(i) 調整回來, 下一圈 i+1, dfs
     */
    public int minTransfers(int[][] transactions) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] t : transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
        }
        return dfs(0, new ArrayList<>(map.values()));
    }

    int dfs(int start, List<Integer> acc) {
        while (start < acc.size() && acc.get(start) == 0) start++;
        if (start == acc.size()) return 0;
        int min = Integer.MAX_VALUE;
        for (int i = start + 1; i < acc.size(); i++) {
            if (acc.get(start) * acc.get(i) < 0) {
                acc.set(i, acc.get(i) + acc.get(start));
                min = Math.min(min, dfs(start + 1, acc) + 1);
                acc.set(i, acc.get(i) - acc.get(start));
            }
        }
        return min;
    }
}
