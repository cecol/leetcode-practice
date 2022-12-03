package leetcode202211.medium;

import leetcode20200921to20201031.BasicTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class LC370RangeAddition extends BasicTemplate {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger("Main");

    }

    class SegTree {
        SegTree left, right;
        int start, end;
        long delta;
        boolean tag;

        SegTree(int s, int e) {
            start = s;
            end = e;
            if (s == e) {
                return;
            }
            int mid = s + (e - s) / 2;
            left = new SegTree(s, mid);
            right = new SegTree(mid + 1, e);
        }

        void pushDown() {
            if (tag && left != null) {
                left.delta += delta;
                right.delta += delta;
                left.tag = true;
                right.tag = true;
                delta = 0;
                tag = false;
            }
        }

        void updateRangeBy(int s, int e, int val) {
            if (e < start || s > end) return;
            if (s <= start && e >= end) {
                delta += val;
                tag = true;
                return;
            }
            pushDown();
            left.updateRangeBy(s, e, val);
            right.updateRangeBy(s, e, val);
        }

        long rangeQuery(int s, int e) {
            if (e < start || s > end) return 0L;
            if (s <= start && e >= end) {
                return delta;
            }
            pushDown();
            return left.rangeQuery(s, e) + right.rangeQuery(s, e);
        }
    }


    /**
     * https://leetcode.com/problems/range-addition/discuss/84217/Java-O(K-%2B-N)time-complexity-Solution
     * https://leetcode.com/problems/range-addition/discuss/84217/Java-O(K-+-N)time-complexity-Solution/88593
     * 這題有兩個解法
     * 1. 差分数组, 簡單來說記載每個 updates[i][0] 頭加上多少, 但他不是一路加到 length - 1,
     * - 所以 updates[i][1] 尾要扣回來
     * - 這樣最後會得到一個 diff[i] 表示 nums[i] 比 nums[i-1] 大多少
     * - 最後一路 sum 過去, 把前面遇到的 diff 加回來
     * - Ex: [1 , 3 , 2] , [2, 3, 3] (length = 5)
     * - res[ 0, 2, ,0, 0 -2 ]
     * - res[ 0 ,2, 3, 0, -5]
     * - sum 0, 2, 5, 5, 0
     * - res[0, 2, 5, 5, 0]
     * 2. segment tree, 跟 LC307RangeSumQueryMutable 很類似
     * https://github.com/wisdompeak/LeetCode/tree/master/Segment_Tree/370.Range-Addition
     * - 可是照著實作會 TLE, 因為 LC307RangeSumQueryMutable 每次更新是 touch 到底層 leave 節點
     * - 得用 Advanced Segment tree - lazy propagation
     * https://github.com/wisdompeak/LeetCode/blob/master/Segment_Tree/370.Range-Addition/370.Range-Addition_SegTree.cpp
     * - 有幾個細節
     * -    1. SegTree 不記載 建立時候不記載什麼值了, 因為初始化每個值都是 0
     * -    2. SegTree 有 delta 跟 boolean, tag
     * -    delta 記載當層 區間的 ModifiedArray, tag 是記載目前當層的 delta 更新是否有推到下層, 還是只是 lazy update
     * -    3. 有個 pushDown 每當 update or query 都會先看看當層是否要往下 left/right query, 如果有, 就得試試 pushDown
     * -    把當層之前 lazy update 直往下層帶
     * -    4. updateRangeBy, 這邊是採非 mid 來分左右取值, 直接看是否 超界 或者
     * -    如果當前 node start/end 在 query 範圍內, 直接當前 node.delta += val, 不用去 left/right child, tag = true
     * -    如果得 updateRangeBy left/right child -> 先 pushDown 然後才去 updateRangeBy(left/right child)
     * -    5.rangeQuery 這邊是採非 mid 來分左右取值, 直接看 超界就是回傳 0
     * -    如果當前 node start/end 在 query 範圍內, 直接回傳當前 node.delta
     * -    如果得 rangeQuery left/right child -> 先 pushDown 然後才去 rangeQuery left + right
     *
     * -    其實注意 最後 res[i] = (int) rt.rangeQuery(i, i); 都會最後走到 leave,
     * -    而這過程中會把 parent 中未帶下來的 lazy delta 帶到 leave, 因為每一層都會 call pushDown();
     * -    最後會把之前囤在 parent delta 帶到 leave, 然後回傳 leave
     */
    public int[] getModifiedArray(int length, int[][] updates) {
        SegTree rt = new SegTree(0, length - 1);
        for (int[] u : updates) {
            rt.updateRangeBy(u[0], u[1], u[2]);
        }

        int[] res = new int[length];
        for (int i = 0; i < length; i++) {
            res[i] = (int) rt.rangeQuery(i, i);
        }
        return res;
    }

    public int[] getModifiedArrayDiif(int length, int[][] updates) {
        int[] res = new int[length];
        for (int[] u : updates) {
            int val = u[2];
            int start = u[0];
            int end = u[1];
            res[start] += val;
            if (end < length - 1) res[end + 1] -= val;
        }
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += res[i];
            res[i] = sum;
        }
        return res;
    }
}
