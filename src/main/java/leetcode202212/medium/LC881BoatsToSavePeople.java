package leetcode202212.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class LC881BoatsToSavePeople extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC881BoatsToSavePeople();
        LC.numRescueBoats(new int[]{3, 2, 3, 2, 2}, 6);
    }

    /**
     * 很直觀的用貪心找成對 最少用到 船 的情境
     * 就是說對於 最重 Xi, 就配 另一個 Yi 使得 Xi+Yi <= 接近 limit
     * * */
    public int numRescueBoats(int[] people, int limit) {
        TreeMap<Integer, Integer> ts = new TreeMap<>();
        for (int p : people) ts.put(p, ts.getOrDefault(p, 0) + 1);
        Arrays.sort(people);
        int res = 0;
        for (int i = people.length - 1; i >= 0; i--) {
            int wi = people[i];
            if (ts.containsKey(wi)) {
                res++;
                ts.put(wi, ts.get(wi) - 1);
                if (ts.get(wi) == 0) ts.remove(wi);
                int rest = limit - wi;
                if (rest > 0 && ts.floorKey(rest) != null) {
                    int riKey = ts.floorKey(rest);
                    ts.put(riKey, ts.get(riKey) - 1);
                    if (ts.get(riKey) == 0) ts.remove(riKey);
                }
            }
        }
        return res;
    }
}
