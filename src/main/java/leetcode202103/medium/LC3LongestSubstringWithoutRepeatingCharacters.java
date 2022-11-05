package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

public class LC3LongestSubstringWithoutRepeatingCharacters extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC3LongestSubstringWithoutRepeatingCharacters();
        LC.lengthOfLongestSubstring("abcabcbb");
//        LC.lengthOfLongestSubstring("abba");
    }


    /**
     * sliding window, 花了很久時間才解, 雖然是自己解, 一開始也有大致想法
     * 但一開始想錯了, 沒注意到他是包含任意字元
     * 接著又想錯, 要記憶的的 offset而非 count
     * */
    public int lengthOfLongestSubstring(String s) {
        int i = 0;
        Map<Character, Integer> cc = new HashMap<>();
        int mx = 0;
        for (int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (cc.containsKey(c)) {
                int k = i;
                i = cc.get(c) + 1;
                for (; k < i; k++) cc.remove(s.charAt(k));
            }
            log.debug("i:{}, j:{}, c:{}, cc:{}", i, j, c, cc);
            mx = Math.max(mx, j - i + 1);
            cc.put(c, j);
        }
        return mx;
    }
}
