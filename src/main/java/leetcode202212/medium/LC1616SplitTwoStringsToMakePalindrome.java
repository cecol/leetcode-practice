package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC1616SplitTwoStringsToMakePalindrome extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1616SplitTwoStringsToMakePalindrome();
    }

    /**
     * https://leetcode.com/problems/split-two-strings-to-make-palindrome/solutions/888885/c-java-greedy-o-n-o-1/
     * 想不到這題可以解這麼乾淨
     * 既然只要 擇一選中就好 - Aprefix + Bsuffix or Bprefix + Asuffix
     *
     * 所以只要 check(a, b) || check(b ,a)
     * check(a, b) - 以 a 為主下去跟 b 找
     * check(b, a) - 以 b 為主下去跟 a 找
     *
     * boolean check(String a, String b)
     * - 以 a 為主下去跟 b 找
     * - 看看 a.prefix 能跟 b.suffix 對到什麼程度
     * -   while (i < j && a.charAt(i) == b.charAt(j)) i++ && j--;
     * 剩下沒對到的 就是要挑出 哪一邊剩下 往中間 靠的區間 的是有 Palindrome
     * -   return isP(a, i, j) || isP(b, i, j);
     *
     * Ex:
     * - A: XYZ | XX | KKK
     * - B: UUU | GK | ZYX
     * APrefix: XXX 對到 Bsuffix: XXX, 後來沒對到
     * 所以取 A or B 剩下的 中心段有 Palindrome 就好
     * */
    public boolean checkPalindromeFormation(String a, String b) {
        return check(a, b) || check(b, a);
    }

    boolean check(String a, String b) {
        int i = 0, j = a.length() - 1;
        while (i < j && a.charAt(i) == b.charAt(j)) {
            i++;
            j--;
        }
        return isP(a, i, j) || isP(b, i, j);
    }

    boolean isP(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
}
