package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class LC269AlienDictionary extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC269AlienDictionary();
//        LC.alienOrder(new String[]{"ac", "ab", "zc", "zb"});
        LC.alienOrder(new String[]{"ozvcdpgfq", "mridvkklqj", "dpwecbwor", "xxtistijm", "xxuon", "tudbazpggu", "hnuumktbjy", "bogbcoi"});
    }

    /**
     * 我是知道 topological sort, 邏輯是對的, 但花超多時間處理各種奇怪的 test case, 還有因為第一次寫到這種比較複雜的 in out dependency
     * 建立 graphs 建立超久
     * 事實上要建立
     *  1. Map<Character, Set<Character>> inEdges = new HashMap<>();
     *  2. Map<Character, Set<Character>> outs = new HashMap<>();
     *  而且還要處理 if (w1.length() > w2.length() && w1.startsWith(w2)) return ""; 如果w1 在w2前面， 代表這排序是錯的
     *  以前處理 inDegree 只要用 count, 這次得記得in有誰, 單純用count會記載到重複edge, 而且得把所有 cases 的 word都加入這兩個map
     *  以前都可以 int[26] 處理掉, 但這次 alien dict 中有的字元可能只有部分, 缺失的不需要在答案之中
     *  這應該是足夠複雜的 topological 的題目了
     */
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        if (words.length == 1) return words[0];
        Map<Character, Set<Character>> inEdges = new HashMap<>();
        Map<Character, Set<Character>> outs = new HashMap<>();
        for (String s : words)
            for (char c : s.toCharArray()) {
                if (!inEdges.containsKey(c)) inEdges.put(c, new HashSet<>());
                if (!outs.containsKey(c)) outs.put(c, new HashSet<>());
            }
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            if (w1.length() > w2.length() && w1.startsWith(w2)) return "";
            for (int j = 0; j < w1.length(); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    outs.get(w1.charAt(j)).add(w2.charAt(j));
                    inEdges.get(w2.charAt(j)).add(w1.charAt(j));
                    break;
                }
            }
        }
        Queue<Character> q = new LinkedList<>();
        for (Character c : inEdges.keySet()) if (inEdges.get(c).size() == 0) q.add(c);
        StringBuilder sb = new StringBuilder();
        while (q.size() > 0) {
            Character c = q.poll();
            sb.append(c);
            for (Character oc : outs.getOrDefault(c, new HashSet<>())) {
                inEdges.get(oc).remove(c);
                if (inEdges.get(oc).size() == 0) q.add(oc);
            }
        }
        String res = sb.length() == inEdges.size() ? sb.toString() : "";
        return res;
    }
}
