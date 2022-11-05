package leetcode202211.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC844BackspaceStringCompare extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC844BackspaceStringCompare();
    }

    public boolean backspaceCompare(String s, String t) {
        return backspace(s).equals(backspace(t));
    }


    String backspace(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); ) {
            if (s.charAt(i) != '#') {
                sb.append(s.charAt(i));
                i++;
            } else {
                while (i < s.length() && s.charAt(i) == '#') {
                    if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
                    i++;
                }
            }
        }
        return sb.toString();
    }
}
