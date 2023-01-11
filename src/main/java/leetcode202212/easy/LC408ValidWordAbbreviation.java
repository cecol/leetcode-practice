package leetcode202212.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC408ValidWordAbbreviation extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC408ValidWordAbbreviation();
    }

    /**
     * https://leetcode.com/problems/valid-word-abbreviation/solutions/89509/simple-java-solution/
     * 這題超多 corner case 要檢查
     * 只有看到解答才發現可以多精簡...
     * - 用 i/j 表達 word/abbr 當前 idx 就好
     * - while (i < w.length() && j < a.length()) 還有得檢查就檢查
     * -   if (Character.isLetter(a.charAt(j)) && w.charAt(i++) != a.charAt(j++)) return false;
     * -     a.charAt(j) 還是字, 但不同於 w.charAt(i) 就 return
     * -   if (j < a.length() && a.charAt(j) == '0') return false;
     * -     a.charAt(j) 是數字 0 開頭 -> return false;
     * -
     * -   StringBuilder sb = new StringBuilder();
     * -   while (j < a.length() && Character.isDigit(a.charAt(j))) sb.append(a.charAt(j++));
     * -   int len = sb.length() > 0 ? Integer.parseInt(sb.toString()) : 0;
     * -   i += len;
     * -     看看當前累計數字, 讓 word idx i 往前跳
     * -     1.  a.charAt(j) 一開始就不是數字, 就 i+0 下一圈繼續找
     * -     2.  a.charAt(j) 一開始就是數字, 累積在 StringBuilder, 最後 算出數字長度, i + len
     * -         如果 i+len 超界 word, 那就出  while, 然後死在
     * -         return [i == w.length()] && j == a.length();
     * -         如果 i+len 沒超界 word, 那就繼續找 while 找
     * -             但可能 j 已經先到盡頭, 那就出  while, 然後死在
     * -             return i == w.length() && [j == a.length();]
     * -
     */
    public boolean validWordAbbreviation(String w, String a) {
        int i = 0, j = 0;
        while (i < w.length() && j < a.length()) {
            if (Character.isLetter(a.charAt(j)) && w.charAt(i++) != a.charAt(j++)) return false;
            if (j < a.length() && a.charAt(j) == '0') return false;
            StringBuilder sb = new StringBuilder();
            while (j < a.length() && Character.isDigit(a.charAt(j))) sb.append(a.charAt(j++));
            int len = sb.length() > 0 ? Integer.parseInt(sb.toString()) : 0;
            i += len;
        }
        return i == w.length() && j == a.length();
    }
}
