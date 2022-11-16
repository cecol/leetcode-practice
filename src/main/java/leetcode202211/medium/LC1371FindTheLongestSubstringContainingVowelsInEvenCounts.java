package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;

public class LC1371FindTheLongestSubstringContainingVowelsInEvenCounts extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    HashMap<Character, Integer> vowelToBitIndex = new HashMap<Character, Integer>() {
        {
            put('a', 1);
            put('e', 2);
            put('i', 4);
            put('o', 8);
            put('u', 16);
        }
    };

    /**
     * https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/discuss/531840/JavaC%2B%2BPython-One-Pass
     * https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/discuss/532101/Java-o(n)-one-pass-solution.-Easy-to-understand.
     * 這題是說要找出 vowel 偶數次, 其實要找的是前面出現過奇數偶數次的位置
     * 因為前面出現奇數次, 現在出現奇數次, 現在減前面就是偶數
     * 因為前面出現偶數次, 現在出現偶數次, 現在減前面就是偶數
     *
     * vowel 只有5個  用 5 bits 來紀錄 當前 preCount(preSum) 是奇數還是偶數, 就是 vowel count %2 結果
     * cur records the count of "aeiou"
     * cur & 1 = the records of a % 2
     * cur & 2 = the records of e % 2
     * cur & 4 = the records of i % 2
     * cur & 8 = the records of o % 2
     * cur & 16 = the records of u % 2
     *
     * 當前 preSum 如果在 hashmap 出現過 就代表這區間 vowel 可以是偶數
     * 因為當前的奇or偶剛好是之前的奇or偶, 當前減掉之前之後 就會是保證偶數
     * */
    public int findTheLongestSubstring(String s) {
        HashMap<Integer, Integer> stateToIndex = new HashMap<>();
        stateToIndex.put(0, -1);
        int state = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (vowelToBitIndex.containsKey(cur)) {
                int bitToFlip = vowelToBitIndex.get(cur);
                state ^= bitToFlip;
            }

            stateToIndex.putIfAbsent(state, i);
            maxLen = Math.max(maxLen, i - stateToIndex.get(state));
        }
        return maxLen;
    }
}
