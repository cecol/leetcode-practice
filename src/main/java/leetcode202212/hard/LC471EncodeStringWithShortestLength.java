package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC471EncodeStringWithShortestLength extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC471EncodeStringWithShortestLength();
    }

    /**
     * https://leetcode.com/problems/encode-string-with-shortest-length/solutions/95613/java-dfs-memorization/
     * 原本以為是用 String[][] dp = new String[n][n] 下去找
     * 每一段 dfs 都是 前半段找一段長度, 配上後半段 dfs 下去
     * 但完全忽略找到的 pattern 還可以再sub pattern 縮減 類似 2[3[ab]] 這樣
     *
     * 而且用 String[][] dp = new String[n][n] 切字串不好切
     *
     * 直到看到答案用 Map<String, String> 配上直接遞迴 題目 encode 真的很簡潔
     * 基本要件要認清
     * 1. Map<String, String> map, key 是縮減前, value 是縮減後, 也如同 String[][] dp, 但不用算 i/j
     * 2. if (s.length() <= 4) return s; 字串太短沒有縮減必要
     * 3. 縮減有兩種
     * - 1. 整個 String s 找重複 pattern,
     * -    找 pattern 這個也可以優化, 我們只要看 s.length()/2 to s.length(), 因為超過 s.length()/2 也無法成為 pattern
     * -    然後 下去count pattern 出現次數, 回傳要驗證 pattern 出現次數是否真的可以組回 String s
     * -    有 pattern 就是是 times + "[" + encode(pattern) + "]"; -> pattern 可以再下去 encode !!
     * - 2. 不找 pattern, 直接切左右下去 encode(s.substring(0, i)) + encode(s.substring(i)) 找看看
     * 然後看看上述誰最短 放進 map 就好
     *
     * */
    Map<String, String> map = new HashMap<>();

    public String encode(String s) {
        if (s == null || s.length() == 0) return "";
        if (s.length() <= 4) return s;
        if (map.containsKey(s)) return map.get(s);
        String res = s;
        for (int k = s.length() / 2; k < s.length(); k++) {
            String pattern = s.substring(k);
            int times = countRepeat(s, pattern);
            if (times * pattern.length() != s.length()) continue;
            String candidate = times + "[" + encode(pattern) + "]";
            if (candidate.length() < res.length()) res = candidate;
        }
        for (int i = 1; i < s.length(); i++) {
            String left = encode(s.substring(0, i));
            String right = encode(s.substring(i));
            String candidate = left + right;
            if (candidate.length() < res.length()) res = candidate;
        }
        map.put(s, res);
        return res;
    }

    int countRepeat(String s, String pattern) {
        int times = 0;
        while (s.length() >= pattern.length()) {
            String sub = s.substring(s.length() - pattern.length());
            if (sub.equals(pattern)) {
                times++;
                s = s.substring(0, s.length() - pattern.length());
            } else return times;
        }
        return times;
    }
}
