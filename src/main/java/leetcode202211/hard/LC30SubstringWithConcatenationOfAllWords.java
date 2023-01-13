package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC30SubstringWithConcatenationOfAllWords extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC30SubstringWithConcatenationOfAllWords();
    }

    /**
     * https://leetcode.com/problems/substring-with-concatenation-of-all-words/discuss/13658/Easy-Two-Map-Solution-(C%2B%2BJava)
     * 2 pointer 解法還是沒有很懂, 反之這個更簡單明瞭
     */
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> wCount = new HashMap<>();
        for (String w : words) wCount.put(w, wCount.getOrDefault(w, 0) + 1);
        List<Integer> res = new ArrayList<>();
        int sLen = s.length();
        int wLen = words.length * words[0].length();

        for (int i = 0; i < sLen - wLen + 1; i++) {
            String sub = s.substring(i, i + wLen);
            if (isConcated(sub, wCount, words[0].length())) {
                res.add(i);
            }
        }
        return res;
    }

    boolean isConcated(String sub, Map<String, Integer> wCount, int wLen) {
        Map<String, Integer> seen = new HashMap<>();
        for (int i = 0; i < sub.length(); i += wLen) {
            String subSub = sub.substring(i, i + wLen);
            seen.put(subSub, seen.getOrDefault(subSub, 0) + 1);
        }
        return seen.equals(wCount);
    }
}
