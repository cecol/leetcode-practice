package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.list.ListNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class LC735AsteroidCollision extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC735AsteroidCollision();
    }

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < asteroids.length; i++) {
            int as = asteroids[i];
            while (dq.size() > 0 && dq.peekLast() > 0 && as < 0) {
                int meet = dq.pollLast();
                if (-as < meet) {
                    dq.offerLast(meet);
                    as = 0;
                } else if(-as == meet) as = 0;
            }
            if (as != 0) dq.offer(as);
        }
        int[] res = new int[dq.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = dq.pollFirst();
        }
        return res;
    }
}
