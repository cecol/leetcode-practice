package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class LC680ValidPalindromeII extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC680ValidPalindromeII();
    }

    /**
     * 這題沒有想出比較好的處理 怎決定是刪除哪個字元
     * https://leetcode.com/problems/valid-palindrome-ii/discuss/107716/Java-O(n)-Time-O(1)-Space
     * 還是別人想得比較清晰,
     * 1. 先頭尾開始檢查, 如果有不一樣字元,
     * 2. 開另個 func 去檢查 i,j-1 or i+1,j
     */
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return isP(s, i + 1, j) || isP(s, i, j - 1);
            i++;
            j--;
        }
        return true;
    }

    private boolean isP(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
