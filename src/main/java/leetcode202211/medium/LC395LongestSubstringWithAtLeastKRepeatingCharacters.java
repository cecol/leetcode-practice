package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC395LongestSubstringWithAtLeastKRepeatingCharacters extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC395LongestSubstringWithAtLeastKRepeatingCharacters();
    }

    /**
     * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/discuss/87738/Java-20-lines-very-easy-solution-7ms-with-explanation
     * 很不值觀的 two pointer
     * 用遞迴 divid and conquer
     * 1. 先計數每個字元出現次數
     * 2. 如果所有字元計數 > k, 整個 s 是解答
     * 如果有字元計數 < k -> 任何 substring 都不該包含該字元
     * 所以用這些不合法字元當作邊界下去切,
     * i, j two pointer 下去切出 substring -> substring 遞迴找 longestSubstring
     */
    public int longestSubstring(String s, int k) {
        int[] cc = new int[26];
        for (char c : s.toCharArray()) cc[c - 'a']++;
        boolean all = true;
        for (int i = 0; i < 26; i++) if (cc[i] > 0 && cc[i] < k) all = false;

        if (all) return s.length();
        int res = 0;
        int i = 0, j = 0;
        for (; j < s.length(); j++) {
            if (cc[s.charAt(j) - 'a'] < k && cc[s.charAt(j) - 'a'] > 0) {
                res = Math.max(res, longestSubstring(s.substring(i, j), k));
                i = j+1;
            }
        }
        res = Math.max(res, longestSubstring(s.substring(i, j), k));
        return res;
    }
}
