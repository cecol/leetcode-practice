package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC1087BraceExpansion extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1087BraceExpansion();
    }

    /**
     * 蠻直觀解法, 就是先把所有 group都找出來, 然後DFS下去建立string
     * Runtime: 2 ms, faster than 96.03%
     */
    public String[] expand(String s) {
        int n = s.length();
        List<String> res = new ArrayList<>();
        List<List<Character>> groups = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (Character.isLowerCase(s.charAt(i))) groups.add(List.of(s.charAt(i)));
            else if (s.charAt(i) == '{') {
                List<Character> g = new ArrayList<>();
                int j = i + 1;
                for (; j < n && s.charAt(j) != '}'; j++) if (Character.isLowerCase(s.charAt(j))) g.add(s.charAt(j));
                Collections.sort(g);
                groups.add(g);
                i = j - 1;
            }
        }
        dfs(res, groups, "", 0);
        return res.toArray(new String[res.size()]);
    }

    private void dfs(List<String> res, List<List<Character>> groups, String cur, int i) {
        if (i == groups.size()) res.add(cur);
        else for (Character c : groups.get(i)) dfs(res, groups, cur + c, i + 1);
    }
}
