package leetcode202210.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC409LongestPalindrome extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC409LongestPalindrome();
    }

    /**
     * 這題想不到卡住我了, odd count 也是可以計入, 只要少拿1就好
     * 原本想錯以為 odd count 字元只取1
     * */
    public int longestPalindrome(String s) {
        Map<Character, Integer> m = new HashMap<>();
        boolean hasOdd = false;
        int res = 0;
        for (char c : s.toCharArray()) {
            m.put(c, m.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> e : m.entrySet()) {
            if (e.getValue() % 2 == 0) res += e.getValue();
            else {
                res += e.getValue() - 1;
                hasOdd = true;
            }
        }

        return hasOdd ? res + 1 : res;
    }
}
