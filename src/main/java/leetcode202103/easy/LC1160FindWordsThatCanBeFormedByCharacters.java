package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LC1160FindWordsThatCanBeFormedByCharacters extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1160FindWordsThatCanBeFormedByCharacters();
        LC.countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach");
    }

    Map<Character, Integer> cs = new HashMap<>();
    /**
     * 我是用 HashMap解, 有過, 但是慢
     * https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/discuss/361004/Easy-Explained-Simple-Java-Check-all-char-count
     * 後來參考解答有用 char[26] 來當map, 每次檢查直接Arrays.copy
     * 其實概念依樣, 只是快更多, 看來同樣做法的, 但因為用 primitive 就會快更多
     * */
    public int countCharacters(String[] words, String chars) {
        for (char c : chars.toCharArray()) cs.put(c, cs.getOrDefault(c, 0) + 1);
        int res = 0;
        for (String w : words) {
            if (isGood(w)) res += w.length();
        }
        return res;

    }

    private boolean isGood(String w) {
        Map<Character, Integer> cw = new HashMap<>();
        for (char c : w.toCharArray()) cw.put(c, cw.getOrDefault(c, 0) + 1);
        for (Character k : cw.keySet()) {
            if (cs.getOrDefault(k, 0) < cw.get(k)) return false;
        }
        return true;
    }
}
