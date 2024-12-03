package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.List;
import java.util.PriorityQueue;

public class LC632SmallestRangeCoveringElementsFromKLists extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 沒過
    // 跟 Merge K Sort list 很像
    // pq 放 int[]{list-head-value of listHeadIdx, listIdx(第幾個 list), listHeadIdx(list-head-idx)}
    // pq 比 int[0] 小的先出來
    // 全倒進 PQ 時候找到 當前 curMax
    // while(pq.size == nums.size) 直到有 LIST 走完
    // curMax - min[0] < range -> 當前最小, start = min[0], range 更新
    // min 還有的話要 offer 回 pq
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]);
        int curMax = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            pq.offer(new int[]{nums.get(i).get(0), i, 0});
            curMax = Math.max(curMax, nums.get(i).get(0));
        }

        int range = Integer.MAX_VALUE;
        int start = -1;
        while (pq.size() == nums.size()) {
            int[] min = pq.poll();
            if (curMax - min[0] < range) {
                range = curMax - min[0];
                start = min[0];
            }

            if (min[2] + 1 < nums.get(min[1]).size()) {
                min[2] += 1;
                min[0] = nums.get(min[1]).get(min[2]);
                curMax = Math.max(curMax, min[0]);
                pq.offer(min);
            }
        }
        return new int[]{start, start + range};
    }
}
