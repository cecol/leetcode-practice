package leetcode202104.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class LC218TheSkylineProblem extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC218TheSkylineProblem();
    }

    /**
     * https://leetcode.com/problems/the-skyline-problem/discuss/61192/Once-for-all-explanation-with-clean-Java-code(O(n2)time-O(n)-space)
     * 這個是看過最好的解釋，直接針對關鍵點下去解釋:
     * If a position is shadowed by other buildings
     * 1. height of that building is larger than the building to which current position belong; -> 比較高才有辦法遮蓋
     * 2. the start point of that building must be smaller(or equal to) than this position; -> 比較早開始才有辦法遮蓋
     * 3. the end point of that building must be larger(or equal to) than this position; -> 還沒結束才有辦法遮蓋
     * -> 因此可推斷出
     * -> a: 當踩到start point, 就有機會影響當前的contour,
     * -> b: 當踩到end point 就代表這個建物的高度影響已結束
     * -> c: 當height高度變化出現最高, 代表沒人可影響他 -> 就是要的 target
     * 所以上面可以推斷出, start/end高矮變化都有機會影響 skyline
     * 我們要把 start/end都攤平來排序, 然後要配上他的高度
     * -> 所以 List<int[]> height = new ArrayList<>(); 中
     * -> height.add(new int[]{b[0], -b[2]});
     * -> height.add(new int[]{b[1], b[2]});
     * -> start 配上的是 -height, end 配上的是 +height -> 用於表示當前踩到的是 start or end
     * -> Collections.sort(height) -> 依x position 來排序, 若高度一樣, 矮的先
     * ->-> 這是因為都是 start 時，要先拿高的，避免重複算同一個位置的上升, 都是 end 時，要先拿矮的，避免重複算同一個位置的下降
     * ->-> start end 各一的時候 start必輸  因為 start配的 h都負數 -> 所以先算到下降在看上升, 先把end 收掉在看
     * 然後我們要去記錄當前遇到的高度, 然後拿最高的 take effect -> PriorityQueue
     * --> 用來記錄當前走到的所有有機會來影響的高度
     * 1. 如果拿到 start point -> 塞進PriorityQueue -> 就有機會影響當前的contour -> 加進去如果是最大->就會開始影響
     * 2. 如果拿到 end point -> PriorityQueue 把該高度刪掉 -> 代表這個建物走完了 -> 高度影響已結束
     * 所以隨後的 int cur = q.peek(); 就是拿目前還在範圍內的建物中最高的 (會因為當前走到的 start/end 有所影響)
     * 然後用 prev vs cur -> 來比較 -> 有變化就是要的答案
     * PriorityQueue 用來記錄當前走到的高度, 要default 塞0, 因為沒有building時候, 0 也在 contour中
     * <p>
     * 簡化版關鍵點
     * 1. 攤平 {start, -h}, {end, h} 並且用start/end值排序 -> 如果一樣用 h, 矮的優先, (h or -h用來辨識是start or end)
     * 2. visit 上面的 list, 並且 start -> 塞入h到 Queue(開始有影響力), end -> 刪除queue中該h(因為沒影響力)
     * 3. 拿出 q.peek() 當前累計最大 height -> 看看是否跟 prev 相等, 不同代表發生高低變化 -> 加入 result
     */
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        List<int[]> height = new ArrayList<>();
        for (int[] b : buildings) {
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }

        Collections.sort(height, (x, y) -> {
            if (x[0] != y[0]) return x[0] - y[0];
            else return x[1] - y[1];
        });

        PriorityQueue<Integer> q = new PriorityQueue<>((x, y) -> y - x);
        q.offer(0);
        int prevMaxH = 0;
        for (int[] h : height) {
            if (h[1] < 0) q.offer(-h[1]);
            else q.remove(h[1]);

            int curMaxH = q.peek();
            if (prevMaxH != curMaxH) {
                res.add(List.of(h[0], curMaxH));
                prevMaxH = curMaxH;
            }
        }
        return res;
    }
}
