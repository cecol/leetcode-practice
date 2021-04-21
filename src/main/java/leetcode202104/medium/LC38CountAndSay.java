package leetcode202104.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC38CountAndSay extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC38CountAndSay();
        LC.countAndSay(4);
    }

    /**
     * 不知道為什麼就直接解掉了, faster than 72.46%
     * 看一下其他答案也沒比較好?
     * */
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String pre = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pre.length(); ) {
            int len = 1;
            while (i + len < pre.length() && pre.charAt(i + len) == pre.charAt(i)) len++;
            String ad = pre.substring(i, i + len);
            if (ad.length() == 1) {
                sb.append(1).append(ad);
            } else sb.append(ad.length()).append(ad.charAt(ad.length() - 1));
            i += len;
        }
        return sb.toString();
    }
}
