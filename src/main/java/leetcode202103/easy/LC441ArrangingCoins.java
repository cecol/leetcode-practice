package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LC441ArrangingCoins extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC441ArrangingCoins();
        LC.arrangeCoins(1804289383);
    }

    /**
     * 是自己解開了, 但沒想到是死在test case會導致int overflow
     * */
    public int arrangeCoins(int n) {
        if (n == 0) return 0;
        long l = 1, h = n;
        while (l < h) {
            long m = (l + h) / 2;
            if (st(m) <= n && st(m + 1) > n) return (int) m;
            else if (st(m) > n) h = m;
            else l = m + 1;
        }
        return (int) l;
    }

    private long st(long k) {
        return (1 + k) * k / 2;
    }
}
