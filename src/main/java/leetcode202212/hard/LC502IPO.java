package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class LC502IPO extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC502IPO();
    }

    /**
     * 這題跟 LC871MinimumNumberOfRefuelingStops 邏輯一模一樣
     * 就是看當前的 capital, 然後看看小於自己 capital 的 project 裏面哪個 profit 最大
     * 拿出來加上自己的 capital, -> PriorityQueue 以 project.profit max 為優先
     * 直到沒有 project 可以做 或者做超過上限
     * */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] pc = new int[n][2];
        int res = w;
        for (int i = 0; i < n; i++) pc[i] = new int[]{profits[i], capital[i]};
        Arrays.sort(pc, (x, y) -> (x[1] - y[1]));
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> (y - x));
        int idx = 0;
        while (k > 0) {
            while (idx < n && res >= pc[idx][1]) pq.offer(pc[idx++][0]);
            if(pq.size() == 0) break;
            res+=pq.poll();
            k--;
        }
        return res;
    }
}
