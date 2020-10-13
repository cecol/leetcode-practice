package leetcode202009.medium;

import leetcode202009.BasicTemplate;

public class LC443StringCompression extends BasicTemplate {

    public static void main(String[] args) {
        var LC = new LC443StringCompression();
        var r = LC.compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'});
    }

    public int compress(char[] chars) {
        if (chars == null) return 0;
        if (chars.length == 0) return 0;
        if (chars.length == 1) return 1;
        StringBuilder s = new StringBuilder("");
        s.append(chars[0]);
        int c = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                c++;
            } else {
                if (c > 1) s.append(Integer.valueOf(c).toString());
                s.append(chars[i]);
                c = 1;
            }
        }
        if (c > 1) s.append(Integer.valueOf(c).toString());
        var rs = s.toString();
        for (int i = 0; i < rs.length(); i++) chars[i] = rs.charAt(i);
        log.debug("{}", chars);
        return rs.length();
    }
}
