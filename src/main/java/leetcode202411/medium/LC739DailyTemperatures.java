package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC739DailyTemperatures extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 秒解 dq + monotonic decrease 就可以做到
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> dq = new LinkedList<>();
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while(!dq.isEmpty() && temperatures[dq.peekLast()] < temperatures[i]) {
                int v = dq.pollLast();
                res[v] = i-v;
            }
            dq.add(i);
        }
        return res;
    }
}
