package leetcode202211.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC9PalindromeNumber extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC9PalindromeNumber();
    }

    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        String n = String.valueOf(x);
        int i = 0, j = n.length() - 1;
        while (i <= j) {
            if (n.charAt(i++) != n.charAt(j--)) return false;
        }
        return true;
    }
}
