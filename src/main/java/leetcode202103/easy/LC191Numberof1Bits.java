package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC191Numberof1Bits extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC191Numberof1Bits();
    }

    public int hammingWeight(int n) {
        int c = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) c++;
            n >>= 1;
        }
        return c;
    }
}
