package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LC295FindMedianFromDataStream extends BasicTemplate {
    public static void main(String[] args) {
    }

    public void MedianFinder() {
    }

    // 沒過, 有想到 pq min/max
    // 前半段是 max -> min queue
    // 但忘記先塞 max -> min, 在看 MIN > max 就吐回去
    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());

    public void addNum(int num) {
        max.add(num);
        min.add(max.poll());
        while (min.size() > max.size()) max.add(min.poll());
    }

    public double findMedian() {
        if (max.size() > min.size()) return max.peek();
        return ((double)(max.peek() + min.peek())) / 2;
    }
}
