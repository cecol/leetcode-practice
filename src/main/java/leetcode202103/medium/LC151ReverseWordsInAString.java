package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LC151ReverseWordsInAString extends BasicTemplate {


    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC151ReverseWordsInAString();
//        LC.reverseWords("the sky is blue");
        LC.reverseWords("  hello world  ");
        LC.reverseWords("a good   example");
        LC.reverseWords("  Bob    Loves  Alice   ");
    }

    /**
     * 有解出來: faster than 84.66% of Java, less than 46.98% of Java
     * 花的有點多時間熟悉String functions: substring, indexOf
     * */
    public String reverseWords(String s) {
        int i = 0;
        StringBuilder r = new StringBuilder();
        while (i < s.length()) {
            int idx = s.indexOf(' ', i);
            if (idx < 0) {
                r.insert(0, s.substring(i) + " ");
                break;
            }
            String sub = s.substring(i, idx).trim();
            if (!sub.isEmpty()) r.insert(0, sub + " ");
            i = idx + 1;
        }
        return r.deleteCharAt(r.length() - 1).toString();
    }
}
