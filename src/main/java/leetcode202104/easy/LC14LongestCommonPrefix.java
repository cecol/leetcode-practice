package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC14LongestCommonPrefix extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC14LongestCommonPrefix();
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        int l = 0;
        String ss = strs[0];

        while (true) {
            for (int i = 0; i < strs.length; i++) {
                if (l >= strs[i].length() || ss.charAt(l) != strs[i].charAt(l)) return ss.substring(0, l);
            }
            l++;
        }
    }
}
