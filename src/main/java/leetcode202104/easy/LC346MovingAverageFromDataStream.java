package leetcode202104.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class LC346MovingAverageFromDataStream extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC346MovingAverageFromDataStream();
    }

    /** Initialize your data structure here. */
    int cap = 0;
    int sum = 0;
    Queue<Integer> w = new LinkedList<>();
    public void MovingAverage(int size) {
        cap = size;
    }

    public double next(int val) {
        sum+=val;
        w.add(val);
        if(w.size()>cap) sum-=w.poll();
        return sum/(double)(Math.min(w.size(), cap));
    }
}
