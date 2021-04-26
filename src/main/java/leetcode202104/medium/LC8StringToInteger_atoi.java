package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC8StringToInteger_atoi extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC8StringToInteger_atoi();
    }

    /**
     * 被規則搞得好煩 "+-12" 要回傳 0, "-+12" 要回傳 12, 馬的 還是看人家怎麼寫
     */
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;
        s = s.trim();
        if (s.length() == 0) return 0;
        long sum = 0L;
        char firstChar = s.charAt(0);
        int sign = 1, start = 0, len = s.length();
        if (firstChar == '+') {
            sign = 1;
            start++;
        }
        if (firstChar == '-') {
            sign = -1;
            start++;
        }

        for (int i = start; i < len; i++) {
            if (!Character.isDigit(s.charAt(i))) return (int) sum * sign;
            sum = sum * 10 + s.charAt(i) - '0';
            if (sign == 1 && sum > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && sign * sum < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        }
        return (int) sum * sign;
    }
}
