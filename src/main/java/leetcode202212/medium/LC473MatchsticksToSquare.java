package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Stack;

public class LC473MatchsticksToSquare extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC473MatchsticksToSquare();
    }

    /**
     * 原來這類的 partition problem (or number partitioning) 是 NP Complete
     * https://blog.csdn.net/huang1024rui/article/details/49154507
     * P = polynomial time, O(1) O(N), O(N^x) 都是 polynomial time
     * 但 O(a^N), O(N!) 就不是 polynomial time
     * https://bluelove1968.pixnet.net/blog/post/222283186-%E8%AB%96p%2Cnp%2Cnp-hard%2Cnp-complete%E5%95%8F%E9%A1%8C
     * P: 有 polynomial time 的解的問題
     * NP: 還沒找到 polynomial time 解法, 但隨意給答案可以在 polynomial time 內驗證是否正確
     * (應該是指答案的集合 size 就是 polynomial)
     * NP complete - NP 所有問題中最難, 如果 NPC 有解, NP 就有解
     * - 看起來是 NP 裏面最難的那一群
     * NP Hard - 既沒有 polynomial time 解法, 隨意給一個答案也不見得在 polynomial time 內驗證是否正確
     * <p>
     * 既然是 NPC, 所以就是直觀的 DFS 下去
     * 只要看到排列組合問題基本上都是 NPC (因為 N!), 可以注意到給的 input range 都很小
     * https://leetcode.com/problems/matchsticks-to-square/discuss/95729/Java-DFS-Solution-with-Explanation
     * 題目有說 1 <= matchsticks.length <= 15
     * 看起來就是所有組合都下去嘗試找答案了
     *
     * 這題有個特點是
     * sort array to DESC, 大開始找會比較快找到結果
     * 所以 sort nums, 且從尾巴開始下去找
     */
    public boolean makesquare(int[] nums) {
        if (nums.length < 4) return false;
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % 4 != 0) return false;
        Arrays.sort(nums);
        return dfs(nums, new int[4], nums.length - 1, sum / 4);
    }

    boolean dfs(int[] nums, int[] cur, int idx, int t) {
        if (idx == -1) {
            if (cur[0] == t && cur[1] == t && cur[2] == t) return true;
            return false;
        }
        for (int i = 0; i < 4; i++) {
            if (cur[i] + nums[idx] > t) continue;
            cur[i] += nums[idx];
            if (dfs(nums, cur, idx - 1, t)) return true;
            cur[i] -= nums[idx];
        }
        return false;
    }
}
