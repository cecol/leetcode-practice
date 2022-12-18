package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class LC691StickersToSpellWord extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC691StickersToSpellWord();
    }

    /**
     * https://leetcode.com/problems/stickers-to-spell-word/solutions/108318/c-java-python-dp-memoization-with-optimization-29-ms-c/
     * 覺得 wisdompeak 把這題放在 状态压缩DP 只是因為可以用 bitmap 來表達 DP 進展
     * 但這不是必要的, 這題有更直白的 DP 紀錄方式
     *
     * 這題是是在找 最少 Stickers 組出 target
     * 字元順序 sticker 能用幾張, sticker 都不是限制
     * 所以其實 sticker 要簡化成 int[][] stickerCharCount, 我們只需要知道 sticker[i] 能貢獻多少字元
     * 然後剩下未貢獻的 dfs 下去繼續找
     *
     * 1. 所以準備 HashMap<String, Integer> dp, dp[String] 就代表 要多少 min sticker 可以組出當前 String
     * 2. 還有 int[][] stickerCharCount - sticker[i] 能貢獻多少字元
     * 3 dfs(dp, stickerCharCount,  target)
     * - 進入 dfs, 先算 int[] tarCount, 先知道 target 需要多少 char,
     * - for i = 0 to all stickers,
     * -    當前 sticker 用了之後, target 還剩下多少字元？
     * -    就是針對 j = 0 to 26, 每個下去檢查, 如果 tarCount[j] > 0, 就減去 sticker[i], 看剩下 String 長怎樣
     * -    組出剩下字串 String sb, 拿剩下 String 繼續 dfs 回來用多少 sticker 組成
     * -    for (int k = 0; k < Math.max(0, tarCount[j] - stickerCharCount[i][j]);
     * -       就是在說 當前 sticker[i] 用了, 該字元 j 還剩下多少要拿去 dfs 繼續找
     * -       tarCount[j] > stickerCharCount[i][j]) -> 會拿去 剩下字串
     * -       tarCount[j] <= stickerCharCount[i][j]) -> 不會拿去 剩下字串
     * -
     *
     * - if (stickerCharCount[i][target.charAt(0) - 'a'] == 0) continue;
     * -    只是個優化, 這個 sticker[i] 要被考慮 至少要有 target(0) 字元吧, 沒有的話 dfs 會很多重複(這個 sticker 根本沒幫助)
     *
     * - int res = Integer.MAX_VALUE 開始設置成 0
     * - 只有 dfs 回來 tmp != -1 才會更新, 這個是用來設置找不到的 case
     * */
    public int minStickers(String[] stickers, String target) {
        int m = stickers.length;
        int[][] stickerCharCount = new int[m][26];
        HashMap<String, Integer> dp = new HashMap<>();
        for (int i = 0; i < m; i++)
            for (char c : stickers[i].toCharArray()) stickerCharCount[i][c - 'a']++;
        dp.put("", 0);
        return dfs(dp, stickerCharCount, target);
    }

    int dfs(HashMap<String, Integer> dp, int[][] stickerCharCount, String target) {
        if (dp.containsKey(target)) return dp.get(target);
        int res = Integer.MAX_VALUE, n = stickerCharCount.length;
        int[] tarCount = new int[26];
        for (char tc : target.toCharArray()) tarCount[tc - 'a']++;

        for (int i = 0; i < n; i++) {
            if (stickerCharCount[i][target.charAt(0) - 'a'] == 0) continue;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (tarCount[j] > 0) {
                    for (int k = 0; k < Math.max(0, tarCount[j] - stickerCharCount[i][j]); k++) {
                        sb.append((char) ('a' + j));
                    }
                }
            }
            String s = sb.toString();
            int tmp = dfs(dp, stickerCharCount, s);
            if (tmp != -1) res = Math.min(res, tmp + 1);
        }
        dp.put(target, res == Integer.MAX_VALUE ? -1 : res);
        return dp.get(target);
    }
}
