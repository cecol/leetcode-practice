package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC791CustomSortString extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC791CustomSortString();
        LC.customSortString2("kqep", "pekeq");
    }

    /**
     * 這題真的太經典, 我看到人家的答案才意識到這根本是 counting sort or bucket sort 主戰場
     * 大概知道概念後  自己用counting 解看看
     */
    public String customSortString2(String S, String T) {
        int[] cc = new int[26];
        for (char c : T.toCharArray()) cc[c - 'a']++;
        log.debug("{}",cc);
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            while (cc[c - 'a'] > 0) {
                sb.append(c);
                cc[c - 'a']--;
            }
        }
        log.debug("{}",cc);
        if (sb.length() < T.length()) {
            for (int i = 0; i < 26; i++) {
                while (cc[i] > 0) {
                    sb.append((char) ('a' + i));
                    cc[i]--;
                }
            }
        }
        log.debug("{}", sb);
        return sb.toString();
    }

    /**
     * 這題是有過, 先用個hashmap建立每個 char的 priority
     * 然後用Arrays.sort 來排序, 很直觀做法, 但速度超慢
     */
    public String customSortString(String S, String T) {
        Map<Character, Integer> ms = new HashMap<>();
        int i = 2;
        for (char c : S.toCharArray()) ms.put(c, i++);
        Character[] tc = T.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        Arrays.sort(tc, new Comparator<Character>() {
            @Override
            public int compare(Character c1, Character c2) {
                return ms.getOrDefault(c1, 1) - ms.getOrDefault(c2, 1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Character c : tc) sb.append(c);
        return sb.toString();
    }
}
