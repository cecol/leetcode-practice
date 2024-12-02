package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import javax.naming.ldap.HasControls;
import java.util.*;

public class LC269AlienDictionary extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // topological sort 有幾個關鍵重點
    // 1. Map<Character, Set<Character>> inEdges & outEdges
    // 2. for i = 1 to n - 2, words[i] & words[i+1] 互相比較
    // 3. 先看 words[i].length > words[i+1].length 且 words[i].startWith(words[i+1]) 一定違法
    // 4. word[i].chatAt(j) vs word[i+1].charAt(j), 找到不一樣就加入。 inEdges & outs
    // 5. outs.put(word[i]), inEdges.put(word[i+1])
    // 6. 傳統的 Queue bfs -> 找到字元放入 StringBuilder
    public String alienOrder(String[] words) {
        StringBuilder sb = new StringBuilder();
        if (words == null || words.length == 0) return "";
        if (words.length == 1) {
            Set<Character> s = new HashSet<>();
            for (char c : words[0].toCharArray()) {
                if(s.add(c)) sb.append(c);
            }
            return sb.toString();
        }
        Map<Character, Set<Character>> inE = new HashMap<>();
        Map<Character, Set<Character>> outs = new HashMap<>();
        for (String s : words) {
            for (char c : s.toCharArray()) {
                if (!inE.containsKey(c)) inE.put(c, new HashSet<>());
                if (!outs.containsKey(c)) outs.put(c, new HashSet<>());
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            if (w1.length() > w2.length() && w1.startsWith(w2)) return "";
            for (int j = 0; j < w1.length(); j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    outs.get(w1.charAt(j)).add(w2.charAt(j));
                    inE.get(w2.charAt(j)).add(w1.charAt(j));
                    break;
                }
            }
        }
        Queue<Character> q = new LinkedList<>();
        for (Character c : inE.keySet()) if (inE.get(c).size() == 0) q.add(c);
        while (!q.isEmpty()) {
            Character c = q.poll();
            sb.append(c);
            for (Character oc : outs.getOrDefault(c, new HashSet<>())) {
                inE.get(oc).remove(c);
                if (inE.get(oc).size() == 0) q.add(oc);
            }
        }
        String res = sb.length() == inE.size() ? sb.toString() : "";
        return res;
    }
}
