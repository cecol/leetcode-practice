package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC125ValidPalindrome extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC125ValidPalindrome();
        LC.isPalindrome("A man, a plan, a canal: Panama");
    }

    public boolean isPalindrome(String s) {
        String als = s.replaceAll("[^0-9a-zA-Z]", "").toLowerCase();
        int i = 0, j = als.length() - 1;
        while (i < j) if (als.charAt(i++) != als.charAt(j--)) return false;
        return true;
    }
}
