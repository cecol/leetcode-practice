package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC424LongestRepeatingCharacterReplacement extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC424LongestRepeatingCharacterReplacement();
        LC.characterReplacement("ABAB", 2);
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
     * -> 此時就跳出縮表過程 j 就繼續往前走, maxCount會重計
     * -> 後來發現縮表的 while 可以改成 if就好, 代表這個邏輯的縮表是只要遇到就會剛好只大於k 一次而已
     * -> 要馬縮掉 maxCount 要馬縮掉其它字元, 怎樣算只要縮一次就會滿足 j - i + 1 - maxCount <= k 可以繼續擴表
     */
    public int characterReplacement(String s, int k) {
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
            res = Math.max(res, j - i + 1);
        }
        return res;
    }
}
