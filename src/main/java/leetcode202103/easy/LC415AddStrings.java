package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class LC415AddStrings extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC415AddStrings();
    }

    public String addStrings(String num1, String num2) {
        int n1 = num1.length() - 1;
        int n2 = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (n1 >= 0 || n2 >= 0) {
            int d = carry;
            if (n1 >= 0) d += (num1.charAt(n1--) - '0');
            if (n2 >= 0) d += (num2.charAt(n2--) - '0');
            carry = d / 10;
            sb.insert(0, d % 10);
        }
        if (carry > 0) sb.insert(0, carry);
        return sb.toString();
    }
}
