package leetcode202103.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeSet;

public class LC739DailyTemperatures extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC739DailyTemperatures();
        LC.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
    }

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        Deque<Integer> tt = new LinkedList<>();
        for (int i = 0; i < T.length; i++) {
            while (tt.size() > 0 && T[tt.getLast()] < T[i]) {
                int j = tt.pollLast();
                res[j] = i-j;
            }
            tt.add(i);
        }
        log.debug("res:{}", res);
        return res;
    }
}
