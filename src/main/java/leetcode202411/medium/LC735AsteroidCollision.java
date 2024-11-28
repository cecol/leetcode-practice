package leetcode202411.medium;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Deque;
import java.util.LinkedList;

public class LC735AsteroidCollision extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒想到, dq 變化題
    // 1. asteroids 從 0 開始掃
    // 2. dp 空 or dq內都是不會碰撞到的 (統一向左飛), 加入 DQ
    // 3. dq last 跟現在 asteroid 方向相撞, 持續撞到 CASE 2
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < asteroids.length; i++) {
            int as = asteroids[i];
            while (dq.size() > 0 && dq.peekLast() > 0 && as < 0) {
                int meet = dq.pollLast();
                if (-as < meet) {
                    dq.offerLast(meet);
                    as = 0;
                } else if (-as == meet) as = 0;
            }
            if (as != 0) dq.offer(as);
        }
        int[] res = new int[dq.size()];
        for (int i = 0; i < res.length; i++) res[i] = dq.pollFirst();
        return res;
    }
}
