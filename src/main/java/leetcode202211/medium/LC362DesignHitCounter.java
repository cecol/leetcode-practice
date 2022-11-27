package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class LC362DesignHitCounter extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    public void HitCounter() {

    }

    Queue<Integer> dq = new LinkedList<>();
    public void hit(int timestamp) {
        dq.offer(timestamp);
    }

    public int getHits(int timestamp) {
        while (dq.size() > 0 && dq.peek() <= timestamp - 300) dq.poll();
        return dq.size();
    }
}
