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

    class SegTree {
        SegTree left, right;
        int start, end;
        int info;
        boolean tag;

        SegTree(int s, int e) {
            start = s;
            end = e;
            if (s == e) return;
            int mid = s + (e - s) / 2;
            left = new SegTree(s, mid);
            right = new SegTree(mid + 1, e);
        }

        void pushDown() {
            if (tag && left != null) {
                tag = false;
                left.info += info;
                right.info += info;
                left.tag = true;
                right.tag = true;
            }
        }

        void updateRange(int s, int e, int v) {
            if (e < start || s > end) return;
            if (s <= start && e >= end) {
                info = v;
                tag = true;
                return;
            }

            pushDown();
            left.updateRange(s, e, v);
            right.updateRange(s, e, v);
            info = Math.max(left.info, right.info);
        }

        int queryRange(int s, int e) {
            if (e < start || s > end) return Integer.MIN_VALUE;
            if (s <= start && e >= end) return info;
            pushDown();
            int res = Math.max(left.queryRange(s, e), right.queryRange(s, e));
            info = Math.max(left.info, right.info);
            return res;
        }
    }

    void dfs(SegTree rt) {
        if (rt.start == rt.end || rt.tag) height.add(List.of(rt.start, rt.info));
        else {
            dfs(rt.left);
            dfs(rt.right);
        }
    }

    List<List<Integer>> height = new ArrayList<>();

    public List<List<Integer>> getSkyline(int[][] buildings) {
        TreeSet<Integer> ts = new TreeSet<>();
        for (int[] b : buildings) {
            ts.add(b[0]);
            ts.add(b[1]);
        }

        Map<Integer, Integer> idx2Pos = new HashMap<>();
        Map<Integer, Integer> pos2Idx = new HashMap<>();
        int idx = 0;
        for (Integer i : ts) {
            idx2Pos.put(idx, i);
            pos2Idx.put(i, idx);
            idx++;
        }

        int n = ts.size();
        SegTree rt = new SegTree(0, n - 1);
        Arrays.sort(buildings, Comparator.comparingInt(x -> x[2]));
        for (int[] b : buildings) {
            rt.updateRange(pos2Idx.get(b[0]), pos2Idx.get(b[1]) - 1, b[2]);
        }
        dfs(rt);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < height.size(); i++) {
            res.add(List.of(idx2Pos.get(height.get(i).get(0)), height.get(i).get(1)));
            while (i + 1 < height.size() && height.get(i + 1).get(1) == height.get(i).get(1)) i++;
        }
        return res;
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
     * -> Collections.sort(height) -> 依 x position 來排序, x 先出現先有機會覆蓋到其他人
     * -    但如果 2點 ｘ 都一樣, 選擇 x[1] - y[1] 比較
     * -    兩者都是 -h1, -h2, 都是 start, 高的那一個優先覆蓋到其他人(後續 pq 那邊遇到 start 是加入覆蓋行列)
     * -        高的換成 -h 更低, 所以 x[1] - y[1] 會放在前面
     * -    兩者都是 +h1, +h2, 都是 end, 低的優先退出(後續 pq 那邊遇到 end 是退出),
     * 所以 x[1] - y[1] end h 低的 會放在前面, 讓同樣高的留在裡面,
     * -        如果高的 end 先退出 pq, 會造成高低變化, 會記錄到錯的點 (同一個 ｘ 位置, 但他會邊下降邊以為又遇到先的高低變化)
     * -    一著 -h(start), 一者 +h(end), -h 優先考量, 先看 start 覆蓋是否創造新的 高度變化
     * -        再看 end 退出, 用 x[1] - y[1] 符合前兩者也不違背結果
     * 然後我們要去記錄當前遇到的高度, 然後拿最高的 take effect -> PriorityQueue
     * --> 用來記錄當前走到的所有有機會來影響的高度
     * 1. 如果拿到 start point -> 塞進PriorityQueue -> 就有機會影響當前的contour -> 加進去如果是最大->就會開始影響
     * 2. 如果拿到 end point -> PriorityQueue 把該高度刪掉 -> 代表這個建物走完了 -> 高度影響已結束
     * 所以隨後的 int cur = q.peek(); 就是拿目前還在範圍內的建物中最高的 (會因為當前走到的 start/end 有所影響)
     * 然後用 prev vs cur -> 來比較 -> 有變化就是要的答案
     * PriorityQueue 用來記錄當前走到的高度, 要default 塞0, 因為沒有building時候, 0 也在 contour中
     * <p>
     * 簡化版關鍵點
     * 1. 攤平 {start, -h}, {end, h} 並且用start/end值排序 -> 如果一樣用 h 比較: x[1] - y[1], (h or -h用來辨識是start or end)
     * 2. visit 上面的 list, 並且 start -> 塞入h到 Queue(開始有影響力), end -> 刪除queue中該h(因為沒影響力)
     * 3. 拿出 q.peek() 當前累計最大 height -> 看看是否跟 prev 相等, 不同代表發生高低變化 -> 加入 result
     */
    public List<List<Integer>> getSkylinePQ(int[][] buildings) {
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
