package leetcode202105.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC824GoatLatin extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC824GoatLatin();
    }

    /**
     * 蠻直觀的直接string.split 然後每個下去檢查 Runtime: 1 ms, faster than 99.78%
     * 只是code 有點長, 其他解答邏輯依樣, 但更簡短
     * */
    public String toGoatLatin(String sentence) {
        String[] s = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        String a = "a";
        for (String ss : s) {
            char fc = ss.charAt(0);
            char fcl = Character.toLowerCase(fc);
            if (fcl == 'a' || fcl == 'e' || fcl == 'i' || fcl == 'o' || fcl == 'u') {
                sb.append(ss).append("ma");
            } else {
                sb.append(ss.substring(1, ss.length())).append(fc).append("ma");
            }
            sb.append(a).append(' ');
            a = a + "a";
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
