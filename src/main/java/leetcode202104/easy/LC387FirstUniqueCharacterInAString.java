package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC387FirstUniqueCharacterInAString extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC387FirstUniqueCharacterInAString();
    }

    /**
     * https://leetcode.com/problems/first-unique-character-in-a-string/discuss/86348/Java-7-lines-solution-29ms
     * 原本有解開但沒想到用 char count 就好了
     */
    public int firstUniqChar(String s) {
        int[] c = new int[26];
        for (char cc : s.toCharArray()) c[cc - 'a']++;
        for (int i = 0; i < s.length(); i++) if (c[s.charAt(i) - 'a'] == 0) return i;
        return -1;
    }

    public int firstUniqCharSlow(String s) {
        Map<Character, int[]> m = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            m.computeIfAbsent(s.charAt(i), c -> new int[]{0, -1});
            int[] ci = m.get(s.charAt(i));
            ci[0]++;
            if (ci[1] == -1) ci[1] = i;
        }
        int min = -1;
        for (int[] e : m.values())
            if (e[0] == 1) if (min == -1) min = e[1];
            else min = Math.min(min, e[1]);
        return min;
    }
}
