package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC291WordPatternII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC291WordPatternII();
        LC.wordPatternMatch("abba", "dogcatcatdog");
    }

    /**
     * 蠻直觀的解法, 就是建立一個  Map<Character, String> map = new HashMap<>(); 記載當前累計的 pattern
     * Set<String> visited = new HashSet<>(); 已看過 pattern -> 因為不可以兩個 Character map 同一個 pattern
     *
     * 然後開始遞迴 back tracking,
     * 1. pattern 每次都拿出第一個 char 來下去檢查
     * 2. s 就是從  for (int i = 1; i <= s.length(); i++) {
     * -           String ps = s.substring(0, i);
     * 來看看當前 pettern 下去是否可以找到 -> dfs 遞迴下去找
     * */
    Map<Character, String> map = new HashMap<>();
    Set<String> visited = new HashSet<>();
    public boolean wordPatternMatch(String pattern, String s) {
        return dfs(pattern, s);
    }

    boolean dfs(String p, String s) {
        if (p.length() == 0 && s.length() == 0) return true;
        if (p.length() != 0 && s.length() == 0) return false;
        if (p.length() == 0) return false;
        char pc = p.charAt(0);
        if (map.containsKey(pc) && !s.startsWith(map.get(pc))) return false;
        if (map.containsKey(pc) && s.startsWith(map.get(pc))) {
            return dfs(p.substring(1), s.replaceFirst(map.get(pc), ""));
        }
        for (int i = 1; i <= s.length(); i++) {
            String ps = s.substring(0, i);
            if(!visited.add(ps)) continue;
            map.put(pc, ps);
            if(dfs(p.substring(1), s.replaceFirst(map.get(pc), ""))) return true;
            visited.remove(ps);
            map.remove(pc);
        }
        return false;
    }
}
