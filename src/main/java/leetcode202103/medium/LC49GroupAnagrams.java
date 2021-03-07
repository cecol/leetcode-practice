package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC49GroupAnagrams extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC49GroupAnagrams();
    }

    /**
     * 我的解法有過, 但速度只有1/4快, 看別人也是差不多解法
     * 比較特別的是
     * https://leetcode.com/problems/group-anagrams/discuss/19233/O(M-*-N)-algorithm-using-hash-without-sort()
     * 有人把每個字元轉換成質數相乘, 也確保unique
     * 但不知道速度就是
     * */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();
        for (String s : strs) {
            int[] cs = new int[26];
            for (char c : s.toCharArray()) {
                cs[c - 'a']++;
            }
            String k = Arrays.toString(cs);
            List<String> ll = m.getOrDefault(k, new ArrayList<>());
            ll.add(s);
            m.put(k, ll);
        }
        return new ArrayList<>(m.values());
    }
}
