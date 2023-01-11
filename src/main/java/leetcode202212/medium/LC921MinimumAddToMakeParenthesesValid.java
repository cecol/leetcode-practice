package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC921MinimumAddToMakeParenthesesValid extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC921MinimumAddToMakeParenthesesValid();
    }

    public int minAddToMakeValid(String s) {
        int l = 0;
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') l++;
            else {
                if (l > 0) l--;
                else res++;
            }
        }
        return res + l;
    }
}
