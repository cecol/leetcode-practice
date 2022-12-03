package leetcode202211.hard;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

public class LC1526MinimumNumberOfIncrementsOnSubarraysToFormATargetArray extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");
        var LC = new LC1526MinimumNumberOfIncrementsOnSubarraysToFormATargetArray();
    }

    /**
     * https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/discuss/754674/JavaC%2B%2BPython-Comparison-of-Consecutive-Elements
     * Greedy 比較直觀 這邊有提到
     * https://github.com/wisdompeak/LeetCode/tree/master/Greedy/1526.Minimum-Number-of-Increments-on-Subarrays-to-Form-a-Target-Array
     * 我們一直往前走, 過程中每一次 遞增到遞減 形成一座山
     * 山的上升我們納入計算, 山的下降不用 因為下降j 就是 前面上升i 時候一起 增加操作 [i, j] 達成的
     * 每一座山都是獨立計算, 山與山之間不可能 增加操作 [i, j] 一起達成 這樣2座山 中間的低谷也會被增加起來
     * <p>
     * 另一個是 Segment tree 解法
     * https://github.com/wisdompeak/LeetCode/blob/master/Greedy/1526.Minimum-Number-of-Increments-on-Subarrays-to-Form-a-Target-Array/1526.Minimum-Number-of-Increments-on-Subarrays-to-Form-a-Target-Array_SegmentTree.cpp
     * SegmentTree 記載 left/child child, start/end 區間, info 是 start/end 區間最小值, idx start/end 區間最小值位置
     * 直觀想法就是先找 0 to n-1 最小值, 把 target 加到該最小值, 假設該最小值位置是 target[i]
     * 然後再繼續找 0 to i-1 最小值 再往上加(base on target[i]) -> 遞迴
     * 然後再繼續找 i+1 to in-1 最小值 再往上加(base on target[i]) -> 遞迴
     * <p>
     * 既然要動態找區間最小值 -> SegmentTree 很適合
     * 所以要
     * 1. build tree func
     * - 跟 LC307RangeSumQueryMutable 一樣
     * 2. queryMin func: start/end 區間 min & min index
     * - queryMin 有兩種寫法
     * - 1. 是依照 LC307RangeSumQueryMutable 作法,
     * -    終止條件是 if (l == rt.start && r == rt.end)
     * -    絕對不會超界,
     * -    但是在往下面找時候要小心
     * -    1. 要算好 int mid = rt.start + (rt.end - rt.start) / 2;
     * -    mid 是拿 rt start/end 來算的
     * -    2. 根據 是在 left/right 來判定是往哪個子樹走 Ex: queryMin(rt.left, l, r);
     * -    或者是跨界 那就是
     * -      int[] ql = queryMin(rt.left, l, mid);
     * -      int[] qr = queryMin(rt.right, mid + 1, r);
     * -  2. 有機會走到超界
     * -  所以 終止條件 要定義好
     * -    1. 超界給極端值 if (r < rt.start || l > rt.end) return new int[]{Integer.MAX_VALUE, l};
     * -    2. 超過 rt start/end 給 rt 能知道的範圍 if (l <= rt.start && r >= rt.end) return new int[]{rt.info, rt.idx};
     * -    3. 直接 left/right query, 不用切 mid, 結果直接 merge left/right & return
     * <p>
     * 3. dfs func: 0 to n-1 遞迴下去找 min(要帶上上一層 base)
     * - 因為我們是逐步找最低的 一起累加上去, 當作逐步從 平地 蓋起多座山,
     * - 每次碰到一個山谷 就是再分區 左右遞迴下去
     */
    class SegTree {
        SegTree left, right;
        int start, end, info = 0, idx = 0;

        SegTree(int s, int e) {
            start = s;
            end = e;
        }
    }

    SegTree build(int s, int e, int[] target) {
        SegTree rt = new SegTree(s, e);
        if (s == e) {
            rt.info = target[s];
            rt.idx = s;
            return rt;
        }
        int mid = s + (e - s) / 2;
        rt.left = build(s, mid, target);
        rt.right = build(mid + 1, e, target);
        if (rt.left.info < rt.right.info) {
            rt.info = rt.left.info;
            rt.idx = rt.left.idx;
        } else {
            rt.info = rt.right.info;
            rt.idx = rt.right.idx;
        }
        return rt;
    }

    int[] queryMin(SegTree rt, int l, int r) {
        if (r < rt.start || l > rt.end) return new int[]{Integer.MAX_VALUE, l};
        if (l <= rt.start && r >= rt.end) return new int[]{rt.info, rt.idx};
        int[] ql = queryMin(rt.left, l, r);
        int[] qr = queryMin(rt.right, l, r);
        if (ql[0] < qr[0]) return ql;
        else return qr;
    }

    int[] queryMin2(SegTree rt, int l, int r) {
        if (l == rt.start && r == rt.end) return new int[]{rt.info, rt.idx};
        int mid = rt.start + (rt.end - rt.start) / 2;
        if (r <= mid) return queryMin(rt.left, l, r);
        else if (l > mid) return queryMin(rt.right, l, r);
        int[] ql = queryMin(rt.left, l, mid);
        int[] qr = queryMin(rt.right, mid + 1, r);
        if (ql[0] < qr[0]) return ql;
        else return qr;
    }

    int dfs(int[] target, int base, int l, int r, SegTree rt) {
        if (l > r) return 0;
        if (l == r) return target[l] - base;
        int[] qMin = queryMin(rt, l, r);
        int res = qMin[0] - base;
        res += dfs(target, qMin[0], l, qMin[1] - 1, rt);
        res += dfs(target, qMin[0], qMin[1] + 1, r, rt);
        return res;
    }

    public int minNumberOperationsSegTree(int[] target) {
        SegTree rt = build(0, target.length - 1, target);
        return dfs(target, 0, 0, target.length - 1, rt);
    }


    public int minNumberOperationsGreedy(int[] target) {
        int res = 0, pre = 0;
        for (int n : target) {
            res += Math.max(0, n - pre);
            pre = n;
        }
        return res;
    }
}
