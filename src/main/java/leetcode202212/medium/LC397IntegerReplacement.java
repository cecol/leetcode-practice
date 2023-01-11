package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LC397IntegerReplacement extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC397IntegerReplacement();
    }

    Map<Long, Integer> map = new HashMap<>();

    public int integerReplacement(int n) {
        return rep(n);
    }

    int rep(long n) {
        if (n == 1) return 0;
        if (map.containsKey(n)) return map.get(n);
        int res = 0;
        if (n % 2 == 0) {
            res = rep(n / 2) + 1;
            map.put(n, res);
            return res;
        } else {
            int add = rep(n + 1);
            int sub = rep(n - 1);
            res = Math.min(add, sub) + 1;
            map.put(n, res);
            return res;
        }
    }
}
