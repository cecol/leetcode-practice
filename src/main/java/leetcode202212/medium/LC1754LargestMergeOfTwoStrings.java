package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1754LargestMergeOfTwoStrings extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1754LargestMergeOfTwoStrings();
    }

    /**
     * https://leetcode.com/problems/largest-merge-of-two-strings/solutions/1053496/java-merge/
     * 其實蠻直觀的
     * 1. w1/w2 前選字首大的
     * 2. w1/w2 如果字首一樣, 直接捨棄字首 看看後面哪個會比較大,
     * - 所以直接 if (w1.substring(i).compareTo(w2.substring(j)) > 0) 來判定, 整個剩下來字串判定就好
     * */
    public String largestMerge(String w1, String w2) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (i < w1.length() && j < w2.length()) {
            if (w1.substring(i).compareTo(w2.substring(j)) > 0) sb.append(w1.charAt(i++));
            else sb.append(w2.charAt(j++));
        }
        while (i < w1.length()) sb.append(w1.charAt(i++));
        while (j < w2.length()) sb.append(w2.charAt(j++));
        return sb.toString();
    }
}
