package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC202HappyNumber extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC202HappyNumber();
        LC.isHappy(19);
    }

    /**
     * 很直觀的解出來 faster than 81.01%
     * */
    public boolean isHappy(int n) {
        Map<Integer, Integer> m = new HashMap<>();
        while (true) {
            int nn = 0;
            int nnn = n;
            while (nnn != 0) {
                nn += Math.pow(nnn % 10, 2);
                nnn /= 10;
            }
            if (nn == 1) return true;
            if (m.containsKey(n)) return false;
            m.put(n, nn);
            n = nn;
        }
    }
}
