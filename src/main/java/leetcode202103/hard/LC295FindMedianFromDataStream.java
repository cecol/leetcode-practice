package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class LC295FindMedianFromDataStream extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC295FindMedianFromDataStream();
    }

    /**
     * 我其實一直沒有想到這題到底要考什麼, 直到看到了答案才意會到
     * 他其實要的是算Median時候是 O(1) 時間
     * 所以要 maintain 2 heap
     * 1. min heap
     * 2. max heap
     * 各存一半的資料, 應該就是 如果給的數字都 排列好了
     * 1,2,3,4,5,6,7,8
     * max = 1,2,3,4
     * min = 5,6,7,8
     * 所以 median = (max.peek + min.peek) / 2.0
     * 所以說每當有新的 num進來, 要先進 max, 然後再進 min -> 這樣就確保 max 拿到的是前半, 後半的給 min
     * 如果 max.size < min.size -> min 要吐出來還給 max
     */
    public void MedianFinder() {

    }

    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(1000, Comparator.reverseOrder());

    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if (max.size() < min.size()) max.offer(min.poll());
    }

    public double findMedian() {
        if (min.size() == max.size()) return (max.peek() + min.peek()) / 2.0;
        else return (double) max.peek();
    }
}
