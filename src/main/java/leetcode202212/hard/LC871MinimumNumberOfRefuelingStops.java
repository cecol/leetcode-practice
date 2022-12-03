package leetcode202212.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class LC871MinimumNumberOfRefuelingStops extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC871MinimumNumberOfRefuelingStops();
    }

    /**
     * https://leetcode.com/problems/minimum-number-of-refueling-stops/discuss/294025/Java-Simple-Code-Greedy
     * 這題真的很不直觀 不會想到用 PriorityQueue, 直到看到上面的圖解
     * 基本概念是, 如果目前 Fuel 最遠還不能走到 target, 我要先挑哪個 gas tation?
     * 這其實是Greedy -> 就是說目前 Fuel 能走到 x offset, 在 x offset, 之前經過的 gas station 哪個最多油可以讓走更遠更接近 target?
     * 所以就是把目前  x offset, 之前經過的 gas station 都加入 PriorityQueue
     * 從裡面取最大出來, 這樣就是變成 x + max_new_fuel 這麼遠
     * 1. x + max_new_fuel 有達成 target -> return
     * 2. x + max_new_fuel 還沒到 target -> 繼續上面步驟
     * - 如果沒有 gas station 在當前 x + max_new_fuel 區間(PriorityQueue is empty) -> 根本到不了, return -1
     * */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (startFuel >= target) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> (y - x));
        int i = 0, n = stations.length, stops = 0, maxDistance = startFuel;
        while(maxDistance < target) {
            while (i < n && stations[i][0] <= maxDistance) {
                pq.offer(stations[i++][1]);
            }
            if(pq.isEmpty()) return -1;
            maxDistance += pq.poll();
            stops++;
        }
        return stops;
    }
}
