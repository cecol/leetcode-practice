package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ListNode;

import java.util.Arrays;

public class LC242ValidAnagram extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC242ValidAnagram();
    }

    /**
     * https://leetcode.com/problems/valid-anagram/discuss/66484/Accepted-Java-O(n)-solution-in-5-lines
     * 這個答案概念差不多, 但應該比我的快, 只用一個 int[26] 做紀錄, 但實際跑起來比我慢XDD 真不知道為啥
     * */
    public boolean isAnagram(String s, String t) {
        int[] sc = new int[26];
        int[] tc = new int[26];
        for (char c : s.toCharArray()) sc[c - 'a']++;
        for (char c : t.toCharArray()) tc[c - 'a']++;
        return Arrays.toString(sc).equals(Arrays.toString(tc));
    }
}
