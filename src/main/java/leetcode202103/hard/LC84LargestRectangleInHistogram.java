package leetcode202103.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;

public class LC84LargestRectangleInHistogram extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC84LargestRectangleInHistogram();
    }

    /**
     * https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/29018/AC-clean-Java-solution-using-stack
     * 有想到要用 stack, 但還是沒好好地想出細節
     * 1. stack 儲存單調遞增(儲存的是 height的 index, 因為要用來算width), 所以不能直接存 height[i], 是要存 i
     * 2. 所以遇到當前的 height 小於 stack top -> 該兌現前面的 max area(因為當前stack 存的比較高的也在後面無望了, 後面矮的會去兌現)
     * -> 所以先q.pollLast() -> heights[q.pollLast()]
     * -> 而pop出來的這個要兌現的
     * -> 1. 如果 stack 還有(!q.isEmpty()), 也一定比當前要兌現的小
     * ->   因此能兌現的寬度就是  i - q.getLast() + 1
     * -> 2. 如果 stack 沒有等於他是最矮的 -> 因此寬度是目前走到的 i
     * 3. 最後一點  for (int i = 0; i <= n; i++) 其中讓 i 走到 n 是因為要配合 int v = i == n ? 0 : heights[i];
     * 等於說若一直遞增, 就會沒有發生兌現, 所以, 得帶入一個最小高度0 -> 算是最後收尾把還沒兌現的高度都兌現了
     */
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, res = 0;
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i <= n; i++) {
            int v = i == n ? 0 : heights[i];
            while (!q.isEmpty() && v < heights[q.getLast()]) {
                int realizeH = heights[q.pollLast()];
                int realizeHWidth = i - (q.isEmpty() ? 0 : q.getLast() + 1);
                res = Math.max(res, realizeH * realizeHWidth);
            }
            q.add(i);
        }
        return res;
    }
}
