package leetcode202103.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LC682BaseballGame extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC682BaseballGame();
        LC.calPoints(new String[]{"5", "2", "C", "D", "+"});
    }

    public int calPoints(String[] ops) {
        Deque<Integer> q = new LinkedList<>();
        int s = 0;
        for (String o : ops) {
            if (o.equals("C")) {
                q.pollLast();
            } else if (o.equals("D")) {
                q.add(q.getLast() * 2);
            } else if (o.equals("+")) {
                Integer l = q.pollLast();
                Integer add = l + q.getLast();
                q.add(l);
                q.add(add);
            } else {
                q.add(Integer.parseInt(o));
            }
        }
        while (q.size() > 0) s += q.pollFirst();
        return s;
    }
}
