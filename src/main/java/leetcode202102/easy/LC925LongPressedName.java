package leetcode202102.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC925LongPressedName extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC925LongPressedName();
        LC.isLongPressedName("alex", "aaleexa");
    }

    public boolean isLongPressedName(String name, String typed) {
        int n1 = 0, t1 = 0;
        while (n1 < name.length()) {
            int n2 = n1;
            while (n2 < name.length() && name.charAt(n2) == name.charAt(n1)) n2++;
            int t2 = t1;
            while (t2 < typed.length() && name.charAt(n1) == typed.charAt(t2)) t2++;
            if (t2 - t1 < n2 - n1) return false;
            n1 = n2;
            t1 = t2;
        }
        log.debug("n1:{}, t1:{}", n1, t1);
        return t1 == typed.length();
    }
}
