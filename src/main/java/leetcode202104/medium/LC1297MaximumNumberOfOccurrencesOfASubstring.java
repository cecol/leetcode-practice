package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC1297MaximumNumberOfOccurrencesOfASubstring extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1297MaximumNumberOfOccurrencesOfASubstring();
    }

    /**
     * https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/discuss/888643/Java-easy-to-understand-solution-O(n)
     * 這題我原本想說用 sliding window來解, 但對於比較短的字串我沒有方法去找出來
     * 後來就用暴力法解, 會過但很慢 faster than 19.46%
     * 關鍵在於
     * 與其討論是否 maxSize 有無意義, 更應該很直覺地看出, 如果 substring ss 出現 n 次, 那麼s的 substring sss也會出現至少n次
     * 所以對於每個字元, 往後推算他的 minSize 的 substring, 如果符合 maxLetters, 那麼就納入 map 去計數, 不用再多去檢查是否要符合 maxSize
     * 也不用再往後找了
     *
     * 所以用sliding window 就也應該這樣看待就好
     * 只要
     * 1. 超過 maxLetters, i--
     * 2. j-i+1 -> substring長度超過 minSize -> i--
     * 然後檢查 j - i + 1 == minSize -> 是的話就加入map
     */
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        HashMap<String, Integer> subCount = new HashMap<>();
        int i = 0, res = 0, j = 0;
        int[] count = new int[26];
        for (; j < s.length(); j++) {
            if (++count[s.charAt(j) - 'a'] == 1) maxLetters--;
            while (maxLetters < 0 || j - i + 1 > minSize) if (--count[s.charAt(i++) - 'a'] == 0) maxLetters++;
            if (j - i + 1 == minSize) {
                String sub = s.substring(i, j + 1);
                subCount.put(sub, subCount.getOrDefault(sub, 0) + 1);
            }
        }
        for (Integer c : subCount.values()) if (c > res) res = c;
        return res;
    }

    public int maxFreqSlow(String s, int maxLetters, int minSize, int maxSize) {
        Map<String, Integer> subCount = new HashMap<>();
        for (int j = 0; j < s.length(); j++) {
            int[] count = new int[26];
            int letterCount = 0;
            for (int i = j; i <= j + maxSize - 1 && i < s.length(); i++) {
                char ic = s.charAt(i);
                count[ic - 'a']++;
                if (count[ic - 'a'] == 1) letterCount++;
                if (letterCount > maxLetters) break;
                if (i - j + 1 >= minSize) {
                    String substring = s.substring(j, i + 1);
                    subCount.put(substring, subCount.getOrDefault(substring, 0) + 1);
                }
            }
        }
        int res = 0;
        for (Integer c : subCount.values()) if (c > res) res = c;
        return res;
    }
}
