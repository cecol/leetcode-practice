package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC468ValidateIPAddress extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC468ValidateIPAddress();
    }

    /**
     * 沒寫過 ip 解析的邏輯, 直接參考最簡單明瞭的案例
     * https://leetcode.com/problems/validate-ip-address/discuss/95484/PythonJava-Easy-Understand-Solution
     * */
    public String validIPAddress(String IP) {
        String[] ip4 = IP.split("\\.", -1);
        String[] ip6 = IP.split(":", -1);
        if (IP.chars().filter(c -> c == '.').count() == 3) {
            for (String s4 : ip4)
                if (isip4(s4)) continue;
                else return "Neither";
            return "IPv4";
        }
        if (IP.chars().filter(c -> c == ':').count() == 7) {
            for (String s6 : ip6)
                if (isip6(s6)) continue;
                else return "Neither";
            return "IPv6";
        }

        return "Neither";
    }

    private boolean isip4(String ip) {
        try {
            Integer ii = Integer.parseInt(ip);
            return String.valueOf(ii).length() == ip.length() && ii <= 255 && ii >= 0;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean isip6(String ip) {
        if (ip.length() > 4) return false;
        try {
            return Integer.parseInt(ip, 16) >= 0 && ip.charAt(0) != '-';
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
