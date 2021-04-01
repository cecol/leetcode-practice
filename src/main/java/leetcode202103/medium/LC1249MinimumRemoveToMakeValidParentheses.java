package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TreeSet;

public class LC1249MinimumRemoveToMakeValidParentheses extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1249MinimumRemoveToMakeValidParentheses();
    }

    /**
     * 蠻直觀的自己解掉了
     * 9 ms, faster than 94.11%
     * */
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(s);
        int open = 0;
        int close = 0;
        for(int i = 0;i<sb.length();i++) {
            if(sb.charAt(i) == '(') {
                open++;
            } else if(sb.charAt(i) == ')') {
                close++;
                if (close > open) {
                    sb.deleteCharAt(i);
                    i--;
                    close--;
                }
            }
        }
        while (open > close) {
            sb.deleteCharAt(sb.lastIndexOf("("));
            open--;
        }
        return sb.toString();
    }
}
