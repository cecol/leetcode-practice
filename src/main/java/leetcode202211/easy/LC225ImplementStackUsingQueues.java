package leetcode202211.easy;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;

public class LC225ImplementStackUsingQueues extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC225ImplementStackUsingQueues();
    }

    public void MyStack() {

    }

    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();

    public void push(int x) {
        q1.offer(x);
    }

    public int pop() {
        while(q1.size() > 1) q2.offer(q1.poll());
        Integer v = q1.poll();
        while(q2.size() > 0) q1.offer(q2.poll());
        return v;
    }

    public int top() {
        while(q1.size() > 1) q2.offer(q1.poll());
        Integer v = q1.poll();
        q2.offer(v);
        while(q2.size() > 0) q1.offer(q2.poll());
        return v;
    }

    public boolean empty() {
        return q1.size() == 0 && q2.size() == 0;
    }
}
