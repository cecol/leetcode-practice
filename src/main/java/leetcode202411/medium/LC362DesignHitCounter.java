package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.*;

public class LC362DesignHitCounter extends BasicTemplate {
    public static void main(String[] args) {
    }


    public void HitCounter() {

    }

    // 沒過
    // 1. 時間只會往前 monotonic increase - 不只 hits, getHits 也是
    // 2. 所以直接用 queue, 每次 getHits, 把 head < 當前時間-300 都可以丟掉, 剩下就是答案
    // 2-1. 因為只在意 當前時間的-300
    Queue<Integer> dq = new LinkedList<>();
    public void hit(int timestamp) {
        dq.offer(timestamp);
    }

    public int getHits(int timestamp) {
        while (dq.size() > 0 && dq.peek() <= timestamp- 300) dq.poll();
        return dq.size();
    }
}
