package leetcode202411.hard;


import leetcode20200921to20201031.BasicTemplate;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class LC84LargestRectangleInHistogram extends BasicTemplate {
    public static void main(String[] args) {
    }

    // 忘記細節了, 只記得 double ended queue + mono-tonic increase
    // 1. dq 放 offset, 且是單調遞增
    // 2. 當前 heights[i] 低於 dq 尾巴, 就代表 得先 pollLast 兌現
    // 3. 兌現的 h = dq.pollLast, 兌現的 w = i - dp.getLast + 1 -> 持續兌現到 heights[i] 可以 mono tonic increase 塞入
    // 4. 當 i = n, 要打一發 h = 0 進去掃頭尾最低案例, 看看 dq 有無沒清理完的 最低 height
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, res = 0;
        LinkedList<Integer> dq = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            int v = i == n ? 0 : heights[i];
            while (!dq.isEmpty() && v < heights[dq.getLast()]) {
                int realizeH = heights[dq.pollLast()];
                int realizeHW = i - (dq.isEmpty() ? 0 : dq.getLast() + 1);
                res = Math.max(res, realizeH * realizeHW);
            }
            dq.add(i);
        }
        return res;
    }
}
