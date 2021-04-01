package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC424LongestRepeatingCharacterReplacement extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC424LongestRepeatingCharacterReplacement();
        LC.characterReplacement("AABABBA", 1);
    }

    /**
     * https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91271/Java-12-lines-O(n)-sliding-window-solution-with-explanation
     * 這題是用two pointer 去 maintain sliding window, i to j
     * 其中找到最大window -> window 中字元x出現最多次 + 非x字元頂多出現k次
     * 找到最大window, 透過換k個字元, 該window 就全部都是同一字元,
     * k 就是能換的
     * 基本上就是要記載目前window i->j 中 出現得最多字元的 maxCount
     * 第一個pointer j 就是從0走到最後的的字元, 盡可能擴大window, 直到爆表, 爆後i 就得往前走
     * 如果 j - i + 1 - maxCount 的語意就是 剩下個字元不是多少不是 maxCount 字元
     * 如果剩下的不是 maxCount 字元大於k 代表目前window 爆表
     * window要縮表 第二個pointer i 要開始往前移
     * -> 我以為縮表時候, 因為 count[s.charAt(i) - 'A']--;
     * -> 所以 maxCount應該會改變, 應該還要重找maxCount, 但事實上不用, 因為如果 count[s.charAt(i) - 'A']--;
     * -> 是剛好減到 maxCount當前字元, 雖然可能maxCount會變, 但因為縮表後就會滿足 j - i + 1 - maxCount > k
     * -> 後來發現縮表的 while 可以改成 if就好, 代表這個邏輯的縮表是只要遇到就會剛好只大於k 一次而已
     * -> 要馬縮掉 maxCount 要馬縮掉其它字元, 怎樣算只要縮一次就會滿足 j - i + 1 - maxCount <= k 可以繼續擴表
     * <p>
     * 針對 maxCount 真正的理解:
     * 其實後面有解釋到 因為縮表後的 maxCount 是可能不合法的, 縮表後的 macCount 就可能不是當前window的maxCount
     * 只是因為前面合法過
     * maxCount may be invalid at some points, but this doesn't matter, because it was valid earlier in the string,
     * and all that matters is finding the max window that occurred anywhere in the string. Additionally,
     * it will expand if and only if enough repeating characters appear in the window to make it expand.
     * So whenever it expands, it's a valid expansion.
     * (這段只說maxCount後續事不是合法不是重點了)
     * <p>
     * Since we are only interested in the longest valid substring, our sliding windows need not shrink,
     * even if a window may cover an invalid substring.
     * We either grow the window by appending one char on the right, or shift the whole window to the right by one.
     * And we only grow the window when the count of the new char exceeds the historical max count (from a previous window that covers a valid substring).
     * <p>
     * That is, we do not need the accurate max count of the current window;
     * we only care if the max count exceeds the historical max count; and that can only happen because of the new char.
     * 這段我的理解是, 因為是要最長的合法子字串, 如果整個字串中在前段就出現最長合法只字串, 後續一直走下去的 maxCount都是 historical max count
     * 那麼走到底, maxCount都不是當前window的maxCount, 除非是最長子字串在更後面, 代表有字元出現的次數大於 historical max count
     * 那麼才會再刷新當前找到的 最長的合法子字串,
     * 所意思是說當 sliding window確實在字串結束前, 已經找到答案了, 那麼後面的字元都只是帶過(邊走邊縮表), maxCount都會卡在前面答案的maxCount
     *
     * 其實這個解答
     * https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91285/Sliding-window-similar-to-finding-longest-substring-with-k-distinct-characters
     * 在if (j - i + 1 - maxCount > k) 縮表中有特地去找當前的 maxCount
     * 答案也是依樣, 所以證實當maxCount出現過一次後也正是答案那次, 後續都不重要了
     */
    public int characterReplacement(String s, int k) {
        log.debug(s);
        int len = s.length();
        int[] count = new int[26];
        int i = 0, res = 0, maxCount = 0;
        for (int j = 0; j < len; j++) {
            count[s.charAt(j) - 'A']++;
            maxCount = Math.max(maxCount, count[s.charAt(j) - 'A']);
            if (j - i + 1 - maxCount > k) {
                count[s.charAt(i) - 'A']--;
                i++;
            }
            log.debug("maxCount:{}", maxCount);
            log.debug(s.substring(i, j + 1));
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
